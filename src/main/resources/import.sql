-- 产品详情表
-- 办公家具、客厅家具、卧室家具、书房家具、卫浴家具、厨卫家具
INSERT INTO product(categories, description, name, no, price, stocks, version) VALUES("办公家具","放电脑的桌子","极客湾的电脑桌","YK070005B",233.33,230,1);
INSERT INTO product(categories, description, name, no, price, stocks, version) VALUES("卧室家具","放电脑的桌子","极客湾的电脑桌","YG073005B",150.33,200,1);
INSERT INTO product(categories, description, name, no, price, stocks, version) VALUES("厨卫家具","放电脑的桌子","极客湾的电脑桌","YG074005B",250.33,200,1);
INSERT INTO `dealermng`.`product` (`categories`, `description`, `name`, `no`, `price`, `stocks`, `version`) VALUES ('书房家具', '婴儿摇篮', '宝贝摇篮', 'YG073006B', '400', '300', '1');
INSERT INTO `dealermng`.`product` (`categories`, `description`, `name`, `no`, `price`, `stocks`, `version`) VALUES ('客厅家具', '儿童木马', '儿童木马', 'YG073007B', '500', '300', '1');
INSERT INTO `dealermng`.`product` (`categories`, `description`, `name`, `no`, `price`, `stocks`, `version`) VALUES ('书房家具', '书桌', '檀木书桌', 'YG073008B', '2000', '100', '1');
INSERT INTO `dealermng`.`product` (`categories`, `description`, `name`, `no`, `price`, `stocks`, `version`) VALUES ('卫浴家具', '洗漱台', '豪华洗漱', 'YG073009B', '3000', '200', '1');

-- 经销商详情表
insert into dealer(id, city, details, district, province, street, area, credit, expired_at, gender, name, note, phone, register_at) values(1, "Hangzhou", "HangZhouDianZi University", "JiangGan", "ZheJiang", "BaiYang", "JiaXing", 1, "2018-12-06 12:00:00", "male", "NormalAsh1", "NULL", "12345678901", "2018-12-06 11:00:00");
insert into dealer(id, city, details, district, province, street, area, credit, expired_at, gender, name, note, phone, register_at) values(2, "Hangzhou", "HangZhouDianZi University", "JiangGan", "ZheJiang", "BaiYang", "JiaXing", 2, "2018-12-06 13:00:00", "male", "NormalAsh2", "NULL", "12345678902", "2018-12-06 12:00:00");
insert into dealer(id, city, details, district, province, street, area, credit, expired_at, gender, name, note, phone, register_at) values(3, "Hangzhou", "HangZhouDianZi University", "JiangGan", "ZheJiang", "BaiYang", "JiaXing", 3, "2018-12-06 14:00:00", "male", "NormalAsh3", "NULL", "12345678903", "2018-12-06 13:00:00");
INSERT INTO `dealermng`.`dealer` (`id`, `city`, `details`, `district`, `province`, `street`, `area`, `credit`, `expired_at`, `gender`, `name`, `note`, `phone`, `register_at`) VALUES ('4', 'ShangHai', 'ShangHai University', 'PuDong', 'ShangHai', 'BeiDa', 'PuDong', '4', '2018-12-06 15:00:00.000000', 'female', 'NormaliYmz1', 'NULL', '12345678905', '2018-12-06 15:00:00.000000');
INSERT INTO `dealermng`.`dealer` (`id`, `city`, `details`, `district`, `province`, `street`, `area`, `credit`, `expired_at`, `gender`, `name`, `note`, `phone`, `register_at`) VALUES ('5', 'ShenZhen', 'GuangDong University', 'ShenZhen', 'GuangDong', 'BuDu', 'BuDo', '5', '2018-12-06 16:00:00.000000', 'male', 'NormaliYmz2', 'NULL', '12345678906', '2018-12-06 16:00:00.000000');
INSERT INTO `dealermng`.`dealer` (`id`, `city`, `details`, `district`, `province`, `street`, `area`, `credit`, `expired_at`, `gender`, `name`, `note`, `phone`, `register_at`) VALUES ('6', 'NanJing', 'NanJing Transport', 'NanJing', 'JiangSu', 'BuDv', 'BuDv', '6', '2018-12-06 16:00:00.000000', 'femal', 'NormaliYmz3', 'NULL', '12345678907', '2018-12-06 16:00:00.000000');

