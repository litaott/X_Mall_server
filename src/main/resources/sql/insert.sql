/*
 插入数据库初始值
 */

use db_XMall_user;
-- 插入用户信息
insert into user_info(username, password, balance, phone_number, avatar, gender_index, age, follow_number, role_index,
                      create_time)
values ('aa', '123456', 5000.00, '12345678901', 'user_default.png', 1, 20, 3, 0, now()),
       ('bb', '123456', 1000.00, '12345678902', 'user_default.png', 2, 40, 0, 0, now()),
       ('cc', '123456', 0.00, '12345678903', 'user_default.png', 1, 22, 1, 0, now()),
       ('dd', '123456', 200.00, '12345678904', 'user_default.png', 1, 0, 0, 0, now()),
       ('ee', '123456', 0.00, '12345678905', 'user_default.png', 0, 25, 0, 0, now()),
       ('ff', '123456', 50.00, '12345678906', 'user_default.png', 2, 30, 2, 0, now()),
       ('gg', '123456', 0.00, '12345678907', 'user_default.png', 0, 0, 0, 0, now());

-- 插入地址信息
insert into address_info(user_id, address, receiver, phone_number, is_default)
values (100001, '北京市东城区东直门街道', '张三', '12345678901', true),
       (100002, '武汉市洪山区洪街街道', '吴青峰', '12345678903', true),
       (100002, '北京市西城区西直门街道', '李四', '12345678902', false),
       (100004, '长沙市雨花区雨花街道', '刘家凯','12345678904', true),
       (100004, '北京市西城区西直门街道', '史俊威','12345678905', false),
       (100004, '北京市东城区东直门街道', '谢馨仪','12345678906', false),
       (100006, '北京市西城区西直门街道', '龚钰祺','12345678907', true),
       (100007, '北京市东城区东直门街道', '何景扬','12345678908', true);

-- 插入购物车信息
insert into cart_info(user_id, goods_id, quantity)
values (100001, 1, 1),
       (100001, 2, 2),
       (100002, 1, 2),
       (100005, 3, 1),
       (100006, 1, 3),
       (100007, 4, 1);

-- 插入关注信息
insert into follow_info(user_id, store_id)
values (100001, 1001),
       (100001, 1002),
       (100001, 1003),
       (100003, 1001),
       (100006, 1004),
       (100006, 1005);

-- 插入浏览记录
insert into history_info(user_id, goods_id, time)
values (100001, 1, now()),
       (100001, 2, now()),
       (100001, 3, now()),
       (100001, 4, now()),
       (100002, 1, now()),
       (100002, 2, now()),
       (100002, 4, now()),
       (100003, 1, now()),
       (100003, 2, now());

use db_XMall_goods;
-- 插入商品信息
insert into goods_info(goods_name, store_id, price, quantity, sale_number, create_time)

