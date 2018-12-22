-- 产品详情表
INSERT INTO product(categories, description, name, no, price, stocks, version) VALUES("电脑桌","放电脑的桌子","FUN科技电脑桌","YK070005B",233.33,230,1);
INSERT INTO product(categories, description, name, no, price, stocks, version) VALUES("电脑桌","放电脑的桌子","极客湾的电脑桌","YG073005B",250.33,200,1);

-- 经销商详情表
insert into dealer(id, city, details, district, province, street, area, credit, expired_at, gender, name, note, phone, register_at) values(1, "Hangzhou", "HangZhouDianZi University", "JiangGan", "ZheJiang", "BaiYang", "JiaXing", 1, "2018-12-06 12:00:00", "male", "NormalAsh1", "NULL", "12345678901", "2018-12-06 11:00:00");
insert into dealer(id, city, details, district, province, street, area, credit, expired_at, gender, name, note, phone, register_at) values(2, "Hangzhou", "HangZhouDianZi University", "JiangGan", "ZheJiang", "BaiYang", "JiaXing", 2, "2018-12-06 13:00:00", "male", "NormalAsh2", "NULL", "12345678902", "2018-12-06 12:00:00");
insert into dealer(id, city, details, district, province, street, area, credit, expired_at, gender, name, note, phone, register_at) values(3, "Hangzhou", "HangZhouDianZi University", "JiangGan", "ZheJiang", "BaiYang", "JiaXing", 3, "2018-12-06 14:00:00", "male", "NormalAsh3", "NULL", "12345678903", "2018-12-06 13:00:00");

-- 产品详情图片表
INSERT into detail_images(images_order,id, images_url) VALUES(0,1,"https://img.alicdn.com/imgextra/i1/355159670/TB2dsDQBH1YBuNjSszeXXablFXa_!!355159670.jpg");
INSERT into detail_images(images_order,id, images_url) VALUES(1,1,"https://img.alicdn.com/imgextra/i1/355159670/TB2t_TnCv1TBuNjy0FjXXajyXXa_!!355159670.jpg");
INSERT into detail_images(images_order,id, images_url) VALUES(2,1,"https://img.alicdn.com/imgextra/i4/355159670/TB2WXYgfKEJL1JjSZFGXXa6OXXa_!!355159670.jpg");

INSERT into detail_images(images_order,id, images_url) VALUES(3,2,"https://img.alicdn.com/imgextra/i1/355159670/TB2dsDQBH1YBuNjSszeXXablFXa_!!355159670.jpg");
INSERT into detail_images(images_order,id, images_url) VALUES(4,2,"https://img.alicdn.com/imgextra/i1/355159670/TB2t_TnCv1TBuNjy0FjXXajyXXa_!!355159670.jpg");
INSERT into detail_images(images_order,id, images_url) VALUES(5,2,"https://img.alicdn.com/imgextra/i4/355159670/TB2WXYgfKEJL1JjSZFGXXa6OXXa_!!355159670.jpg");



-- 产品轮播图片表
INSERT into carousel_images(images_order,id, images_url) VALUES(0,1,"https://gd4.alicdn.com/imgextra/i1/355159670/TB2dsDQBH1YBuNjSszeXXablFXa_!!355159670.jpg_400x400.jpg");
INSERT into carousel_images(images_order,id, images_url) VALUES(1,1,"http://gd1.alicdn.com/imgextra/i1/355159670/TB2_J61BN9YBuNjy0FfXXXIsVXa_!!355159670.jpg");
INSERT into carousel_images(images_order,id, images_url) VALUES(2,1,"http://gd1.alicdn.com/imgextra/i1/355159670/TB2yzHhB49YBuNjy0FfXXXIsVXa_!!355159670.jpg");

INSERT into carousel_images(images_order,id, images_url) VALUES(3,2,"https://gd4.alicdn.com/imgextra/i1/355159670/TB2dsDQBH1YBuNjSszeXXablFXa_!!355159670.jpg_400x400.jpg");
INSERT into carousel_images(images_order,id, images_url) VALUES(4,2,"http://gd1.alicdn.com/imgextra/i1/355159670/TB2_J61BN9YBuNjy0FfXXXIsVXa_!!355159670.jpg");
INSERT into carousel_images(images_order,id, images_url) VALUES(5,2,"http://gd1.alicdn.com/imgextra/i1/355159670/TB2yzHhB49YBuNjy0FfXXXIsVXa_!!355159670.jpg");

