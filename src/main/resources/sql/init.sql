drop database if exists db_XMall_user;
create database db_XMall_user;
use db_XMall_user;
create table user_info (
                           id int not null primary key auto_increment,
                           password varchar(1023) not null,
                           username varchar(255) binary not null,
                           phone_number varchar(20),
                           avatar varchar(1023),
                           address set,
                           gender int not null comment '0:男,1:女,2:保密',
                           age int,
                           cart set comment '购物车（商品列表）',
                           orderList set,
                           role varchar(10) not null default 'USER' comment 'user:普通用户,admin:管理员',
                           create_time datetime not null,
                           del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '用户信息表' charset=utf8;

create table address_info (
                           id int not null primary key auto_increment,
                           address varchar(255) not null,
                           receiver varchar(255) not null,
                           phone_number varchar(20),
                           isDefault int not null comment '0:默认,1:非默认',
                           label enum('家','学校','公司'),
) comment '地址信息表' charset=utf8;

drop database if exists db_XMall_goods;
create database db_XMall_goods;
use db_XMall_goods;
create table goods_info (
                           id int not null primary key auto_increment,
                           goodsName varchar(1023) not null,
                           price float(255) not null,
                           quantity int,
                           images varchar(1023),
                           saled varchar(255) ,
                           comment set,
                           category varchar(10) not null default 'USER' comment 'user:普通用户,admin:管理员',
                           create_time datetime not null,
                           insurance varchar(255),
                           transportation varchar(255),
                           transPrice int,
                           prefId,
                           del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '商品信息表' charset=utf8;

create table comment_info (
                            id int not null primary key auto_increment,
                            senderId varchar(1023) not null,
                            message float(255) not null,
                            sendTime datetime not null,
) comment '评价信息表' charset=utf8;

drop database if exists db_XMall_store;
create database db_XMall_store;
use db_XMall_store;
create table store_info (
                            id int not null primary key auto_increment,
                            userId varchar(1023) not null,
                            storeName varchar(255) binary not null,
                            goodsList varchar(20),
                            reputation varchar(1023),
                            fans varchar(255), comment '可能多个'
                                gender int not null comment '0:男,1:女,2:保密',
                            image int,
                            create_time datetime not null,
                            del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '店铺信息表' charset=utf8;

drop database if exists db_XMall_order;
create database db_XMall_order;
use db_XMall_order;
create table order_info (
                            id int not null primary key auto_increment,
                            userId varchar(1023) not null,
                            storeId varchar(255) binary not null,
                            price varchar(20),
                            transPrice varchar(1023),
                            payTime varchar(255), comment '可能多个',
                            payWay enum('微信','支付宝'),
                            address int,
                            isPayed int not null default 0 comment '0:未支付,1:已支付',
                            isFinished int not null default 0 comment '0:未完成,1:已完成',
) comment '订单信息表' charset=utf8;

create table order_detail_info (
                            id int not null primary key auto_increment,
                            goodsName
                            price varchar(20),
                            quantity int,

) comment '订单信息表' charset=utf8;

drop database if exists db_XMall_communication;
create database db_XMall_communication;
use db_XMall_communication;
create table communication_info (
                                senderId varchar(1023) not null,
                                receiverId varchar(255) binary not null,
                                message varchar(20),
                                time varchar(1023),
                                isRead int,
) comment '聊天记录表' charset=utf8;

drop database if exists db_XMall_afterSale;
create database db_XMall_afterSale;
use db_XMall_afterSale;
create table afterSale_info (
                            id int not null primary key auto_increment,
                            userId varchar(1023) not null,
                            storeId varchar(255) binary not null,
                            orderId varchar(20),
                            result varchar(1023),
                            reason varchar(255),
                            time int,
                            isFinished datetime not null,
) comment '售后信息表' charset=utf8;

drop database if exists db_XMall_preference;
create database db_XMall_preference;
use db_XMall_preference;
create table preference_info (
                                id int not null primary key auto_increment,
                                category enum('无','降价','打折','赠品'),
                                prefId varchar(255) binary not null,
                                startTime int,
                                endTime int,
                                prefName varchar(255),
) comment '优惠信息表' charset=utf8;

create table reduction_info (
                               id int not null primary key auto_increment,
                               reduction varchar(255) binary not null,
) comment '降价信息表' charset=utf8;

create table discount_info (
                               id int not null primary key auto_increment,
                               discount varchar(255) binary not null,
) comment '打折信息表' charset=utf8;

create table gift_info (
                               id int not null primary key auto_increment,
                               gift varchar(255),
) comment '赠品信息表' charset=utf8;












/*
create table user_follower_info (
                                    id int not null auto_increment primary key,
                                    user_id int not null,
                                    follower_id int not null,
                                    follow_time datetime not null,
                                    del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '用户关注表' charset=utf8;
create index userId_follower_index on user_follower_info(user_id);
create index followerId_follower_index on user_follower_info(follower_id);

INSERT INTO user_info (password, username, phone_number, avatar, address, gender, age, signature, role, create_time, del_flag)
VALUES
    ('$2a$10$qDsDERjBgAnI02TJSquZAed.sb7Ak7VLGWbWY5ixtXkWNm2qH5JNm', 'lyh', '1234567890', 'alice_avatar.jpg', '北京', 1, 28, 'Nice to meet you!', 'USER', '2023-06-28 08:49:39', 0),
    ('$2a$10$qDsDERjBgAnI02TJSquZAed.sb7Ak7VLGWbWY5ixtXkWNm2qH5JNm', 'hzy', '9876543210', 'bob_avatar.jpg', '上海', 0, 32, 'Hello, I am Bob.', 'USER', '2023-06-28 08:49:39', 0),
    ('$2a$10$qDsDERjBgAnI02TJSquZAed.sb7Ak7VLGWbWY5ixtXkWNm2qH5JNm', 'Admin', '5555555555', 'admin_avatar.jpg', '武汉', 2, NULL, 'Welcome to the admin panel.', 'ADMIN', '2023-06-28 08:49:39', 0),
    ('$2a$10$qDsDERjBgAnI02TJSquZAed.sb7Ak7VLGWbWY5ixtXkWNm2qH5JNm','ww','1234567890','ww_avatar.jpg','苏州',2,NULL,'hhh','USER','2021-06-29 09:56:23',0);
INSERT INTO user_follower_info (user_id, follower_id, follow_time, del_flag)
VALUES
    (1, 2, '2023-06-28 10:00:00', 0),
    (1, 3, '2023-06-28 10:15:00', 0),
    (3, 2, '2023-06-28 10:30:00', 0),
    (2, 1, '2023-06-28 10:45:00', 0);

drop database if exists db_stellar_town_post;
create database db_stellar_town_post;
use db_stellar_town_post;
create table post_info (
                           id int not null primary key auto_increment,
                           user_id int not null,
                           image varchar(255),
                           title varchar(255),
                           content varchar(1024),
                           post_time datetime not null,
                           shot_time datetime,
                           address varchar(255),
                           like_count int not null default 0,
                           tag varchar(255),
                           del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '帖子信息表' charset=utf8;

create table post_follower_info (
                                    id int not null auto_increment primary key,
                                    post_id int not null,
                                    liker_id int not null,
                                    follow_time datetime not null,
                                    del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '帖子点赞表' charset=utf8;

INSERT INTO post_info (user_id, image, title, content, post_time, shot_time, address, like_count, tag, del_flag)
VALUES
    (1, 'image1.jpg', '第一篇帖子', '这是第一篇帖子的内容。', '2023-06-28 10:00:00', NULL, '北京', 0, '生活', 0),
    (2, 'image2.jpg', 'Second Post', 'This is the second post content.', '2023-06-28 11:00:00', NULL, '上海', 0, '新闻', 0),
    (3, 'image3.jpg', 'Third Post', 'This is the third post content.', '2023-06-28 12:00:00', NULL, '广州', 0, '娱乐', 0),
    (4, 'image4.jpg', 'Fourth Post', 'This is the fourth post content.', '2023-06-28 12:00:01', NULL, '日照', 0, '娱乐', 0);

INSERT INTO post_follower_info (post_id, liker_id, follow_time, del_flag)
VALUES
    (1, 1, '2023-06-28 10:00:00', 0),
    (2, 1, '2023-06-28 10:30:00', 0),
    (1, 2, '2023-06-28 10:15:00', 0),
    (2, 2, '2023-06-28 10:45:00', 0),
    (3, 2, '2023-06-28 10:45:00', 0),
    (1, 3, '2023-06-28 10:45:00', 0),
    (4, 3, '2023-06-28 10:30:00', 0);


drop database if exists  db_stellar_town_attraction;
create database db_stellar_town_attraction;
use db_stellar_town_attraction;
create table attraction_info(
                                id int not null auto_increment primary key,
                                name varchar(255),
                                introduction varchar(255),
                                address varchar(255),
                                image  varchar(255),
                                altitude varchar(255),
                                longitude varchar(255),
                                latitude varchar(255)
)comment '景点信息表' charset=utf8;
*/





