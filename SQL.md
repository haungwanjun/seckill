```mysql
-- 商品
create table `seckill`.`goods` (
    `id` bigint(20) not null auto_increment,
    `goods_name` varchar(16) default null comment '商品名称',
    `goods_title` varchar(64) default null comment '商品标题',
    `goods_img` varchar(64) default null comment '商品图片',
    `goods_detail` longtext comment '商品详情',
    `goods_price` decimal(10,2) default '0.00' comment '商品单价',
    `goods_stock` int default '0' comment '商品库存',
    `is_seckill` int default '0' comment '是否设为秒杀',
    primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- 秒杀用户
create table `seckill`.`seckill_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salt` varchar(10) DEFAULT NULL,
  `head` varchar(128) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `login_count` int(11) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- 订单详情
CREATE TABLE `seckill`.`order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `goods_name` varchar(45) DEFAULT NULL,
  `goods_count` int(11) DEFAULT NULL,
  `goods_price` decimal(10,2) DEFAULT NULL,
  `order_channel` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- 秒杀商品
CREATE TABLE `seckill`.`seckill_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT NULL,
  `seckill_price` decimal(10,2) DEFAULT '0.00',
  `stock_count` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- 秒杀订单
CREATE TABLE `seckill`.`seckill_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_uid_gid` (`user_id`,`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- 插入商品
INSERT INTO `seckill`.`goods`
(`id`,
`goods_name`,
`goods_title`,
`goods_img`,
`goods_detail`,
`goods_price`,
`goods_stock`,
`is_seckill`)
VALUES
(1,'iPhone XR','iPhone XR (A2108) 64GB 白色 移动联通电信4G手机','/img/AppleiphoneXR.jpg','iPhone XR (A2108) 64GB 白色 移动联通电信4G手机 双卡双待 ',5239,999,1),
(2,'HUAWEI nova4 ','华为nova4 手机3e 苏音蓝 6期免息','/img/huawei_nova4.jpg','华为nova4 手机3e 苏音蓝 6期免息 4800万超广角三摄8GB+128GB',3199,999,1),
(3,'小米8SE','小米8SE 全面屏智能游戏拍照手机 6GB+64GB','/img/xiaomi8SE.jpg','小米8SE 全面屏智能游戏拍照手机 6GB+64GB 灰色 骁龙710处理器 全网通4G 双卡双待',1399,999,1),
(4,'三星 Galaxy S8','三星 Galaxy S8 4GB+64GB 谜夜黑（SM-G9500）全视曲面屏 虹膜识别 全网通4G','/img/SamsungGalaxyS8.jpg','三星 Galaxy S8 4GB+64GB 谜夜黑（SM-G9500）全视曲面屏 虹膜识别 全网通4G 双卡双待',2888,999,1),
(5,'OPPO Reno','OPPO Reno 10倍变焦版 全面屏拍照手机 6G+256G 雾海绿 全网通','/img/oppoReno10.jpg','OPPO Reno 10倍变焦版 全面屏拍照手机 6G+256G 雾海绿 全网通 移动联通电信 双卡双待手机',5000,999,1);
SELECT * FROM seckill.goods;


```

