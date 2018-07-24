-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
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
  `cover_type_name` varchar(36) NOT NULL COMMENT '封面类型名称',
  `is_active` varchar(2) NOT NULL DEFAULT '屏蔽' COMMENT '默认为屏蔽',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `src` varchar(500) NOT NULL COMMENT '下载地址',
  `introduce` varchar(50) DEFAULT NULL COMMENT '说明',
  `href` varchar(100) DEFAULT NULL COMMENT '外链地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='封面信息';

-- 正在导出表  gdd.cover 的数据：~4 rows (大约)
DELETE FROM `cover`;
/*!40000 ALTER TABLE `cover` DISABLE KEYS */;
INSERT INTO `cover` (`id`, `create_time`, `cover_type_name`, `is_active`, `name`, `src`, `introduce`, `href`) VALUES
	('02443ee6-d227-42a3-9008-ca32ab018ee1', '2018-07-24 12:13:17', '轮播图', '激活', 'h6.jpg', 'http://pc9o5rve4.bkt.clouddn.com/Fgoh5ZrlTeDFDu_RaXUVXNqBtXA5', '轮播图', 'http://pc9o5rve4.bkt.clouddn.com/Fgoh5ZrlTeDFDu_RaXUVXNqBtXA5'),
	('9307aed0-fab9-4e2a-b3ea-aa44295aeefd', '2018-07-24 12:13:17', '轮播图', '激活', 'h7.jpg', 'http://pc9o5rve4.bkt.clouddn.com/Fl4DOnf147AMl9czmYYbHoHXT8P9', NULL, NULL),
	('a348d4fa-040a-4a6b-92a6-049602e33aa7', '2018-07-24 12:13:17', '轮播图', '激活', 'h8.jpg', 'http://pc9o5rve4.bkt.clouddn.com/FnEt_QlUTlT4m2Uw_jl7JBdA5NmO', NULL, NULL),
	('d86a96b1-4f48-45e4-b5e2-3424289cc5a0', '2018-07-24 12:13:17', '轮播图', '激活', 'h5.jpg', 'http://pc9o5rve4.bkt.clouddn.com/FtIDT_2XKBh-sCqT31cC7JMooXhc', NULL, NULL);
/*!40000 ALTER TABLE `cover` ENABLE KEYS */;

-- 导出  表 gdd.cover_type 结构
DROP TABLE IF EXISTS `cover_type`;
CREATE TABLE IF NOT EXISTS `cover_type` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `parent_id` varchar(36) NOT NULL DEFAULT 'root' COMMENT '父级编号,root表示根节点',
  `name` varchar(20) NOT NULL COMMENT '类别名称',
  `node_level` int(11) NOT NULL COMMENT '节点级别,0表示根节点,1表示一级子节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='封面类别信息';

