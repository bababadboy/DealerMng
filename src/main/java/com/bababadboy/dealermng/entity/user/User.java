package com.bababadboy.dealermng.entity.user;

import com.bababadboy.dealermng.entity.Dealer;
import com.bababadboy.dealermng.entity.Group;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author wangxiaobin
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(nullable = false)
    private String username;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 6, message = "Minimum password length: 6 characters")
    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToOne
    private Dealer dealer;

    @OneToOne
    private Group group;

    public User() {
    }

    public User(@Size(min = 4, max = 255, message = "Minimum username length: 4 characters") String username, String email, @Size(min = 6, message = "Minimum password length: 6 characters") String password, List<Role> roles, Group group) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.group = group;
    }

    /** getter and setter **/
    //得到对应的经销商
    public void setDealer(Dealer dealer){
        this.dealer = dealer;
    }

    public Dealer getDealer(){return dealer;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
