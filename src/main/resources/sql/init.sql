drop database if exists db_XMall_user;
create database db_XMall_user;
use db_XMall_user;
create table user_info
(
    user_id            int                 not null primary key auto_increment
        comment '用户id',
    password      varchar(20)       not null
        comment '密码',
    username      varchar(255) binary not null
        comment '用户名',
    is_login      int                 not null
        comment '登陆状态；0:未登录,1:已登录',
    phone_number  varchar(20)
        comment '电话号码',
    avatar        varchar(1023)
        comment '头像',
    gender        int                 not null default 2
        comment '性别；0:男,1:女,2:保密',
    age           int
        comment '年龄',
    follow_number int
        comment '关注店铺数',
    role          enum ('普通用户','管理员')   default '普通用户'
        comment '账号角色',
    create_time   datetime            not null
        comment '注册时间',
    del_flag      int                 not null default 0
        comment '删除标记；0:未删除,1:已删除'
) comment '用户信息表' charset = utf8;

create table address_info
(
    address_id           int          not null primary key auto_increment
        comment '地址id',
    user_id      int
        comment '所属用户id',
    address      varchar(255) not null
        comment '地址信息',
    receiver     varchar(255) not null
        comment '收货人',
    phone_number varchar(20)
        comment '收货人号码',
    is_default   int          not null
        comment '默认标记；0:默认,1:非默认',
    label        enum ('家','学校','公司')
        comment '地址标签'
) comment '地址信息表' charset = utf8;

create table cart_info
(
    user_id  int not null
        comment '用户id',
    goods_id int not null
        comment '商品id',
    quantity int
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

drop database if exists db_XMall_goods;
create database db_XMall_goods;
use db_XMall_goods;
create table goods_info
(
    goods_id             int           not null primary key auto_increment
        comment '商品id',
    store_id       int
        comment '所属店铺id',
    goods_name     varchar(1023) not null
        comment '商品名称',
    price          float         not null
        comment '单价',
    quantity       int
        comment '库存量',
    images         varchar(1023)
        comment '展示图片',
    sale_number    int
        comment '当前销量',
    category       varchar(255)
        comment '分类',
    create_time    datetime      not null
        comment '上架时间',
    insurance      varchar(255)
        comment '售后标准',
    transportation varchar(255)
        comment '运输方式',
    trans_price    float
        comment '运费',
    del_flag       int           not null default 0
        comment '删除标记；0:未删除,1:已删除'
) comment '商品信息表' charset = utf8;

create table comment_info
(
    comment_id        int      not null primary key auto_increment
        comment '评论id',
    goods_id  int
        comment '所属商品id',
    sender_id int
        comment '发送者id',
    message   varchar(255)
        comment '评论内容',
    send_time datetime not null
        comment '发送时间'
) comment '评价信息表' charset = utf8;

drop database if exists db_XMall_store;
create database db_XMall_store;
use db_XMall_store;
create table store_info
(
    store_id           int      not null primary key auto_increment
        comment '店铺id',
    store_name   varchar(10)
        comment '店铺名称',
    password     varchar(20)
        comment '密码',
    owner_name   varchar(10)
        comment '经营者姓名',
    phone_number varchar(20)
        comment '经营者号码',
    credit_id    varchar(20)
        comment '经营者身份信息',
    reputation   float
        comment '店铺评级',
    fans_number  int
        comment '粉丝数',
    image        varchar(255)
        comment '展示图片',
    create_time  datetime not null
        comment '创建时间',
    del_flag     int      not null default 0
        comment '删除标记；0:未删除,1:已删除'
) comment '店铺信息表' charset = utf8;

drop database if exists db_XMall_order;
create database db_XMall_order;
use db_XMall_order;
create table order_info
(
    order_id           int not null primary key auto_increment
        comment '订单id',
    user_id      int
        comment '买家id',
    store_id     int
        comment '店铺id',
    total_price  float
        comment '总价格',
    trans_price  float
        comment '运费',
    pay_time     datetime
        comment '支付时间',
    pay_way      enum ('微信','支付宝')
        comment '支付方式',
    send_time    datetime
        comment '发货时间',
    receive_time datetime
        comment '收货时间',
    finish_time  datetime
        comment '订单完成时间',
    address_id   int
        comment '收货地址id',
    status       enum ('未支付','待发货','运输中','已到货','已完成')
        comment '当前订单状态'
) comment '订单信息表' charset = utf8;

create table order_detail_info
(
    order_detail_id       int not null primary key auto_increment
        comment '订单详情id',
    order_id int
        comment '所属订单id',
    goods_id int
        comment '商品id',
    price    float
        comment '商品单价',
    quantity int
        comment '商品数量'
) comment '订单商品信息表' charset = utf8;

drop database if exists db_XMall_communication;
create database db_XMall_communication;
use db_XMall_communication;
create table communication_info
(
    message_id          int not null primary key auto_increment
        comment '消息id',
    sender_id   int
        comment '发送者id',
    receiver_id int
        comment '接收者id',
    message     varchar(1023)
        comment '消息内容',
    time        datetime
        comment '发送时间',
    is_read     int not null default 0
        comment '已读标记；0:未读,1:已读'
) comment '聊天记录表' charset = utf8;

drop database if exists db_XMall_after_sale;
create database db_XMall_after_sale;
use db_XMall_after_sale;
create table after_sale_info
(
    after_sale_id          int not null primary key auto_increment
        comment '售后订单id',
    user_id     int
        comment '用户id',
    store_id    int
        comment '店铺id',
    order_id    int
        comment '订单id',
    goods_id    int
        comment '商品id',
    category    enum ('仅退款','退货退款','换货')
        comment '售后类型',
    reason      varchar(255)
        comment '申请售后原因',
    result      enum ('同意申请','拒绝申请')
        comment '售后处理结果',
    start_time  datetime
        comment '开始时间',
    finish_time datetime
        comment '结束时间',
    is_finished int not null default 0
        comment '完成标记；0:未完成,1:已完成'
) comment '售后信息表' charset = utf8;

drop database if exists db_XMall_preference;
create database db_XMall_preference;
use db_XMall_preference;
create table preference_info
(
    preference_id         int not null primary key auto_increment
        comment '优惠方案id',
    category   enum ('降价','打折','赠品')
        comment '优惠类型',
    pref_id    int
        comment '优惠力度id',
    start_time datetime
        comment '开始时间',
    end_time   datetime
        comment '结束时间',
    pref_name  varchar(255)
        comment '优惠活动名称'
) comment '优惠信息表' charset = utf8;

create table reduction_info
(
    id        int not null primary key auto_increment
        comment '降价id',
    reduction float
        comment '降价金额'
) comment '降价信息表' charset = utf8;

create table discount_info
(
    id       int not null primary key auto_increment
        comment '打折id',
    discount float
        comment '打折系数'
) comment '打折信息表' charset = utf8;

create table gift_info
(
    id   int not null primary key auto_increment
        comment '赠品id',
    gift varchar(255)
        comment '赠品信息'
) comment '赠品信息表' charset = utf8;