-- 正在导出表  gdd.cover_type 的数据：~24 rows (大约)
DELETE FROM `cover_type`;
/*!40000 ALTER TABLE `cover_type` DISABLE KEYS */;
INSERT INTO `cover_type` (`id`, `create_time`, `parent_id`, `name`, `node_level`) VALUES
	('0831986d-b7aa-459f-b63b-1823a4324d4b', '2018-07-24 09:49:01', '724bec51-a780-43a0-bb2d-9c886561c539', '喵类', 1),
	('19b171d0-29ac-4cfb-9bb8-acf65c7e94bc', '2018-07-24 09:51:47', 'f1dbc6e2-7592-465b-9a6f-d3910ebac6d6', '青霉素', 2),
	('217144af-fb5f-43cb-b698-be3ddc006d58', '2018-07-24 09:48:53', '724bec51-a780-43a0-bb2d-9c886561c539', '犬类', 1),
	('337c5ef0-ed89-40df-803d-76bf0deb3784', '2018-07-24 10:54:38', '217144af-fb5f-43cb-b698-be3ddc006d58', '金毛', 2),
	('3e8d7db4-a8f3-4552-bd21-9ebabfc68b11', '2018-07-24 10:56:21', '217144af-fb5f-43cb-b698-be3ddc006d58', '杜宾犬', 2),
	('724bec51-a780-43a0-bb2d-9c886561c539', '2018-07-24 09:48:26', 'root', '商品', 0),
	('76b01b20-449c-4a91-ae1e-3f592dec995d', '2018-07-24 09:24:40', 'root', '头像', 0),
	('794598c8-b3ba-4842-aec0-43e93602e3df', '2018-07-24 09:51:05', '217144af-fb5f-43cb-b698-be3ddc006d58', '法斗', 2),
	('7e022e7e-0878-4e14-99b4-bb4072e80649', '2018-07-24 10:55:07', '217144af-fb5f-43cb-b698-be3ddc006d58', '边牧', 2),
	('8513019f-a4b8-4f0a-8089-d41a8c1399f2', '2018-07-24 09:51:15', '0831986d-b7aa-459f-b63b-1823a4324d4b', '波斯喵', 2),
	('8c4ab926-8a61-4033-b790-62e8ddf344ac', '2018-07-24 09:52:03', 'ed616d0b-40f0-4b5c-a52e-90ea032feb65', '笼子', 2),
	('95af9f3e-98ef-4473-8f53-642714cd632f', '2018-07-24 10:54:45', '217144af-fb5f-43cb-b698-be3ddc006d58', '泰迪', 2),
	('9c450d45-f864-4907-b199-a9f8422958aa', '2018-07-24 10:56:57', '0831986d-b7aa-459f-b63b-1823a4324d4b', '暹罗猫', 2),
	('9cc76536-f6c3-4d08-8623-9d5bc14b2e37', '2018-07-24 09:24:51', 'root', '轮播图', 0),
	('9d03497a-1b2c-4b7f-aee6-106d8d77b9ac', '2018-07-24 10:54:14', 'f1dbc6e2-7592-465b-9a6f-d3910ebac6d6', '消炎药', 2),
	('a422dada-8130-4d1d-95e6-7792b6ddcc38', '2018-07-24 10:56:10', '217144af-fb5f-43cb-b698-be3ddc006d58', '贵宾犬', 2),
	('bbf18f16-d76e-4d5f-a5d0-84673d5a4498', '2018-07-24 09:49:11', '724bec51-a780-43a0-bb2d-9c886561c539', '食品', 1),
	('bef3131c-6e39-475e-9907-9821579f987b', '2018-07-24 09:49:21', '217144af-fb5f-43cb-b698-be3ddc006d58', '拉布拉多', 2),
	('d5b7e69a-c0f2-4ed8-91c9-a81216aa306a', '2018-07-24 09:51:25', 'bbf18f16-d76e-4d5f-a5d0-84673d5a4498', '牛油果', 2),
	('ddbf5b7c-f6a1-496c-b452-841ed9af3a10', '2018-07-24 10:55:42', '217144af-fb5f-43cb-b698-be3ddc006d58', '吉娃娃', 2),
	('e7a1d7f6-82b3-4034-9a3a-45f4db896efe', '2018-07-24 10:54:50', '217144af-fb5f-43cb-b698-be3ddc006d58', '柴犬', 2),
	('ea370f19-ebee-4d6e-b72c-232128e4da02', '2018-07-24 10:56:00', '217144af-fb5f-43cb-b698-be3ddc006d58', '哈士奇', 2),
	('ed616d0b-40f0-4b5c-a52e-90ea032feb65', '2018-07-24 09:51:57', '724bec51-a780-43a0-bb2d-9c886561c539', '用品', 1),
	('f1dbc6e2-7592-465b-9a6f-d3910ebac6d6', '2018-07-24 09:51:38', '724bec51-a780-43a0-bb2d-9c886561c539', '药物', 1);
/*!40000 ALTER TABLE `cover_type` ENABLE KEYS */;

