-- MySQL dump 10.13  Distrib 8.0.23, for osx10.16 (x86_64)
--
-- Host: 127.0.0.1    Database: my_blog_db
-- ------------------------------------------------------
-- Server version	5.7.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_admin_user`
--

DROP TABLE IF EXISTS `tb_admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_admin_user` (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) DEFAULT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) DEFAULT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_admin_user`
--

LOCK TABLES `tb_admin_user` WRITE;
/*!40000 ALTER TABLE `tb_admin_user` DISABLE KEYS */;
INSERT INTO `tb_admin_user` (`admin_user_id`, `login_user_name`, `login_password`, `nick_name`, `locked`) VALUES (1,'renjiahua','027d3922dd6fa2060d6a9c1bbf69e122','不会敲代码的小白',0),(2,'admin','21232f297a57a5a743894a0e4a801fc3','李进德',0);
/*!40000 ALTER TABLE `tb_admin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_blog`
--

DROP TABLE IF EXISTS `tb_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog` (
  `blog_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客表主键id',
  `blog_title` varchar(200) DEFAULT NULL COMMENT '博客标题',
  `blog_sub_url` varchar(200) DEFAULT NULL COMMENT '博客自定义路径url',
  `blog_cover_image` varchar(200) DEFAULT NULL COMMENT '博客封面图',
  `blog_content` mediumtext COMMENT '博客内容',
  `blog_category_id` int(11) DEFAULT NULL COMMENT '博客分类id',
  `blog_category_name` varchar(50) DEFAULT NULL COMMENT '博客分类(冗余字段)',
  `blog_tags` varchar(200) DEFAULT NULL COMMENT '博客标签',
  `blog_status` tinyint(4) DEFAULT '0' COMMENT '0-草稿 1-发布',
  `blog_views` bigint(20) DEFAULT '0' COMMENT '阅读量',
  `enable_comment` tinyint(4) DEFAULT '0' COMMENT '0-允许评论 1-不允许评论',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0=否 1=是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog`
--

LOCK TABLES `tb_blog` WRITE;
/*!40000 ALTER TABLE `tb_blog` DISABLE KEYS */;
INSERT INTO `tb_blog` (`blog_id`, `blog_title`, `blog_sub_url`, `blog_cover_image`, `blog_content`, `blog_category_id`, `blog_category_name`, `blog_tags`, `blog_status`, `blog_views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (40,'G1GC垃圾回收器','','http://localhost:8000/admin/dist/img/rand/8.jpg','G1GC是服务端垃圾回收器,。在于多核大内存的服务器.情况下，用来减少GC时间，提高系统吞吐量.Oracle JDK 7 update 4 以上的版本完全支持. \n\n何时使用G1（-XX:+UseG1GC）\ng1收集器的设计目标：\n\n与应用线程同时工作，几乎不需要stop-the-world(与CMS类似);\n整理剩余空间,不产生内存碎片；（CMS只能在full-GC时，用stop-the-world整理碎片内存）\nGC停顿更加可控；\n不牺牲系统的吞吐量；\ngc不要求额外的内存空间(CMS需要预留空间存储浮动垃圾)；\nG1的设计规划，是要替换掉CMS。\n         G1在某些方便弥补了CMS的不足，比如，CMS使用的是mark-sweep算法，自然会产生内存碎片；然而G1基于copying算法，高效的整理剩余内存,而不需要使用free-list去管理内存碎片。\n另外，G1提供了更多手段，以达到对gc停顿时间可控。\n\nCMS 传统的垃圾回收器：\n\nG1GC垃圾回收器：\n\nheap被划分为一个个相等的不连续的内存区域（regions）,每个region都有一个分代的角色:eden、survivor、old（old还有一种细分 humongous，用来存放大小超过 region 50%以上的巨型对象）。\n\n但是对每个角色的数量并没有强制的限定，也就是说对每种分代内存的大小，可以动态变化(默认年轻代占整个heap的5%)。\n\nG1最大的特点就是高效的执行回收，优先去执行那些大量对象可回收的区域（region）。\n\n另外，G1使用了gc停顿可预测的模型，来满足用户设定的gc停顿时间，根据用户设定的目标时间，g1会自动的选择哪些region要清楚，一次清除多少个region。\n\nG1从多个region中复制存活的对象，然后集中放入一个region中，同时整理、清除内存(copying收集算法)。\n\n注意对比之前的垃圾收集器(主要是CMS)：\n对比使用mark-sweep的CMS，g1使用的copying算法不会造成内存碎片；\n对比ParallelScavenge(基于copying )、ParallelOld收集器(基于mark-compact-sweep)，Parallel\n会对整个区域做整理导致gc Pause会比较长，而g1只是特定的整理几个region。\n\n值得注意:g1不是一个实时的收集器，与parallelScavenge一样，对gc 停顿时间的设置并不绝对生效，只是g1有较高的几率保证不超过设定gc停顿时间。与之前的gc收集器对比，g1会根据用户设定的gc停顿时间，智能评估一下哪几个region需要被回收可以满足用户设定。\n\n',25,'java','Java,JVM',1,0,0,0,'2021-04-01 18:22:35','2021-04-01 18:22:35');
/*!40000 ALTER TABLE `tb_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_blog_category`
--

DROP TABLE IF EXISTS `tb_blog_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类表主键',
  `category_name` varchar(50) DEFAULT NULL COMMENT '分类的名称',
  `category_icon` varchar(50) DEFAULT NULL COMMENT '分类的图标',
  `category_rank` int(11) DEFAULT '1' COMMENT '分类的排序值 被使用的越多数值越大',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0=否 1=是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog_category`
--

LOCK TABLES `tb_blog_category` WRITE;
/*!40000 ALTER TABLE `tb_blog_category` DISABLE KEYS */;
INSERT INTO `tb_blog_category` (`category_id`, `category_name`, `category_icon`, `category_rank`, `is_deleted`, `create_time`) VALUES (25,'java','/admin/dist/img/category/02.png',31,0,'2020-01-21 11:06:58'),(26,'博客介绍','/admin/dist/img/category/06.png',1,1,'2020-03-27 21:06:06'),(27,'安卓','/admin/dist/img/category/03.png',1,0,'2020-03-27 21:07:50'),(28,'数据库','/admin/dist/img/category/04.png',1,1,'2020-03-27 21:08:01'),(29,'数据库','/admin/dist/img/category/04.png',1,0,'2021-04-01 18:12:50'),(30,'大数据','/admin/dist/img/category/12.png',1,0,'2021-04-01 18:13:02'),(31,'WEB','/admin/dist/img/category/08.png',1,0,'2021-04-01 18:13:19'),(32,'Linux','/admin/dist/img/category/13.png',1,0,'2021-04-01 18:13:35'),(33,'容器','/admin/dist/img/category/11.png',1,0,'2021-04-01 18:13:43'),(34,'运维','/admin/dist/img/category/05.png',1,0,'2021-04-01 18:13:52'),(35,'其他','/admin/dist/img/category/18.png',1,0,'2021-04-01 18:14:07');
/*!40000 ALTER TABLE `tb_blog_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_blog_comment`
--

DROP TABLE IF EXISTS `tb_blog_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `blog_id` bigint(20) DEFAULT '0' COMMENT '关联的blog主键',
  `commentator` varchar(50) DEFAULT '' COMMENT '评论者名称',
  `email` varchar(100) DEFAULT '' COMMENT '评论人的邮箱',
  `q_number` varchar(255) DEFAULT NULL COMMENT '评论人qq号',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '评论人昵称',
  `head_img` varchar(255) DEFAULT NULL COMMENT '评论人头像',
  `website_url` varchar(50) DEFAULT '' COMMENT '网址',
  `comment_body` varchar(200) DEFAULT '' COMMENT '评论内容',
  `comment_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论提交时间',
  `commentator_ip` varchar(20) DEFAULT '' COMMENT '评论时的ip地址',
  `reply_body` varchar(200) DEFAULT '' COMMENT '回复内容',
  `reply_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  `comment_status` tinyint(4) DEFAULT '0' COMMENT '是否审核通过 0-未审核 1-审核通过',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog_comment`
--

LOCK TABLES `tb_blog_comment` WRITE;
/*!40000 ALTER TABLE `tb_blog_comment` DISABLE KEYS */;
INSERT INTO `tb_blog_comment` (`comment_id`, `blog_id`, `commentator`, `email`, `q_number`, `nick_name`, `head_img`, `website_url`, `comment_body`, `comment_create_time`, `commentator_ip`, `reply_body`, `reply_create_time`, `comment_status`, `is_deleted`) VALUES (27,22,'不会敲代码的小白','1406628376@qq.com',NULL,NULL,NULL,'','dadad','2020-02-01 15:45:40','','不错呦','2020-02-05 10:46:19',1,1),(28,22,'','1406628376@qq.com','1406628376','敲代码的长腿毛欧巴','http://qlogo1.store.qq.com/qzone/1406628376/1406628376/100','','eqeqeq','2020-03-06 07:02:53','','','2020-03-06 15:02:53',1,1),(29,25,'','1406628376@qq.com','1406628376','敲代码的长腿毛欧巴','http://qlogo1.store.qq.com/qzone/1406628376/1406628376/100','','daaaaaaaaaaaaaaa','2020-03-09 09:03:15','','很高兴收到你的留言','2020-03-09 09:03:51',1,1),(30,26,'','1406628376@qq.com','1406628376','敲代码的长腿毛欧巴','http://qlogo1.store.qq.com/qzone/1406628376/1406628376/100','','dadada','2020-03-09 09:29:38','','da dafaf','2020-03-10 06:45:55',1,1),(31,29,'','1406628376@qq.com','1406628376','敲代码的长腿毛欧巴','http://qlogo1.store.qq.com/qzone/1406628376/1406628376/100','','阿发发发啊 ','2020-03-25 09:44:24','','不错呀','2020-03-25 09:45:12',1,1),(32,37,'','673073521@qq.com','673073521','St. Peter','http://qlogo2.store.qq.com/qzone/673073521/673073521/100','','测试一下评论','2021-04-01 09:49:37','','','2021-04-01 17:49:39',1,1),(33,37,'','673073521@qq.com','673073521','St. Peter','http://qlogo2.store.qq.com/qzone/673073521/673073521/100','','赤裸','2021-04-01 09:52:33','','','2021-04-01 17:52:35',1,1),(34,40,'','673073521@qq.com','673073521','St. Peter','http://qlogo2.store.qq.com/qzone/673073521/673073521/100','','图片怎么不显示了呢','2021-04-01 10:27:42','','','2021-04-01 18:27:43',1,0),(35,40,'','673073521@qq.com','673073521','St. Peter','http://qlogo2.store.qq.com/qzone/673073521/673073521/100','','已经修复好啦','2021-04-08 02:19:51','','','2021-04-08 10:19:53',1,0);
/*!40000 ALTER TABLE `tb_blog_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_blog_tag`
--

DROP TABLE IF EXISTS `tb_blog_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键id',
  `tag_name` varchar(100) DEFAULT NULL COMMENT '标签名称',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0=否 1=是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog_tag`
--

LOCK TABLES `tb_blog_tag` WRITE;
/*!40000 ALTER TABLE `tb_blog_tag` DISABLE KEYS */;
INSERT INTO `tb_blog_tag` (`tag_id`, `tag_name`, `is_deleted`, `create_time`) VALUES (142,'dasdsada',1,'2020-01-21 11:07:58'),(143,'dada',1,'2020-02-01 13:15:31'),(144,'HashMap',0,'2020-02-01 13:17:56'),(145,'ha',1,'2020-02-05 18:58:07'),(146,'大大a',1,'2020-03-09 16:14:06'),(147,'a大啊的啊',1,'2020-03-09 16:14:06'),(148,'a fa',1,'2020-03-09 17:29:14'),(149,'测试',1,'2020-03-16 11:13:56'),(150,'dad',1,'2020-03-26 17:17:05'),(151,'a',1,'2020-03-26 17:17:05'),(152,'adad',1,'2020-03-26 17:17:37'),(153,'add',1,'2020-03-26 17:17:46'),(154,'简历',1,'2020-03-26 20:27:16'),(155,'介绍',1,'2020-03-26 21:30:37'),(156,'Mark Down',1,'2020-03-26 21:30:37'),(157,'Spring Boot',1,'2020-03-26 21:30:37'),(158,'Java',0,'2021-04-01 18:22:35'),(159,'JVM',0,'2021-04-01 18:22:35');
/*!40000 ALTER TABLE `tb_blog_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_blog_tag_relation`
--

DROP TABLE IF EXISTS `tb_blog_tag_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog_tag_relation` (
  `relation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=349 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog_tag_relation`
--

LOCK TABLES `tb_blog_tag_relation` WRITE;
/*!40000 ALTER TABLE `tb_blog_tag_relation` DISABLE KEYS */;
INSERT INTO `tb_blog_tag_relation` (`relation_id`, `blog_id`, `tag_id`, `create_time`) VALUES (309,28,144,'2020-03-09 18:17:09'),(347,40,158,'2021-04-21 17:18:55'),(348,40,159,'2021-04-21 17:18:55');
/*!40000 ALTER TABLE `tb_blog_tag_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_config`
--

DROP TABLE IF EXISTS `tb_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_config` (
  `config_name` varchar(100) NOT NULL DEFAULT '' COMMENT '配置项的名称',
  `config_value` varchar(200) DEFAULT '' COMMENT '配置项的值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`config_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_config`
--

LOCK TABLES `tb_config` WRITE;
/*!40000 ALTER TABLE `tb_config` DISABLE KEYS */;
INSERT INTO `tb_config` (`config_name`, `config_value`, `create_time`, `update_time`) VALUES ('footerAbout','2021','2018-11-11 20:33:23','2021-04-01 03:10:21'),('footerCopyRight','Copyright © 2021 dddpeter','2018-11-11 20:33:31','2021-04-01 03:10:21'),('footerICP','暂无备案信息','2018-11-11 20:33:27','2021-04-01 03:10:21'),('footerPoweredBy','https://gitee.com/dddpeter','2018-11-11 20:33:36','2021-04-01 03:10:21'),('footerPoweredByURL','https://gitee.com/dddpeter','2018-11-11 20:33:39','2021-04-01 03:10:21'),('websiteDescription','dddpeter的博客','2018-11-11 20:33:04','2021-04-01 03:10:15'),('websiteIcon','/blog/amaze/images/favicon.ico','2018-11-11 20:33:11','2021-04-01 03:10:15'),('websiteLogo','/blog/amaze/images/website.png','2018-11-11 20:33:08','2021-04-01 03:10:15'),('websiteName','dddpeter的博客','2018-11-11 20:33:01','2021-04-01 03:10:15'),('yourAvatar','/admin/dist/img/headimg.png','2018-11-11 20:33:14','2021-04-08 02:14:27'),('yourEmail','dddpeter@126.com','2018-11-11 20:33:17','2021-04-08 02:14:27'),('yourName','dddpeter','2018-11-11 20:33:20','2021-04-08 02:14:27');
/*!40000 ALTER TABLE `tb_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_images`
--

DROP TABLE IF EXISTS `tb_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_path` varchar(400) NOT NULL,
  `image_url` varchar(400) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_images`
--

LOCK TABLES `tb_images` WRITE;
/*!40000 ALTER TABLE `tb_images` DISABLE KEYS */;
INSERT INTO `tb_images` (`id`, `image_path`, `image_url`, `created_time`, `modified_time`) VALUES (1,'/Users/hqins/workspace/Lumos/opt/deploy/upload/20210420_18081287.png','http://localhost:8000/upload/20210420_18081287.png','2021-04-20 18:08:12','2021-04-20 18:08:12'),(2,'/Users/hqins/workspace/Lumos/opt/deploy/upload/20210420_18101415.png','http://localhost:8000/upload/20210420_18101415.png','2021-04-20 18:10:40','2021-04-20 18:10:40'),(3,'/Users/hqins/workspace/Lumos/opt/deploy/upload/20210420_18282451.png','http://localhost:8000/upload/20210420_18282451.png','2021-04-20 18:28:25','2021-04-20 18:28:25');
/*!40000 ALTER TABLE `tb_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_link`
--

DROP TABLE IF EXISTS `tb_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_link` (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
  `link_type` tinyint(4) DEFAULT '0' COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
  `link_name` varchar(50) DEFAULT NULL COMMENT '网站名称',
  `link_url` varchar(100) DEFAULT NULL COMMENT '网站链接',
  `link_description` varchar(100) DEFAULT NULL COMMENT '网站描述',
  `link_logo` varchar(512) DEFAULT NULL COMMENT '网站logo',
  `link_rank` int(11) DEFAULT '0' COMMENT '用于列表排序',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_link`
--

LOCK TABLES `tb_link` WRITE;
/*!40000 ALTER TABLE `tb_link` DISABLE KEYS */;
INSERT INTO `tb_link` (`link_id`, `link_type`, `link_name`, `link_url`, `link_description`, `link_logo`, `link_rank`, `is_deleted`, `create_time`) VALUES (20,0,'我的博客','https://javafan.club','我的个人博客','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=69433670,3397024061&fm=15&gp=0.jpg',0,1,'2020-03-26 15:06:09'),(21,0,'腾讯','https://www.baidu.com/','大大','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3557113452,423804103&fm=15&gp=0.jpg',0,1,'2020-03-26 15:06:40'),(22,0,'维普','https://weipu.com','c查重daaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','https://www.baidu.com/img/pc_cc75653cd975aea6d4ba1f59b3697455.png',0,1,'2020-05-27 22:27:55'),(23,0,'baidu','https://www.baidu.com','面向开源及私有软件项目的托管平台','http://img4.imgtn.bdimg.com/it/u=1490414401,2620769295&fm=26&gp=0.jpg',0,1,'2020-06-03 18:57:43');
/*!40000 ALTER TABLE `tb_link` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-25 18:12:24
