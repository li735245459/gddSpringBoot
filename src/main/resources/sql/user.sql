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

-- 导出  表 gdd.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(36) NOT NULL COMMENT '编号,UUID字符串',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据生成时间',
  `cover` varchar(50) DEFAULT 'cover.jpg' COMMENT '头像地址链接',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `email` varchar(50) NOT NULL COMMENT '邮箱(一个邮箱只能注册一个用户),找回密码使用',
  `password` varchar(50) NOT NULL DEFAULT 'afb4f702e90e70060d09f4d28a848914' COMMENT '密码(默认li123456)(afb4f702e90e70060d09f4d28a848914)',
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

-- 正在导出表  gdd.user 的数据：~4 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `create_time`, `cover`, `name`, `phone`, `email`, `password`, `introduce`, `sex`, `hobby`, `province`, `city`, `area`, `address`, `login_time`, `login_ip`) VALUES
	('77cfd433-c255-4978-aca2-efb453558eb8', '2018-07-06 19:58:09', 'cover.jpg', '李星', '18013896707', 'lixing_java@163.com', 'afb4f702e90e70060d09f4d28a848914', '该用户很懒什么都没有留下', '男', '篮球,足球,排球,皮球', '河北省', '承德市', '围场满族蒙古族自治县', '育群胡同21号', '2018-07-12 12:42:07', '127.0.0.1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