-- 产品详情图片表
INSERT into detail_images(id, images_url) VALUES(1,"https://img.alicdn.com/imgextra/i4/1634990931/O1CN01nTTJHj1IkRDrcI2IP_!!1634990931.jpg");
INSERT into detail_images(id, images_url) VALUES(1,"https://img.alicdn.com/imgextra/i3/1634990931/O1CN01wpyBKU1IkRDufJrkn_!!1634990931.jpg");
INSERT into detail_images(id, images_url) VALUES(1,"https://img.alicdn.com/imgextra/i4/1634990931/O1CN01fECBAp1IkRDrcIZZZ_!!1634990931.jpg");

INSERT into detail_images(id, images_url) VALUES(2,"https://img.alicdn.com/imgextra/i3/711882586/O1CN011UyQhHk29R0HLsr_!!711882586.jpg");
INSERT into detail_images(id, images_url) VALUES(2,"https://img.alicdn.com/imgextra/i3/711882586/O1CN013pdFd01UyQhL03Wva_!!711882586.jpg");
INSERT into detail_images(id, images_url) VALUES(2,"https://img.alicdn.com/imgextra/i3/711882586/O1CN011UyQhHk29R0HLsr_!!711882586.jpg");

INSERT into detail_images(id, images_url) VALUES(3,"https://gd4.alicdn.com/imgextra/i1/1069028005/TB2rEGCXEhnpuFjSZFEXXX0PFXa_!!1069028005.jpg");
INSERT into detail_images(id, images_url) VALUES(3,"https://gd1.alicdn.com/imgextra/i1/1069028005/TB2w1A9eOpnpuFjSZFkXXc4ZpXa_!!1069028005.jpg");
INSERT into detail_images(id, images_url) VALUES(3,"https://gd3.alicdn.com/imgextra/i3/1069028005/TB2HZPog5CYBuNkSnaVXXcMsVXa_!!1069028005.jpg");

INSERT into detail_images(id, images_url) VALUES(4,"https://gd1.alicdn.com/imgextra/i3/4259915058/O1CN01xjRq8d1nEbmoyaiqI_!!4259915058.jpg");
INSERT into detail_images(id, images_url) VALUES(4,"https://gd2.alicdn.com/imgextra/i2/4259915058/O1CN01HGayi91nEbmoKMLCm_!!4259915058.jpg");
INSERT into detail_images(id, images_url) VALUES(4,"https://gd4.alicdn.com/imgextra/i4/4259915058/O1CN01J9Vfay1nEbmq3xdHN_!!4259915058.jpg");

