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
  `cover` varchar(50) DEFAULT 'defaultCover.png' COMMENT '头像地址链接',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `email` varchar(50) NOT NULL COMMENT '邮箱(一个邮箱只能注册一个用户),找回密码使用',
  `password` varchar(50) NOT NULL COMMENT '密码,MD5加密显示',
  `sex` varchar(50) NOT NULL DEFAULT 'male' COMMENT '性别(male:男,remale:女)',
  `hobby` varchar(225) NOT NULL COMMENT '爱好',
  `introduce` varchar(225) DEFAULT '该用户很懒什么都没有留下' COMMENT '自我介绍',
  `province` varchar(50) NOT NULL COMMENT '省',
  `city` varchar(50) NOT NULL COMMENT '市',
  `area` varchar(50) DEFAULT NULL COMMENT '区',
  `address` varchar(225) NOT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户信息表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
