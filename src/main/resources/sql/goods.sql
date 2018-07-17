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

-- 导出  表 gdd.goods 结构
CREATE TABLE IF NOT EXISTS `goods` (
  `id` varchar(36) NOT NULL COMMENT '编号,uuid',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `goods_type_id` varchar(36) DEFAULT NULL COMMENT '外键编号',
  `goods_type_name` varchar(20) DEFAULT NULL COMMENT '外键名称',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `introduce` varchar(50) DEFAULT NULL COMMENT '介绍',
  `price` double NOT NULL COMMENT '价格',
  `promotion_price` double DEFAULT NULL COMMENT '促销价格',
  PRIMARY KEY (`id`),
  KEY `FK_goods_goods_type` (`goods_type_id`),
  CONSTRAINT `FK_goods_goods_type` FOREIGN KEY (`goods_type_id`) REFERENCES `goods_type` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

-- 正在导出表  gdd.goods 的数据：~0 rows (大约)
DELETE FROM `goods`;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
