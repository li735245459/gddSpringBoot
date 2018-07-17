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

-- 导出  表 gdd.cover 结构
CREATE TABLE IF NOT EXISTS `cover` (
  `id` varchar(36) NOT NULL COMMENT '编号,uuid',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cover_type_id` varchar(36) DEFAULT NULL COMMENT '外键编号,uuid',
  `cover_type_name` varchar(36) DEFAULT NULL COMMENT '外键名称',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `introduce` varchar(50) DEFAULT NULL COMMENT '说明',
  `src` varchar(500) NOT NULL COMMENT '下载地址',
  `link` varchar(100) DEFAULT NULL COMMENT '外链',
  PRIMARY KEY (`id`),
  KEY `FK_cover_cover_type` (`cover_type_id`),
  CONSTRAINT `FK_cover_cover_type` FOREIGN KEY (`cover_type_id`) REFERENCES `cover_type` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='封面信息';

-- 正在导出表  gdd.cover 的数据：~0 rows (大约)
DELETE FROM `cover`;
/*!40000 ALTER TABLE `cover` DISABLE KEYS */;
/*!40000 ALTER TABLE `cover` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