-- 导出  表 gdd.email_code 结构
DROP TABLE IF EXISTS `email_code`;
CREATE TABLE IF NOT EXISTS `email_code` (
  `id` varchar(36) NOT NULL COMMENT '编号,UUID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `code_type` varchar(50) NOT NULL COMMENT '验证类型（0:忘记密码模块）',
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
  `goods_type_name` varchar(20) NOT NULL COMMENT '商品类型名称',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `introduce` varchar(50) DEFAULT NULL COMMENT '介绍',
  `price` double NOT NULL COMMENT '价格',
  `promotion_price` double DEFAULT NULL COMMENT '促销价格',
  `isActive` int(1) NOT NULL DEFAULT '0' COMMENT '是否激活,0屏蔽1激活',
  PRIMARY KEY (`id`)
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
  `name` varchar(20) NOT NULL COMMENT '类别名称',
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

-- 正在导出表  gdd.log 的数据：~9 rows (大约)
DELETE FROM `log`;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` (`id`, `create_time`, `req_url`, `req_method`, `req_args`, `controller_method`, `msg`) VALUES
	('19f0e17e-673c-4b98-8e05-dc53ac321604', '2018-07-19 16:59:15', 'http://localhost:8080/gdd/file/importCover', 'POST', 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@274ee41c', 'snoob.gdd.controller.FileController.importCover', 'Invalid header signature; read 0x464A1000E0FFD8FF, expected 0xE11AB1A1E011CFD0 - Your file appears not to be a valid OLE2 document'),
	('1c820b31-d9ce-4a5e-bbf5-9d37afecfb5d', '2018-07-19 14:24:39', 'http://localhost:8080/gdd/file/importUser', 'POST', 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@51044c14', 'snoob.gdd.controller.FileController.importUser', '### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'lixing_java@163.com\' for key \'email\'\r\n### The error may involve snoob.gdd.mapper.UserMapper.customInsert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into user(id, name, sex, phone,email,hobby,province,city,area,address,create_time) values                        (?,?,?,?,?,?,?,?,?,?,?)\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'lixing_java@163.com\' for key \'email\'\n; ]; Duplicate entry \'lixing_java@163.com\' for key \'email\'; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'lixing_java@163.com\' for key \'email\''),
	('2bf1ccc8-f22b-49bb-badf-714aa3e8920c', '2018-07-19 14:29:05', 'http://localhost:8080/gdd/file/importUser', 'POST', 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@20e199f7', 'snoob.gdd.controller.FileController.importUser', '### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'lixing_java@163.com\' for key \'email\'\r\n### The error may involve snoob.gdd.mapper.UserMapper.customInsert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into user(id, name, sex, phone,email,hobby,province,city,area,address,create_time) values                        (?,?,?,?,?,?,?,?,?,?,?)\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'lixing_java@163.com\' for key \'email\'\n; ]; Duplicate entry \'lixing_java@163.com\' for key \'email\'; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'lixing_java@163.com\' for key \'email\''),
	('63cb5981-e07c-4f88-8138-d4836b07f6ac', '2018-07-24 09:12:51', 'http://localhost:8080/gdd/cover/deleteCoverType', 'POST', 'c3d77fe3-40a9-45c3-95c6-8513f2a5913b,ff032041-15ec-4ff7-a7bf-c48dc21f3ca1,628d7ecc-73e2-454c-8f8c-11b826ce15f8,71fd9743-7803-4bc2-a0e8-bf0ded2b9ec3,fe28ed79-1de7-4d4f-b4c4-96d55c1f60b0,e9177e1e-9315-4610-9cf9-47ba80f60881,a3740752-c8c8-47ec-9467-19698a61bea5,8b0fcda0-d0cf-4401-96cf-d5d1faf79e22,495885db-c0e6-4c4d-a967-969faa61e1e2', 'snoob.gdd.controller.CoverController.delete', 'Invalid bound statement (not found): snoob.gdd.mapper.CoverTypeMapper.customDelete'),
	('683b424b-d7ff-436b-a156-9b3dae6c7f32', '2018-07-22 22:18:56', 'http://localhost:8080/gdd/cover/modifyCoverType', 'POST', 'snoob.gdd.model.CoverType@29a3597c', 'snoob.gdd.controller.CoverController.modify', '/ by zero'),
	('696e9bfe-3065-44f2-aa4c-e57156cf6198', '2018-07-19 14:23:08', 'http://localhost:8080/gdd/file/importUser', 'POST', 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@1b17595a', 'snoob.gdd.controller.FileController.importUser', '### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may involve snoob.gdd.mapper.UserMapper.customInsert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into user(id, name, sex, phone,email,hobby,province,city,area,address,create_time) values\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1'),
	('8214d03d-e73e-45fc-9229-83bc1ec2395e', '2018-07-24 09:11:09', 'http://localhost:8080/gdd/cover/deleteCoverType', 'POST', 'c3d77fe3-40a9-45c3-95c6-8513f2a5913b,ff032041-15ec-4ff7-a7bf-c48dc21f3ca1,628d7ecc-73e2-454c-8f8c-11b826ce15f8,71fd9743-7803-4bc2-a0e8-bf0ded2b9ec3,fe28ed79-1de7-4d4f-b4c4-96d55c1f60b0,e9177e1e-9315-4610-9cf9-47ba80f60881,a3740752-c8c8-47ec-9467-19698a61bea5,8b0fcda0-d0cf-4401-96cf-d5d1faf79e22,495885db-c0e6-4c4d-a967-969faa61e1e2', 'snoob.gdd.controller.CoverController.delete', 'Invalid bound statement (not found): snoob.gdd.mapper.CoverTypeMapper.customDelete'),
	('8bd1b65a-6330-4af2-bebc-f4e8257249e1', '2018-07-19 14:24:13', 'http://localhost:8080/gdd/file/importUser', 'POST', 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@486e9cb6', 'snoob.gdd.controller.FileController.importUser', '### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may involve snoob.gdd.mapper.UserMapper.customInsert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into user(id, name, sex, phone,email,hobby,province,city,area,address,create_time) values\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1'),
	('e2582be6-53db-4e52-88a2-3d54303b3bcd', '2018-07-22 22:16:36', 'http://localhost:8080/gdd/cover/modifyCoverType', 'POST', 'snoob.gdd.model.CoverType@5a6603a', 'snoob.gdd.controller.CoverController.modify', '/ by zero');
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
  `sex` varchar(1) DEFAULT '男' COMMENT '性别',
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
	('7b95ffa8-f777-4a88-98c1-add44d608894', '2018-07-06 19:58:09', 'cover.jpg', '李星', '18013896707', 'lixing_java@163.com', '1750637e26c7cad8c487824ad7f6337b', NULL, '男', '篮球,足球,排球,皮球', '河北省', '承德市', '围场满族蒙古族自治县', '育群胡同21号', '2018-07-24 12:59:55', '127.0.0.1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
