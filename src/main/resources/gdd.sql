-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.22-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 gdd 的数据库结构
DROP DATABASE IF EXISTS `gdd`;
CREATE DATABASE IF NOT EXISTS `gdd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gdd`;

-- 导出  表 gdd.cover 结构
DROP TABLE IF EXISTS `cover`;
CREATE TABLE IF NOT EXISTS `cover` (
  `id` varchar(36) NOT NULL COMMENT '编号,uuid',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cover_type_id` varchar(36) DEFAULT NULL COMMENT '外键编号,uuid',
  `cover_type_name` varchar(36) DEFAULT NULL COMMENT '外键名称',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `introduce` varchar(50) DEFAULT NULL COMMENT '说明',
  `src` varchar(500) NOT NULL COMMENT '下载地址',
  `link` varchar(100) DEFAULT NULL COMMENT '外链',
  `isActive` int(1) NOT NULL DEFAULT '0' COMMENT '是否激活,0屏蔽,1激活',
  PRIMARY KEY (`id`),
  KEY `FK_cover_cover_type` (`cover_type_id`),
  CONSTRAINT `FK_cover_cover_type` FOREIGN KEY (`cover_type_id`) REFERENCES `cover_type` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='封面信息';

-- 正在导出表  gdd.cover 的数据：~0 rows (大约)
DELETE FROM `cover`;
/*!40000 ALTER TABLE `cover` DISABLE KEYS */;
/*!40000 ALTER TABLE `cover` ENABLE KEYS */;

-- 导出  表 gdd.cover_type 结构
DROP TABLE IF EXISTS `cover_type`;
CREATE TABLE IF NOT EXISTS `cover_type` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `parent_id` varchar(36) NOT NULL DEFAULT 'root' COMMENT '父级编号,root表示根节点',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  `node_level` int(11) NOT NULL COMMENT '节点级别,0表示根节点,1表示一级子节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='封面类别信息';

-- 正在导出表  gdd.cover_type 的数据：~9 rows (大约)
DELETE FROM `cover_type`;
/*!40000 ALTER TABLE `cover_type` DISABLE KEYS */;
INSERT INTO `cover_type` (`id`, `create_time`, `parent_id`, `name`, `node_level`) VALUES
	('495885db-c0e6-4c4d-a967-969faa61e1e2', '2018-07-15 21:53:09', 'root', '头像', 0),
	('628d7ecc-73e2-454c-8f8c-11b826ce15f8', '2018-07-15 21:54:59', 'ff032041-15ec-4ff7-a7bf-c48dc21f3ca1', '犬类', 1),
	('71fd9743-7803-4bc2-a0e8-bf0ded2b9ec3', '2018-07-15 21:58:58', '628d7ecc-73e2-454c-8f8c-11b826ce15f8', '汪星人', 2),
	('8b0fcda0-d0cf-4401-96cf-d5d1faf79e22', '2018-07-15 21:59:32', 'e9177e1e-9315-4610-9cf9-47ba80f60881', '喵粮', 2),
	('a3740752-c8c8-47ec-9467-19698a61bea5', '2018-07-15 21:59:25', 'e9177e1e-9315-4610-9cf9-47ba80f60881', '喵星人', 2),
	('c3d77fe3-40a9-45c3-95c6-8513f2a5913b', '2018-07-15 21:49:54', 'root', '轮播图', 0),
	('e9177e1e-9315-4610-9cf9-47ba80f60881', '2018-07-15 21:55:14', 'ff032041-15ec-4ff7-a7bf-c48dc21f3ca1', '猫类', 1),
	('fe28ed79-1de7-4d4f-b4c4-96d55c1f60b0', '2018-07-15 21:59:07', '628d7ecc-73e2-454c-8f8c-11b826ce15f8', '犬粮', 2),
	('ff032041-15ec-4ff7-a7bf-c48dc21f3ca1', '2018-07-15 21:52:39', 'root', '商品', 0);
/*!40000 ALTER TABLE `cover_type` ENABLE KEYS */;

-- 导出  表 gdd.email_code 结构
DROP TABLE IF EXISTS `email_code`;
CREATE TABLE IF NOT EXISTS `email_code` (
  `id` varchar(36) NOT NULL COMMENT '编号,UUID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `type` varchar(50) NOT NULL COMMENT '验证类型（0:忘记密码模块）',
  `sender` varchar(50) NOT NULL COMMENT '发送者邮箱',
  `receiver` varchar(50) NOT NULL COMMENT '接收者邮箱',
  `subject` varchar(225) NOT NULL COMMENT '邮件主题',
  `content` varchar(225) NOT NULL COMMENT '邮件内容',
  `code` varchar(4) NOT NULL COMMENT '验证码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通过邮箱获取验证码';

-- 正在导出表  gdd.email_code 的数据：~0 rows (大约)
DELETE FROM `email_code`;
/*!40000 ALTER TABLE `email_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_code` ENABLE KEYS */;

-- 导出  表 gdd.goods 结构
DROP TABLE IF EXISTS `goods`;
CREATE TABLE IF NOT EXISTS `goods` (
  `id` varchar(36) NOT NULL COMMENT '编号,uuid',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `goods_type_id` varchar(36) DEFAULT NULL COMMENT '外键编号',
  `goods_type_name` varchar(20) DEFAULT NULL COMMENT '外键名称',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `introduce` varchar(50) DEFAULT NULL COMMENT '介绍',
  `price` double NOT NULL COMMENT '价格',
  `promotion_price` double DEFAULT NULL COMMENT '促销价格',
  `isActive` int(1) NOT NULL DEFAULT '0' COMMENT '是否激活,0屏蔽1激活',
  PRIMARY KEY (`id`),
  KEY `FK_goods_goods_type` (`goods_type_id`),
  CONSTRAINT `FK_goods_goods_type` FOREIGN KEY (`goods_type_id`) REFERENCES `goods_type` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

-- 正在导出表  gdd.goods 的数据：~0 rows (大约)
DELETE FROM `goods`;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

-- 导出  表 gdd.goods_type 结构
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE IF NOT EXISTS `goods_type` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `parent_id` varchar(36) NOT NULL DEFAULT 'root' COMMENT '父级编号,root表示根节点',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  `node_level` int(11) NOT NULL COMMENT '节点级别,0表示根节点,1表示一级子节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类别信息';

-- 正在导出表  gdd.goods_type 的数据：~0 rows (大约)
DELETE FROM `goods_type`;
/*!40000 ALTER TABLE `goods_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `goods_type` ENABLE KEYS */;

-- 导出  表 gdd.log 结构
DROP TABLE IF EXISTS `log`;
CREATE TABLE IF NOT EXISTS `log` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `req_url` varchar(500) DEFAULT NULL COMMENT '请求链接',
  `req_method` varchar(50) DEFAULT NULL COMMENT '请求方法',
  `req_args` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `controller_method` varchar(50) DEFAULT NULL COMMENT '控制层方法',
  `msg` varchar(5000) DEFAULT NULL COMMENT '日志信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='错误日志';

-- 正在导出表  gdd.log 的数据：~0 rows (大约)
DELETE FROM `log`;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;

-- 导出  表 gdd.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(36) NOT NULL COMMENT '编号,UUID字符串',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据生成时间',
  `cover` varchar(50) DEFAULT 'cover.jpg' COMMENT '头像地址链接',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `email` varchar(50) NOT NULL COMMENT '邮箱(一个邮箱只能注册一个用户),找回密码使用',
  `password` varchar(50) NOT NULL DEFAULT '1750637e26c7cad8c487824ad7f6337b' COMMENT '密码(gdd1234)(1750637e26c7cad8c487824ad7f6337b)',
  `introduce` varchar(225) DEFAULT NULL COMMENT '备注',
  `sex` varchar(50) DEFAULT '男' COMMENT '性别',
  `hobby` varchar(225) DEFAULT NULL COMMENT '爱好',
  `province` varchar(50) NOT NULL DEFAULT '0' COMMENT '省',
  `city` varchar(50) NOT NULL DEFAULT '-1' COMMENT '市',
  `area` varchar(50) NOT NULL DEFAULT '-1' COMMENT '区',
  `address` varchar(225) DEFAULT NULL COMMENT '详细地址',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登陆ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户信息';

-- 正在导出表  gdd.user 的数据：~0 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `create_time`, `cover`, `name`, `phone`, `email`, `password`, `introduce`, `sex`, `hobby`, `province`, `city`, `area`, `address`, `login_time`, `login_ip`) VALUES
	('77cfd433-c255-4978-aca2-efb453558eb8', '2018-07-06 19:58:09', 'cover.jpg', '李星', '18013896707', 'lixing_java@163.com', '1750637e26c7cad8c487824ad7f6337b', '该用户很懒什么都没有留下', '男', '篮球,足球,排球,皮球', '河北省', '承德市', '围场满族蒙古族自治县', '育群胡同21号', '2018-07-12 12:42:07', '127.0.0.1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
