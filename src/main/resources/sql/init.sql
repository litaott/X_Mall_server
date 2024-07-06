drop database if exists db_XMall_user;
create database db_XMall_user;
use db_XMall_user;
create table user_info
(
    id            int                 not null primary key auto_increment,
    password      varchar(1023)       not null,
    username      varchar(255) binary not null,
    is_login      int                 not null comment '0:未登录,1:已登录',
    phone_number  varchar(20),
    avatar        varchar(1023),
    gender        int                 not null default 2 comment '0:男,1:女,2:保密',
    age           int,
    follow_number int,
    role          enum ('普通用户','管理员')   default '普通用户',
    create_time   datetime            not null,
    del_flag      int                 not null default 0 comment '0:未删除,1:已删除'
) comment '用户信息表' charset = utf8;

create table address_info
(
    id           int          not null primary key auto_increment,
    user_id      int,
    address      varchar(255) not null,
    receiver     varchar(255) not null,
    phone_number varchar(20),
    is_default   int          not null comment '0:默认,1:非默认',
    label        enum ('家','学校','公司')
) comment '地址信息表' charset = utf8;

create table cart_info
(
    user_id  int not null,
    goods_id int not null,
    quantity int,
    PRIMARY KEY (user_id, goods_id)
) comment '购物车信息表' charset = utf8;

create table follow_info
(
    user_id  int not null,
    store_id int not null,
    PRIMARY KEY (user_id, store_id)
) comment '关注信息表' charset = utf8;

drop database if exists db_XMall_goods;
create database db_XMall_goods;
use db_XMall_goods;
create table goods_info
(
    id             int           not null primary key auto_increment,
    store_id       int,
    goods_name     varchar(1023) not null,
    price          float         not null,
    quantity       int,
    images         varchar(1023),
    sale_number    int,
    category       varchar(255),
    create_time    datetime      not null,
    insurance      varchar(255),
    transportation varchar(255),
    trans_price    float,
    del_flag       int           not null default 0 comment '0:未删除,1:已删除'
) comment '商品信息表' charset = utf8;

create table comment_info
(
    id        int      not null primary key auto_increment,
    goods_id  int,
    sender_id int,
    message   varchar(255),
    send_time datetime not null
) comment '评价信息表' charset = utf8;

drop database if exists db_XMall_store;
create database db_XMall_store;
use db_XMall_store;
create table store_info
(
    id           int      not null primary key auto_increment,
    store_name   varchar(10),
    password     varchar(20),
    owner_name   varchar(10),
    phone_number varchar(20),
    credit_id    varchar(20),
    reputation   float,
    fans_number  int,
    image        varchar(255),
    create_time  datetime not null,
    del_flag     int      not null default 0 comment '0:未删除,1:已删除'
) comment '店铺信息表' charset = utf8;

drop database if exists db_XMall_order;
create database db_XMall_order;
use db_XMall_order;
create table order_info
(
    id           int not null primary key auto_increment,
    user_id      int,
    store_id     int,
    total_price  float,
    trans_price  float,
    pay_time     datetime,
    pay_way      enum ('微信','支付宝'),
    send_time    datetime,
    receive_time datetime,
    finish_time  datetime,
    address      varchar(255),
    status       enum ('未支付','待发货','运输中','已到货','已完成')
) comment '订单信息表' charset = utf8;

create table order_detail_info
(
    id       int not null primary key auto_increment,
    order_id int,
    goods_id int,
    price    float,
    quantity int
) comment '订单信息表' charset = utf8;

drop database if exists db_XMall_communication;
create database db_XMall_communication;
use db_XMall_communication;
create table communication_info
(
    id          int not null primary key auto_increment,
    sender_id   int,
    receiver_id int,
    message     varchar(1023),
    time        datetime,
    is_read     int not null default 0 comment '0:未读,1:已读'
) comment '聊天记录表' charset = utf8;

drop database if exists db_XMall_after_sale;
create database db_XMall_after_sale;
use db_XMall_after_sale;
create table after_sale_info
(
    id          int not null primary key auto_increment,
    user_id     int,
    store_id    int,
    order_id    int,
    goods_id    int,
    category    enum ('仅退款','退货退款','换货'),
    reason      varchar(255),
    result      enum (
        '同意申请',
        '拒绝申请'
        ),
    start_time  datetime,
    finish_time datetime,
    is_finished int not null default 0 comment '0:未完成,1:已完成'
) comment '售后信息表' charset = utf8;

drop database if exists db_XMall_preference;
create database db_XMall_preference;
use db_XMall_preference;
create table preference_info
(
    id         int not null primary key auto_increment,
    category   enum ('降价','打折','赠品'),
    pref_id    int,
    start_time datetime,
    end_time   datetime,
    pref_name  varchar(255)
) comment '优惠信息表' charset = utf8;

create table reduction_info
(
    id        int not null primary key auto_increment,
    reduction float
) comment '降价信息表' charset = utf8;

create table discount_info
(
    id       int not null primary key auto_increment,
    discount float
) comment '打折信息表' charset = utf8;

create table gift_info
(
    id   int not null primary key auto_increment,
    gift varchar(255)
) comment '赠品信息表' charset = utf8;





