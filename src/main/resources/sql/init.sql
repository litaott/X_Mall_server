drop database if exists db_XMall_user;
create database db_XMall_user;
use db_XMall_user;
create table user_info
(
    user_id       int                 not null primary key auto_increment
        comment '用户id',
    password      varchar(20)         not null
        comment '密码',
    username      varchar(255) binary not null
        comment '用户名',
    is_login      boolean             not null default false
        comment '登录状态',
    balance       decimal(10, 2)      not null default 0.00
        comment '余额',
    phone_number  varchar(20)         not null
        comment '电话号码',
    avatar        varchar(1023)       not null default 'user_default.png'
        comment '头像',
    gender_index  int                 not null default 0
        comment '用户性别，0:未知 1:男 2:女',
    age           int
        comment '年龄',
    follow_number int                 not null default 0
        comment '关注店铺数',
    role_index    int                 not null default 0
        comment '用户角色，0:普通用户 1:管理员',
    create_time   datetime            not null
        comment '注册时间'
) comment '用户信息表' charset = utf8
                       auto_increment = 100001;

create table address_info
(
    address_id   int          not null primary key auto_increment
        comment '地址id',
    user_id      int          not null
        comment '所属用户id',
    address      varchar(255) not null
        comment '地址信息',
    receiver     varchar(255) not null
        comment '收货人',
    phone_number varchar(20)  not null
        comment '收货人号码',
    is_default   boolean      not null default true
        comment '默认标记',
    label_index  int
        comment '地址标签，0:家 1:学校 2:公司'
) comment '地址信息表' charset = utf8;

create table cart_info
(
    user_id  int not null
        comment '用户id',
    goods_id int not null
        comment '商品id',
    quantity int not null
        comment '商品数量',
    PRIMARY KEY (user_id, goods_id)
) comment '购物车信息表' charset = utf8;

create table follow_info
(
    user_id  int not null
        comment '用户id',
    store_id int not null
        comment '店铺id',
    PRIMARY KEY (user_id, store_id)
) comment '关注信息表' charset = utf8;

create table history_info
(
    user_id   int      not null
        comment '用户id',
    goods_id  int      not null
        comment '商品id',
    time      datetime not null
        comment '浏览时间',
    is_delete boolean  not null default false
        comment '删除标记',
    PRIMARY KEY (user_id, goods_id)
) comment '历史浏览信息表' charset = utf8;

drop database if exists db_XMall_goods;
create database db_XMall_goods;
use db_XMall_goods;
create table goods_info
(
    goods_id       int            not null primary key auto_increment
        comment '商品id',
    store_id       int            not null
        comment '所属店铺id',
    goods_name     varchar(20)    not null
        comment '商品名称',
    price          decimal(10, 2) not null
        comment '单价',
    quantity       int            not null
        comment '库存量',
    sale_number    int            not null default 0
        comment '当前销量',
    category_index int            not null default 0
        comment '商品类别，0:数码产品 1:家用电器',
    create_time    datetime       not null
        comment '上架时间',
    insurance      varchar(255)
        comment '售后标准',
    transportation varchar(20)
        comment '运输方式',
    trans_price    decimal(10, 2) not null default 0.00
        comment '运费'
) comment '商品信息表' charset = utf8;

create table comment_info
(
    comment_id int          not null primary key auto_increment
        comment '评论id',
    goods_id   int          not null
        comment '所属商品id',
    sender_id  int          not null
        comment '发送者id',
    message    varchar(255) not null
        comment '评论内容',
    point      int          not null
        comment '评分',
    send_time  datetime     not null
        comment '发送时间'
) comment '评价信息表' charset = utf8;

create table goods_image_info
(
    image_id  int           not null primary key auto_increment
        comment '商品图片id',
    goods_id  int           not null
        comment '所属商品id',
    image_url varchar(1023) not null
        comment '图片路径'
) comment '商品图片信息表' charset = utf8;

drop database if exists db_XMall_store;
create database db_XMall_store;
use db_XMall_store;
create table store_info
(
    store_id     int            not null primary key auto_increment
        comment '店铺id',
    store_name   varchar(10)    not null
        comment '店铺名称',
    password     varchar(20)    not null
        comment '密码',
    owner_name   varchar(10)    not null
        comment '经营者姓名',
    phone_number varchar(20)    not null
        comment '经营者号码',
    credit_id    varchar(20)    not null
        comment '经营者身份信息',
    reputation   decimal(2, 1)  not null default 5.0
        comment '店铺评级',
    revenue      decimal(10, 2) not null default 0.00
        comment '店铺总营收',
    fans_number  int            not null default 0
        comment '粉丝数',
    image        varchar(255)
        comment '展示图片',
    create_time  datetime       not null
        comment '创建时间'
) comment '店铺信息表' charset = utf8
                       auto_increment = 1001;