-- 订单子项
INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details1","district1","浙江","下沙",CURRENT_TIME(),CURRENT_TIME(),"315315","note1","ordernum1","支付宝","1",2443.5,CURRENT_TIME(),CURRENT_TIME(),"13371243211",1);
INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details2","district1","浙江","下沙",CURRENT_TIME(),CURRENT_TIME(),"315315","note1","ordernum2","支付宝","2",2443.5,CURRENT_TIME(),CURRENT_TIME(),"13371243211",2);
INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details3","district1","浙江","下沙",CURRENT_TIME(),CURRENT_TIME(),"315315","note1","ordernum3","支付宝","3",2443.5,CURRENT_TIME(),CURRENT_TIME(),"13371243211",1);
INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details4","district1","浙江","下沙",CURRENT_TIME(),CURRENT_TIME(),"315315","note1","ordernum4","支付宝","6",2443.5,CURRENT_TIME(),CURRENT_TIME(),"13371243211",1);
INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details5","district1","浙江","下沙",CURRENT_TIME(),CURRENT_TIME(),"315315","note1","ordernum5","支付宝","7",2443.5,CURRENT_TIME(),CURRENT_TIME(),"13371243211",2);
INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details6","district1","浙江","下沙",CURRENT_TIME(),CURRENT_TIME(),"315315","note1","ordernum6","支付宝","4",2443.5,CURRENT_TIME(),CURRENT_TIME(),"13371243211",3);
INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details7","district1","浙江","下沙",CURRENT_TIME(),CURRENT_TIME(),"315315","note1","ordernum7","支付宝","5",2443.5,CURRENT_TIME(),CURRENT_TIME(),"13371243211",1);

-- 订单详情
INSERT INTO order_detail(amount, total_money, order_item_id, product_no) VALUES (3,55245,1,"YK070005B");
INSERT INTO order_detail(amount, total_money, order_item_id, product_no) VALUES (3,44445,2,"YK070005B");
INSERT INTO order_detail(amount, total_money, order_item_id, product_no) VALUES (3,5545,2,"YK070005B");


-- 登录用户信息表
-- INSERT into application_user(username,password) VALUES ("rychou","rychou123");


-- INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('1', '1', '2018-12-13', '1200', '2018-12-13', '2018-12-13', '2018-12-13', '1200', '1', '1', '1', '1', '1', '1');
-- INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('2', '1', '2018-12-12', '1000', '2018-12-12', '2018-12-12', '2018-12-12', '1000', '1', '1', '2', '2', '1', '2');
-- INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('3', '1', '2018-12-11 00:00:00.000000', '2', '2018-12-11 00:00:00.000000', '2018-12-11 00:00:00.000000', '2018-12-11 00:00:00.000000', '22', '1', '1', '1213', '3', '1', '3');
-- INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('4', '1', '2018-12-10 00:00:00.000000', '3333', '2018-12-10 00:00:00.000000', '2018-12-10 00:00:00.000000', '2018-12-10 00:00:00.000000', '123', '1', '1', '3333', '4', '1', '2');

-- INSERT INTO `product_sale_info` (`customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('1', '2018-12-13', '1200', '2018-12-13', '2018-12-13', '2018-12-13', '1200', '1', '1', '1', '1', '1', '1');
-- INSERT INTO `product_sale_info` (`customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('1', '2018-12-12', '1000', '2018-12-12', '2018-12-12', '2018-12-12', '1000', '1', '1', '2', '2', '1', '2');
-- INSERT INTO `product_sale_info` (`customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('1', '2018-12-11 00:00:00.000000', '2', '2018-12-11 00:00:00.000000', '2018-12-11 00:00:00.000000', '2018-12-11 00:00:00.000000', '22', '1', '1', '1213', '3', '1', '3');
-- INSERT INTO `product_sale_info` (`customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('1', '2018-12-10 00:00:00.000000', '3333', '2018-12-10 00:00:00.000000', '2018-12-10 00:00:00.000000', '2018-12-10 00:00:00.000000', '123', '1', '1', '3333', '4', '1', '2');

