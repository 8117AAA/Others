# Host: localhost  (Version: 5.7.26)
# Date: 2021-06-01 23:35:18
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "store_car"
#

CREATE TABLE `store_car` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `com_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品id',
  `num` int(11) NOT NULL DEFAULT '0' COMMENT '数量',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "store_commodity"
#

CREATE TABLE `store_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(255) NOT NULL DEFAULT '商品' COMMENT '商品名',
  `price` float NOT NULL DEFAULT '0' COMMENT '价格',
  `inventory` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `detail` text COMMENT '描述',
  `tsales` int(11) NOT NULL DEFAULT '0' COMMENT '总销量',
  `msales` int(11) NOT NULL DEFAULT '0' COMMENT '月销量',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Structure for table "store_order"
#

CREATE TABLE `store_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `comm_id` varchar(255) NOT NULL DEFAULT '',
  `price` float(16,2) NOT NULL DEFAULT '0.00',
  `create_time` datetime DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Structure for table "store_user"
#

CREATE TABLE `store_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL DEFAULT '0' COMMENT '电话',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '名字',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `profile_photo` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