drop database if exists db_XMall_order;
create database db_XMall_order;
use db_XMall_order;
create table order_info
(
    order_id      int            not null primary key auto_increment
        comment '订单id',
    user_id       int            not null
        comment '买家id',
    store_id      int            not null
        comment '店铺id',
    total_price   decimal(10, 2) not null
        comment '总价格',
    trans_price   decimal(10, 2) not null default 0.00
        comment '运费',
    pay_time      datetime
        comment '支付时间',
    pay_way_index int
        comment '支付方式，0:支付宝 1:微信 2:余额',
    send_time     datetime
        comment '发货时间',
    receive_time  datetime
        comment '收货时间',
    finish_time   datetime
        comment '订单完成时间',
    address_id    int
        comment '收货地址id',
    status_index  int            not null default 0
        comment '订单状态，0:待付款 1:待发货 2:待收货 3:待完成 4:已完成'
) comment '订单信息表' charset = utf8;

create table order_item_info
(
    order_item_id int            not null primary key auto_increment
        comment '订单详情id',
    order_id      int            not null
        comment '所属订单id',
    goods_id      int            not null
        comment '商品id',
    price         decimal(10, 2) not null
        comment '商品单价',
    quantity      int            not null
        comment '商品数量'
) comment '订单商品信息表' charset = utf8;

drop database if exists db_XMall_communication;
create database db_XMall_communication;
use db_XMall_communication;
create table communication_info
(
    message_id  int           not null primary key auto_increment
        comment '消息id',
    sender_id   int           not null
        comment '发送者id',
    receiver_id int           not null
        comment '接收者id',
    message     varchar(1023) not null
        comment '消息内容',
    time        datetime      not null
        comment '发送时间',
    is_read     boolean       not null default false
        comment '已读标记'
) comment '聊天记录表' charset = utf8;

drop database if exists db_XMall_after_sale;
create database db_XMall_after_sale;
use db_XMall_after_sale;
create table after_sale_info
(
    after_sale_id  int      not null primary key auto_increment
        comment '售后订单id',
    user_id        int      not null
        comment '用户id',
    store_id       int      not null
        comment '店铺id',
    order_id       int      not null
        comment '订单id',
    goods_id       int      not null
        comment '商品id',
    category_index int      not null
        comment '售后类型，0:退货退款 1:换货 2:仅退款',
    reason         varchar(255)
        comment '申请售后原因',
    result_index   int
        comment '售后处理结果，0:同意申请 1:拒绝申请',
    start_time     datetime not null
        comment '开始时间',
    finish_time    datetime
        comment '结束时间',
    is_finish      boolean  not null default false
        comment '完成标记'
) comment '售后信息表' charset = utf8;

drop database if exists db_XMall_preference;
create database db_XMall_preference;
use db_XMall_preference;
create table preference_info
(
    preference_id  int      not null primary key auto_increment
        comment '优惠方案id',
    goods_id       int      not null
        comment '所属商品id',
    category_index int      not null
        comment '优惠类型，0:降价 1:折扣 2:赠品',
    pref_id        int      not null
        comment '优惠力度id',
    start_time     datetime not null
        comment '开始时间',
    end_time       datetime not null
        comment '结束时间',
    pref_name      varchar(255)
        comment '优惠活动名称'
) comment '优惠信息表' charset = utf8;

create table reduction_info
(
    id        int            not null primary key auto_increment
        comment '降价id',
    reduction decimal(10, 2) not null
        comment '降价金额'
) comment '降价信息表' charset = utf8;

create table discount_info
(
    id       int           not null primary key auto_increment
        comment '折扣id',
    discount decimal(2, 1) not null
        comment '折扣系数'
) comment '折扣信息表' charset = utf8;

create table gift_info
(
    id   int          not null primary key auto_increment
        comment '赠品id',
    gift varchar(255) not null
        comment '赠品信息'
) comment '赠品信息表' charset = utf8;