INSERT into detail_images(id, images_url) VALUES(5,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01bvMQ7N1PZAmoxSROM_!!3336611854.jpg");
INSERT into detail_images(id, images_url) VALUES(5,"https://img.alicdn.com/imgextra/i1/3336611854/O1CN016lCeiW1PZAn7QjxF6_!!3336611854.jpg");
INSERT into detail_images(id, images_url) VALUES(5,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01r9fFts1PZAmoL9cr8_!!3336611854.jpg");

INSERT into detail_images(id, images_url) VALUES(6,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01bvMQ7N1PZAmoxSROM_!!3336611854.jpg");
INSERT into detail_images(id, images_url) VALUES(6,"https://img.alicdn.com/imgextra/i1/3336611854/O1CN016lCeiW1PZAn7QjxF6_!!3336611854.jpg");
INSERT into detail_images(id, images_url) VALUES(6,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01r9fFts1PZAmoL9cr8_!!3336611854.jpg");

INSERT into detail_images(id, images_url) VALUES(7,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01bvMQ7N1PZAmoxSROM_!!3336611854.jpg");
INSERT into detail_images(id, images_url) VALUES(7,"https://img.alicdn.com/imgextra/i1/3336611854/O1CN016lCeiW1PZAn7QjxF6_!!3336611854.jpg");
INSERT into detail_images(id, images_url) VALUES(7,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01r9fFts1PZAmoL9cr8_!!3336611854.jpg");


-- 产品轮播图片表
INSERT into carousel_images(id, images_url) VALUES(1,"https://img.alicdn.com/imgextra/i4/1634990931/O1CN01nTTJHj1IkRDrcI2IP_!!1634990931.jpg");
INSERT into carousel_images(id, images_url) VALUES(1,"https://img.alicdn.com/imgextra/i3/1634990931/O1CN01wpyBKU1IkRDufJrkn_!!1634990931.jpg");
INSERT into carousel_images(id, images_url) VALUES(1,"https://img.alicdn.com/imgextra/i4/1634990931/O1CN01fECBAp1IkRDrcIZZZ_!!1634990931.jpg");

INSERT into carousel_images(id, images_url) VALUES(2,"https://img.alicdn.com/imgextra/i3/711882586/O1CN011UyQhHk29R0HLsr_!!711882586.jpg");
INSERT into carousel_images(id, images_url) VALUES(2,"https://img.alicdn.com/imgextra/i3/711882586/O1CN013pdFd01UyQhL03Wva_!!711882586.jpg");
INSERT into carousel_images(id, images_url) VALUES(2,"https://img.alicdn.com/imgextra/i3/711882586/O1CN011UyQhHk29R0HLsr_!!711882586.jpg");

INSERT into carousel_images(id, images_url) VALUES(3,"https://gd4.alicdn.com/imgextra/i1/1069028005/TB2rEGCXEhnpuFjSZFEXXX0PFXa_!!1069028005.jpg");
INSERT into carousel_images(id, images_url) VALUES(3,"https://gd1.alicdn.com/imgextra/i1/1069028005/TB2w1A9eOpnpuFjSZFkXXc4ZpXa_!!1069028005.jpg");
INSERT into carousel_images(id, images_url) VALUES(3,"https://gd3.alicdn.com/imgextra/i3/1069028005/TB2HZPog5CYBuNkSnaVXXcMsVXa_!!1069028005.jpg");

INSERT into carousel_images(id, images_url) VALUES(4,"https://gd1.alicdn.com/imgextra/i3/4259915058/O1CN01xjRq8d1nEbmoyaiqI_!!4259915058.jpg");
INSERT into carousel_images(id, images_url) VALUES(4,"https://gd2.alicdn.com/imgextra/i2/4259915058/O1CN01HGayi91nEbmoKMLCm_!!4259915058.jpg");
INSERT into carousel_images(id, images_url) VALUES(4,"https://gd4.alicdn.com/imgextra/i4/4259915058/O1CN01J9Vfay1nEbmq3xdHN_!!4259915058.jpg");

INSERT into carousel_images(id, images_url) VALUES(5,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01bvMQ7N1PZAmoxSROM_!!3336611854.jpg");
INSERT into carousel_images(id, images_url) VALUES(5,"https://img.alicdn.com/imgextra/i1/3336611854/O1CN016lCeiW1PZAn7QjxF6_!!3336611854.jpg");
INSERT into carousel_images(id, images_url) VALUES(5,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01r9fFts1PZAmoL9cr8_!!3336611854.jpg");

INSERT into carousel_images(id, images_url) VALUES(6,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01bvMQ7N1PZAmoxSROM_!!3336611854.jpg");
INSERT into carousel_images(id, images_url) VALUES(6,"https://img.alicdn.com/imgextra/i1/3336611854/O1CN016lCeiW1PZAn7QjxF6_!!3336611854.jpg");
INSERT into carousel_images(id, images_url) VALUES(6,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01r9fFts1PZAmoL9cr8_!!3336611854.jpg");

INSERT into carousel_images(id, images_url) VALUES(7,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01bvMQ7N1PZAmoxSROM_!!3336611854.jpg");
INSERT into carousel_images(id, images_url) VALUES(7,"https://img.alicdn.com/imgextra/i1/3336611854/O1CN016lCeiW1PZAn7QjxF6_!!3336611854.jpg");
INSERT into carousel_images(id, images_url) VALUES(7,"https://img.alicdn.com/imgextra/i2/3336611854/O1CN01r9fFts1PZAmoL9cr8_!!3336611854.jpg");


-- 订单子项
--
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details1","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum1","支付宝","unPaid",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",1);
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details2","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum2","支付宝","Paid",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",2);
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details3","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum3","支付宝","companyConfirmed",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",1);
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details4","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum4","支付宝","delivered",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",1);
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details5","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum5","支付宝","signed",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",2);
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details6","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum6","支付宝","orderDone",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",3);
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details7","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum7","支付宝","Paid",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",1);
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details7","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum7","支付宝","salesReturned",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",1);
-- INSERT INTO order_item(city, details, district, province, street, completed_at, delivered_at, express_number, note, order_no, order_payment_method, order_status, order_total_price, ordered_at, paid_at, phone, dealer_id) VALUES ("杭州","details7","district1","浙江","下沙",UTC_TIME(),UTC_TIME(),"315315","note1","ordernum7","支付宝","delivered",2443.5,UTC_TIME(),UTC_TIME(),"13371243211",1);

-- 订单详情
-- INSERT INTO order_detail(amount, total_money, order_item_id, product_no) VALUES (3,55245,1,"YK070005B");
-- INSERT INTO order_detail(amount, total_money, order_item_id, product_no) VALUES (3,44445,2,"YK070005B");
-- INSERT INTO order_detail(amount, total_money, order_item_id, product_no) VALUES (3,5545,2,"YK070005B");


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




-- 销售信息表
INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('1', '1', '2018-12-13', '1200', '2018-12-13', '2018-12-13', '2018-12-13', '1200', '1', '1', '1', '1', '1', '1');
INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('2', '1', '2018-12-12', '1000', '2018-12-12', '2018-12-12', '2018-12-12', '1000', '1', '1', '2', '2', '1', '2');
INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('3', '1', '2018-12-11 00:00:00.000000', '2', '2018-12-11 00:00:00.000000', '2018-12-11 00:00:00.000000', '2018-12-11 00:00:00.000000', '22', '1', '1', '1213', '3', '1', '3');
INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('4', '1', '2018-12-10 00:00:00.000000', '3333', '2018-12-10 00:00:00.000000', '2018-12-10 00:00:00.000000', '2018-12-10 00:00:00.000000', '123', '1', '1', '3333', '4', '1', '2');
INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('5', '2', '2018-10-10 00:00:00.000000', '2456', '2018-10-10 00:00:00.000000', '2018-10-10 00:00:00.000000', '2018-10-10 00:00:00.000000', '300', '1', '1', '2456', '5', '2', '3');
INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('6', '3', '2018-9-4 00:00:00.000000', '10000', '2018-9-4 00:00:00.000000', '2018-9-4 00:00:00.000000', '2018-9-4 00:00:00.000000', '100', '1', '1', '10000', '6', '3', '4');
INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('7', '2', '2018-8-4 00:00:00.000000', '2000', '2018-8-4 00:00:00.000000', '2018-8-4 00:00:00.000000', '2018-8-4 00:00:00.000000', '20', '1', '1', '10000', '7', '4', '1');
INSERT INTO `dealermng`.`product_sale_info` (`id`, `customer_id`, `delivery_time`, `order_amount`, `order_finish_time`, `order_time`, `payment_time`, `product_nums`, `sale_payment_status_code`, `sale_status_code`, `total_price`, `trade_id`, `dealer_id`, `product_id`) VALUES ('8', '3', '2018-12-21 00:00:00.000000', '20000', '2018-12-21 00:00:00.000000', '2018-12-21 00:00:00.000000', '2018-12-21 00:00:00.000000', '50', '1', '1', '20000', '8', '5', '4');

-- 集团仓库表
INSERT INTO `dealermng`.`group_warehouse` (`id`, `address`, `warehouse_name`, `warehouse_no`) VALUES ('1', '杭州下沙', '杭州第一仓库', '1');


-- 集团库存信息表
INSERT INTO `dealermng`.`group_inventory` (`id`, `max_stocks`, `min_stocks`, `group_warehouse_id`, `product_id`) VALUES ('2', '300', '30', '1', '2');
INSERT INTO `dealermng`.`group_inventory` (`id`, `max_stocks`, `min_stocks`,  `group_warehouse_id`, `product_id`) VALUES ('3', '300', '30', '1', '3');
INSERT INTO `dealermng`.`group_inventory` (`id`, `max_stocks`, `min_stocks`, `group_warehouse_id`, `product_id`) VALUES ('4', '50', '6', '1', '4');


