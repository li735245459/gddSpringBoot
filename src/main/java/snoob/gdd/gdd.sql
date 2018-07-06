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

-- 导出  表 gdd.log 结构
CREATE TABLE IF NOT EXISTS `log` (
  `id` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `req_url` varchar(500) NOT NULL,
  `req_method` varchar(50) NOT NULL,
  `req_args` varchar(500) NOT NULL,
  `controller_method` varchar(50) NOT NULL,
  `msg` varchar(5000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='错误日志';

-- 正在导出表  gdd.log 的数据：~13 rows (大约)
DELETE FROM `log`;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` (`id`, `create_time`, `req_url`, `req_method`, `req_args`, `controller_method`, `msg`) VALUES
	('0ea54f5d-7e4a-453f-bc79-4944db36119c', '2018-07-05 18:53:06', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@10e148e', 'snoob.gdd.controller.ExcelController.export', 'Java heap space'),
	('217521ba-d396-4b7a-9876-41f70872f3e1', '2018-07-04 10:26:24', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@5356ec', 'snoob.gdd.controller.ExcelController.export', 'cn.afterturn.easypoi.excel.entity.ExportParams cannot be cast to org.apache.poi.ss.usermodel.Workbook'),
	('2fa5ab30-03c7-4883-832f-260b9069d8a5', '2018-07-04 08:43:47', 'http://localhost:8080/gdd/user/export', 'POST', 'org.apache.catalina.connector.RequestFacade@65bb', 'snoob.gdd.controller.UserController.export', 'D:\\excel\\ExcelExportBigData.bigDataExport.xlsx (系统找不到指定的路径。)'),
	('3e163874-3458-47c3-8404-2e4d18dd3e71', '2018-07-04 13:27:56', 'http://127.0.0.1:8080/gdd/excel/exportUser', 'GET', 'org.apache.catalina.connector.RequestFacade@e07e8b', 'snoob.gdd.controller.ExcelController.export', 'JWT校验失败'),
	('4a740d5d-c409-4d2e-9f20-d0ff25eee56b', '2018-07-05 18:53:08', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@7378de', 'snoob.gdd.controller.ExcelController.export', 'java.io.IOException: 您的主机中的软件中止了一个已建立的连接。'),
	('537ccdbb-99d9-48cb-8d5d-e796779900e6', '2018-07-04 08:46:33', 'http://localhost:8080/gdd/user/export', 'POST', 'org.apache.catalina.connector.RequestFacade@12c6b02', 'snoob.gdd.controller.UserController.export', 'D: (拒绝访问。)'),
	('6ee158fa-64d5-428a-8828-0881ae7aca40', '2018-07-04 08:47:02', 'http://localhost:8080/gdd/user/export', 'POST', 'org.apache.catalina.connector.RequestFacade@12c6b02', 'snoob.gdd.controller.UserController.export', 'D: (拒绝访问。)'),
	('792f1e5d-9deb-41b7-8436-e46d9a42b39d', '2018-07-05 19:04:36', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@6360ff', 'snoob.gdd.controller.ExcelController.export', 'Java heap space'),
	('8b71b9c6-e90e-4fad-a4fb-a73a449cb5cf', '2018-07-05 19:17:32', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@e0fafa', 'snoob.gdd.controller.ExcelController.export', 'Java heap space'),
	('9b761963-de92-41b9-9d91-bf759f0cffe4', '2018-07-05 19:22:35', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@62462b', 'snoob.gdd.controller.ExcelController.export', 'Java heap space'),
	('ab97297f-f1ea-4047-a9e6-59bd10a401db', '2018-07-04 10:27:22', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@5356ec', 'snoob.gdd.controller.ExcelController.export', 'cn.afterturn.easypoi.excel.entity.ExportParams cannot be cast to org.apache.poi.ss.usermodel.Workbook'),
	('b00ec2c7-1456-4ded-a77f-3658ad6c8c76', '2018-07-04 10:26:12', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@5356ec', 'snoob.gdd.controller.ExcelController.export', 'cn.afterturn.easypoi.excel.entity.ExportParams cannot be cast to org.apache.poi.ss.usermodel.Workbook'),
	('c3ec741d-ebaf-4cce-a29a-67ac164ea1d2', '2018-07-04 10:29:14', 'http://localhost:8080/gdd/excel/exportUser', 'POST', 'org.apache.catalina.connector.RequestFacade@5356ec', 'snoob.gdd.controller.ExcelController.export', 'cn.afterturn.easypoi.excel.entity.ExportParams cannot be cast to org.apache.poi.ss.usermodel.Workbook');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;

-- 导出  表 gdd.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(36) NOT NULL COMMENT '编号,UUID字符串',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据生成时间',
  `cover` varchar(50) DEFAULT 'cover.jpg' COMMENT '头像地址链接',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `email` varchar(50) NOT NULL COMMENT '邮箱(一个邮箱只能注册一个用户),找回密码使用',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `introduce` varchar(225) DEFAULT '该用户很懒什么都没有留下' COMMENT '备注',
  `sex` varchar(50) DEFAULT '男' COMMENT '性别',
  `hobby` varchar(225) DEFAULT NULL COMMENT '爱好',
  `province` varchar(50) DEFAULT '0' COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `area` varchar(50) DEFAULT NULL COMMENT '区',
  `address` varchar(225) DEFAULT NULL COMMENT '详细地址',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登陆ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户信息表';

-- 正在导出表  gdd.user 的数据：~101 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `create_time`, `cover`, `name`, `phone`, `email`, `password`, `introduce`, `sex`, `hobby`, `province`, `city`, `area`, `address`, `login_time`, `login_ip`) VALUES
	('24b12317-eae6-4b0e-8935-0f88d42adb5b', '2018-07-06 20:01:20', 'cover.jpg', '张伟', '18013896708', 'lixing_java@126.com', 'afb4f702e90e70060d09f4d28a848914', '该用户很懒什么都没有留下', '男', '篮球,足球,排球', '天津市', '和平区', '-1', '鞍山道32号', '2018-07-06 20:01:27', '127.0.0.1'),
	('77cfd433-c255-4978-aca2-efb453558eb8', '2018-07-06 19:58:09', 'cover.jpg', '李星', '18013896707', 'lixing_java@163.com', 'afb4f702e90e70060d09f4d28a848914', '该用户很懒什么都没有留下', '男', '篮球,足球,排球,皮球', '北京市', '东城区', '-1', '育群胡同21号', '2018-07-06 20:03:10', '127.0.0.1'),
	('edbf2143-ec56-426f-94fb-bc4ed69cc672', '2018-07-06 20:15:00', 'cover.jpg', '毛立磊', '18013896705', 'lixing_java@sina.com', 'afb4f702e90e70060d09f4d28a848914', '该用户很懒什么都没有留下', '女', '皮球', '河北省', '承德市', '围场满族蒙古族自治县', '妮敦道19号', NULL, NULL),
	('effbbe22-cbe1-49ca-ae51-236ca2d3d9c9', '2018-07-06 20:08:45', 'cover.jpg', '李佳丽', '18013896706', 'lixing_java@qq.com', 'afb4f702e90e70060d09f4d28a848914', '该用户很懒什么都没有留下', '女', '足球,篮球', '上海市', '宝山', '-1', '密山路5号', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
