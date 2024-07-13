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
       (100004, '长沙市雨花区雨花街道', '刘家凯', '12345678904', true),
       (100004, '北京市西城区西直门街道', '史俊威', '12345678905', false),
       (100004, '北京市东城区东直门街道', '谢馨仪', '12345678906', false),
       (100006, '北京市西城区西直门街道', '龚钰祺', '12345678907', true),
       (100007, '北京市东城区东直门街道', '何景扬', '12345678908', true);

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
insert into goods_info(goods_name, store_id, price, trans_price, quantity, sale_number, create_time, category_index,
                       insurance, transportation)
values ('小米10', 1001, 5999.00, 0.00, 100, 0, now(), 0, null, null),
       ('小米10 Pro', 1001, 7999.00, 0.00, 100, 0, now(), 0, null, null),
       ('红米K50', 1001, 2999.00, 0.00, 100, 0, now(), 0, null, null),
       ('冰箱', 1002, 7999.00, 0.00, 100, 0, now(), 1, null, null),
       ('彩电', 1002, 7999.00, 0.00, 100, 0, now(), 1, null, null),
       ('联想笔记本', 1003, 7999.00, 0.00, 100, 0, now(), 0, null, null),
       ('洗衣机', 1004, 7999.00, 0.00, 100, 0, now(), 1, null, null),
       ('微波炉', 1004, 7999.00, 0.00, 100, 0, now(), 1, null, null),
       ('华为平板', 1005, 7999.00, 0.00, 100, 0, now(), 0, null, null);

-- 插入商品评论信息
insert into comment_info(goods_id, sender_id, message, send_time)
    values (1, 100001, '真好用', now()),
           (1, 100002, '孩子很喜欢', now()),
           (4, 100002, '敏感肌也能用', now()),
           (6, 100005, '邻居都馋疯了', now());

-- 插入商品图片信息

use db_xmall_store;
-- 插入店铺信息
insert into store_info(store_name, password, owner_name, phone_number, credit_id, reputation,
                       revenue, fans_number, image, create_time)
values ('小米商城', '123456', '雷军', '12345678901', 430, 5.0, 1000000, 2, 'store_default.png', now()),
       ('合家家电', '123456', '刘聪', '12345678902', 430, 4.8, 500000, 1, 'store_default.png', now()),
       ('联想电脑', '123456', '柳传志', '12345678903', 430, 4.9, 500000, 1, 'store_default.png', now()),
       ('全友家居', '123456', '全友', '12345678904', 430, 4.1, 1000000, 1, 'store_default.png', now()),
       ('华为商城', '123456', '任志强', '12345678905', 430, 4.8, 1000000, 1, 'store_default.png', now());

use db_xmall_order;
-- 插入订单信息
insert into order_info(user_id, store_id, total_price, trans_price, address_id, pay_time, pay_way_index, send_time,
                       receive_time, finish_time, status_index)
values (100001, 1001, 5999.00, 0.00, 100001, now(), 0, null, null, null, 0),
       (100003, 1004, 5999.00, 0.00, 100001, now(), 1, now(), now(), now(), 4);

-- 插入订单商品信息
insert into order_item_info(order_id, goods_id, quantity, price)
values (1, 1, 1, 5999.00),
       (2, 7, 1, 3999.00),
       (2, 8, 1, 2000.00);

use db_XMall_communication;
-- 插入聊天记录信息
insert into communication_info(sender_id, receiver_id, message, time)
values (100001, 1001, '你好', now()),
       (1001, 100001, '你好啊', now()),
       (100001, 1001, '再见', now());

use db_xmall_after_sale;
-- 插入售后信息
insert into after_sale_info(user_id, store_id, order_id, goods_id, category_index, reason, result_index, start_time,
                            finish_time, is_finish)
values (100003, 1004, 2, 7, 0, '不想要了', 0, now(), now(), true);

use db_xmall_preference;
-- 插入优惠信息
insert into preference_info(goods_id, category_index, pref_name, pref_id, start_time, end_time)
values (1, 0, '立减10元', 1, now(), now());

-- 插入降价信息
insert into reduction_info(reduction)
values (10.00);