INSERT INTO product(categories, description, name, no, price, stock, version) VALUES("电脑桌","放电脑的桌子","FUN科技电脑桌","YK070005B",233.33,230,1);

INSERT INTO product(categories, description, name, no, price, stock, version) VALUES("电脑桌","放电脑的桌子","极客湾的电脑桌","YG073005B",250.33,200,1);
-- insert into product(categories,description, name,no,price, stock,version) values("categories","description","名字","YK070005B",12.34,233,1);

-- 产品详情图片表
INSERT into detail_images(images_order,id, images_url) VALUES(0,1,"https://img.alicdn.com/imgextra/i1/355159670/TB2dsDQBH1YBuNjSszeXXablFXa_!!355159670.jpg");
INSERT into detail_images(images_order,id, images_url) VALUES(1,1,"https://img.alicdn.com/imgextra/i1/355159670/TB2t_TnCv1TBuNjy0FjXXajyXXa_!!355159670.jpg");
INSERT into detail_images(images_order,id, images_url) VALUES(2,1,"https://img.alicdn.com/imgextra/i4/355159670/TB2WXYgfKEJL1JjSZFGXXa6OXXa_!!355159670.jpg");

-- 产品轮播图片表
INSERT into carousel_images(images_order,id, images_url) VALUES(0,1,"https://gd4.alicdn.com/imgextra/i1/355159670/TB2dsDQBH1YBuNjSszeXXablFXa_!!355159670.jpg_400x400.jpg");
INSERT into carousel_images(images_order,id, images_url) VALUES(1,1,"http://gd1.alicdn.com/imgextra/i1/355159670/TB2_J61BN9YBuNjy0FfXXXIsVXa_!!355159670.jpg");
INSERT into carousel_images(images_order,id, images_url) VALUES(2,1,"http://gd1.alicdn.com/imgextra/i1/355159670/TB2yzHhB49YBuNjy0FfXXXIsVXa_!!355159670.jpg");

-- test data dealer infomation insert
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('1', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'female', 'Chaos', 'VIP至尊尊享', '12580', '2008-12-26 00:00:00.000000');
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('2', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'female', 'Chaos', 'VIP至尊尊享', '1258', '2008-12-26 00:00:00.000000');
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('3', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'male', 'Chaos', 'VIP至尊尊享', '125', '2008-12-26 00:00:00.000000');
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('4', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'male', 'Chaos', 'VIP至尊尊享', '12', '2008-12-26 00:00:00.000000');
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('5', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'female', 'Chaos', 'VIP至尊尊享', '1', '2008-12-26 00:00:00.000000');
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('6', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'male', 'Chaos', 'VIP至尊尊享', '125801', '2008-12-26 00:00:00.000000');
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('7', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'female', 'Chaos', 'VIP至尊尊享', '125802', '2008-12-26 00:00:00.000000';)
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('8', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'female', 'Chaos', 'VIP至尊尊享', '125803', '2008-12-26 00:00:00.000000');
INSERT into dealer(id,address,area,credit,expired_at,gender,name,note,phone,register_at) VALUES('9', '杭州下沙', '浙江', '1', '2008-12-13 00:00:00.000000', 'male', 'Chaos', 'VIP至尊尊享', '125804', '2008-12-26 00:00:00.000000');