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

-- 导出  表 gdd.cover_type 结构
CREATE TABLE IF NOT EXISTS `cover_type` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `p_id` varchar(36) NOT NULL COMMENT '父级编号,"0"表示根节点',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  `node_level` int(11) NOT NULL COMMENT '节点级别,0表示根节点,1表示一级子节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='封面类别信息';

-- 正在导出表  gdd.cover_type 的数据：~12 rows (大约)
DELETE FROM `cover_type`;
/*!40000 ALTER TABLE `cover_type` DISABLE KEYS */;
INSERT INTO `cover_type` (`id`, `create_time`, `p_id`, `name`, `node_level`) VALUES
	('4112e27d-8f13-445f-8f8e-75bab131740d', '2018-07-15 22:00:44', 'a48c4a1e-0868-4824-9319-67ed7181bbb4', '皮肤药', 2),
	('495885db-c0e6-4c4d-a967-969faa61e1e2', '2018-07-15 21:53:09', '0', '用户头像', 0),
	('628d7ecc-73e2-454c-8f8c-11b826ce15f8', '2018-07-15 21:54:59', 'ff032041-15ec-4ff7-a7bf-c48dc21f3ca1', '犬类', 1),
	('71fd9743-7803-4bc2-a0e8-bf0ded2b9ec3', '2018-07-15 21:58:58', '628d7ecc-73e2-454c-8f8c-11b826ce15f8', '汪星人', 2),
	('8b0fcda0-d0cf-4401-96cf-d5d1faf79e22', '2018-07-15 21:59:32', 'e9177e1e-9315-4610-9cf9-47ba80f60881', '喵粮', 2),
	('a3740752-c8c8-47ec-9467-19698a61bea5', '2018-07-15 21:59:25', 'e9177e1e-9315-4610-9cf9-47ba80f60881', '喵星人', 2),
	('a48c4a1e-0868-4824-9319-67ed7181bbb4', '2018-07-15 22:00:07', 'ff032041-15ec-4ff7-a7bf-c48dc21f3ca1', '其他', 1),
	('c03da631-8320-409f-9683-e32e861b871a', '2018-07-15 22:00:33', 'a48c4a1e-0868-4824-9319-67ed7181bbb4', '锁绳', 2),
	('c3d77fe3-40a9-45c3-95c6-8513f2a5913b', '2018-07-15 21:49:54', '0', '门户网站轮播图', 0),
	('e9177e1e-9315-4610-9cf9-47ba80f60881', '2018-07-15 21:55:14', 'ff032041-15ec-4ff7-a7bf-c48dc21f3ca1', '猫类', 1),
	('fe28ed79-1de7-4d4f-b4c4-96d55c1f60b0', '2018-07-15 21:59:07', '628d7ecc-73e2-454c-8f8c-11b826ce15f8', '犬粮', 2),
	('ff032041-15ec-4ff7-a7bf-c48dc21f3ca1', '2018-07-15 21:52:39', '0', '商品封面图', 0);
/*!40000 ALTER TABLE `cover_type` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;