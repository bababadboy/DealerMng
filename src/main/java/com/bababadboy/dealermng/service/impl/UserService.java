package com.bababadboy.dealermng.service.impl;

import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.Group;
import com.bababadboy.dealermng.entity.user.Role;
import com.bababadboy.dealermng.entity.user.User;
import com.bababadboy.dealermng.exception.CustomException;
import com.bababadboy.dealermng.repository.DealerRepository;
import com.bababadboy.dealermng.repository.GroupRepository;
import com.bababadboy.dealermng.repository.UserRepository;
import com.bababadboy.dealermng.security.JwtTokenProvider;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountLockedException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author wangxiaobin
 */
@Transactional
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    private final ModelMapper modelMapper;

    private final DealerRepository dealerRepository;

    private final GroupRepository groupRepository;

    private final StringRedisTemplate stringRedisTemplate;

    private static final int MAX_RETRY_FAIL_TIMES = 5;

    @Value("${login.failTimes}")
    private int loginFailTimes;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, ModelMapper modelMapper, DealerRepository dealerRepository, GroupRepository groupRepository,StringRedisTemplate stringRedisTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.dealerRepository = dealerRepository;
        this.groupRepository = groupRepository;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 用户注册
     * @return 注册成功，返回生成的jwt
     */
    public String signUp(User user) throws ServiceException {
        if (!userRepository.existsUserByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            LOG.info("注册密码是："+user.getPassword());
            // 得到与user一一对应的dealer
            Dealer d = user.getDealer();
            try {
                // 设置dealer经销商的默认属性
                if (null != d){
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
//                d.setRegisterAt(new Date(ts.getTime()));
                    d.setRegisterAt(new Date(System.currentTimeMillis()));
                    Timestamp expiredTime = new Timestamp(1570673410);
                    d.setExpiredAt(new Date(expiredTime.getTime()));
                    d.setCredit(1);
                    d.setArea("中国华东区");
                    dealerRepository.save(d);
                    user.setDealer(d);
                    LOG.info("{}对应的dealer保存成功!",user.getUsername());
                }
                user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
                userRepository.save(user);
            } catch (Exception e) {
                e.printStackTrace();
                return "null";
            }
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomException("Username is already in use.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * 集团注册
     * @return 注册成功，返回生成的jwt
     */
    public String groupSignUp(User user) throws ServiceException {
        if (!userRepository.existsUserByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // 得到与user唯一对应的group
//            Group group = user.getGroup();
            Group group = user.getGroup();
            group.setGroupName("王氏集团有限公司");
            groupRepository.save(group);

            user.setRoles(Arrays.asList(Role.ROLE_ADMIN));
            user.setGroup(group);
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        }

//        else {
//            throw new CustomException("GroupName is already in use.", HttpStatus.UNPROCESSABLE_ENTITY);
//        }
        return "";
    }

    /**
     * 用户登录,返回token,关注
     * @see MyUserDetailsImpl
     */
    public String logIn(String username, String password) throws AccountLockedException,BadCredentialsException{

        // 用户名是否存在
        if (!userRepository.existsUserByUsername(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 登录密码验证
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (BadCredentialsException ex){             // 坏的登录凭证
            LOG.info("用户名{}登录，密码错误",username);
            if (stringRedisTemplate.opsForValue().get("login:error:"+username) == null) {
                stringRedisTemplate.opsForValue().set("login:error:"+username,String.valueOf(0));   // 初始化次数0
            }
            stringRedisTemplate.opsForValue().increment("login:error:"+username);   // 自增
            if (Integer.valueOf(stringRedisTemplate.opsForValue().get("login:error:"+username))
                    >= MAX_RETRY_FAIL_TIMES){
                userRepository.findByUsername(username).setLocked(true);
                throw new AccountLockedException();
            }
            LOG.info("用户名{}登录，密码错误次数{}",username,stringRedisTemplate.opsForValue().get("login:error:"+username));

            throw ex;
        }catch (AccountStatusException ex){
            LOG.info("{}账户被锁定",username);
            throw ex;
        }
        // 密码正确则删除错误次数
        stringRedisTemplate.delete("login:error:"+username);
        // 创建token
        String token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        LOG.info("{}登录创建token：{}",username,token);
        return token;
    }

    /**
     * 用户登录,返回token
     */
    public List<Object> logInAndReturnUserInfo(String username, String password) {
        List<Object> list = new ArrayList<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("token是"+jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles()));
            list.add(userRepository.findByUsername(username));
            list.add(jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles()));
            return list;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username or password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * 删除注册的用户
     */
    public void delete(String username) {
        userRepository.deleteUserByUsername(username);
    }

    /**
     * 查找用户
     */
    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }


    public User whoami(HttpServletRequest req) {
        return userRepository.
                findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

}
