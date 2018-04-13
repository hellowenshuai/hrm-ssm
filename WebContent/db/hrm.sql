/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : hrm

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2018-04-01 23:26:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `dept_inf`
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES ('1', '技术部', '技术部');
INSERT INTO `dept_inf` VALUES ('2', '运营部', '运营部');
INSERT INTO `dept_inf` VALUES ('3', '财务部', '财务部');
INSERT INTO `dept_inf` VALUES ('5', '总公办', '总公办');
INSERT INTO `dept_inf` VALUES ('6', '市场部', '市场部');
INSERT INTO `dept_inf` VALUES ('7', '教学部', '教学部');
INSERT INTO `dept_inf` VALUES ('8', '管理部', '管理部');

-- ----------------------------
-- Table structure for `document_inf`
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `filename` varchar(300) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `do_user_id` (`user_id`),
  CONSTRAINT `do_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document_inf
-- ----------------------------
INSERT INTO `document_inf` VALUES ('1', 'hrm', 'xxx/文档说明.txt', 'hrm数据库，文件错误', '2018-03-25 10:19:32', '1');
INSERT INTO `document_inf` VALUES ('2', '3.21回顾', 'xxx/3.21回顾.txt', '3.21回顾.txt', '2018-03-25 10:31:31', '1');
INSERT INTO `document_inf` VALUES ('3', '3.22', 'xxx/3.22.txt', '3.22', '2018-03-25 10:34:14', '1');
INSERT INTO `document_inf` VALUES ('4', '阅读词汇', 'xxx/3.30阅读词汇.txt', '英语学习必备', '2018-03-25 10:36:13', '1');
INSERT INTO `document_inf` VALUES ('6', 'errors', 'xxx/error.txt', '这是hrm程序编写的错误文档，是我的珍贵资料', '2018-04-01 21:02:50', '1');

-- ----------------------------
-- Table structure for `employee_inf`
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `card_id` varchar(18) NOT NULL,
  `address` varchar(50) NOT NULL,
  `post_code` varchar(50) DEFAULT NULL,
  `tel` varchar(16) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `qq_num` varchar(10) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `sex` int(11) NOT NULL DEFAULT '1',
  `party` varchar(10) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `race` varchar(100) DEFAULT NULL,
  `education` varchar(10) DEFAULT NULL,
  `speciality` varchar(20) DEFAULT NULL,
  `hobby` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `em_dept_id` (`dept_id`),
  KEY `em_job_id` (`job_id`),
  CONSTRAINT `em_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `dept_inf` (`id`),
  CONSTRAINT `em_job_id` FOREIGN KEY (`job_id`) REFERENCES `job_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES ('1', '1', '8', '爱丽丝', '4328011988', '广州天河', '510000', '020-77777777', '13902001111', '36750066', '251425887@qq.com', '2', '党员', '1980-01-01 00:00:00', '满', '本科', '美声', '唱歌', '四大天王', '2016-03-14 11:35:18');
INSERT INTO `employee_inf` VALUES ('2', '2', '1', '杰克', '412727199808022658', '43234', '510000', '020-77777777', '15893630801', '1397754081', '251425887@qq.com', '2', '团员', '2018-04-19 15:38:21', '满', '硕士', '美声', '唱歌', '', '2018-04-01 16:14:41');
INSERT INTO `employee_inf` VALUES ('5', '3', '3', '反反复复', '412727199808022657', '15893630801', '510000', '020-77777777', '15893630801', '1397754081', '15893630801@163.com', '1', '团员', '2018-04-21 15:38:25', '汉', '本科', null, '敲代码', '陈氏', '2018-03-30 17:51:14');
INSERT INTO `employee_inf` VALUES ('7', '1', '6', '张三丰', '412727199808022658', '张家界武当山', '510000', '020-77771111', '15893630801', '1397754082', '15893630801@163.com', '1', '党员', '2018-04-19 15:38:32', '汉', '博士', '美声', '练习太极', '武当宗师', '2018-04-01 16:12:51');
INSERT INTO `employee_inf` VALUES ('8', '3', '1', 'b', '412727199808022657', '河南省淮阳县葛店乡', '510000', '020-77777777', '15893630801', '1397754081', '15893630801@163.com', '1', '团员', '2018-04-19 15:38:36', '汉', '大专', '软件工程', '敲代码', '陈氏夫人', '2018-03-30 23:29:53');

-- ----------------------------
-- Table structure for `job_inf`
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES ('1', '职员', '职员');
INSERT INTO `job_inf` VALUES ('2', 'Java开发工程师', 'Java开发工程师');
INSERT INTO `job_inf` VALUES ('3', 'Java中级开发工程师', 'Java中级开发工程师');
INSERT INTO `job_inf` VALUES ('4', 'Java高级开发工程师', 'Java高级开发工程师');
INSERT INTO `job_inf` VALUES ('5', '系统管理员', '系统管理员');
INSERT INTO `job_inf` VALUES ('6', '架构师', '架构师');
INSERT INTO `job_inf` VALUES ('7', '主管', '主管');
INSERT INTO `job_inf` VALUES ('8', '经理', '经理');
INSERT INTO `job_inf` VALUES ('9', '总经理', '总经理');
INSERT INTO `job_inf` VALUES ('10', '培训老师', '培训老师');

-- ----------------------------
-- Table structure for `notice_inf`
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_NOTICE_USER` (`user_id`),
  CONSTRAINT `notice_inf_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------
INSERT INTO `notice_inf` VALUES ('2', '公司清明节放假通知', '20xx清明节假期将至，根据根据国家节假日放假规定，并考虑公司的实际情况，公司特将20xx年清明节放假做以下安排，请各位员工参照执行！\r\n\r\n　　1、4月5-7日放假调休，共3天。4月8日（星期日）上班。\r\n\r\n　　2、节假日期间外出游玩请看好自己的行李物品，注意自己的人身安全。意外情况，公司将不予负责！\r\n\r\n　　3、请各部门将需要安排值班情况以及值班人员安排的详细情况（包括姓名、部门、联系方式等）与放假前一天下班之前提交公司人事部xx处，谢谢！\r\n', '2018-04-01 20:21:21', null);
INSERT INTO `notice_inf` VALUES ('3', 'user_id', '这是用户的id，是整个表的主键，也是这个表的灵魂', '2018-04-01 23:16:03', '1');

-- ----------------------------
-- Table structure for `user_inf`
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) NOT NULL,
  `password` varchar(16) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES ('1', 'admin', '123456', '2', '2016-03-12 09:34:28', '超级管理员');
INSERT INTO `user_inf` VALUES ('2', '陈文文', '123456', '1', '2018-03-13 15:42:49', '普通管理员');
INSERT INTO `user_inf` VALUES ('3', '陈帅帅和陈文文', '123456', '1', '2018-03-15 17:55:57', '普通管理员');
INSERT INTO `user_inf` VALUES ('4', '段海涛', '123456', '1', '2018-03-15 19:36:46', '普通管理员');
INSERT INTO `user_inf` VALUES ('5', '黄药师', '666999', '1', '2018-03-15 19:39:57', '普通管理员');
INSERT INTO `user_inf` VALUES ('6', '洪七公', '999666', '1', '2018-03-16 17:50:42', '普通管理员');
INSERT INTO `user_inf` VALUES ('19', 'test01', '123456', '1', '2018-04-01 22:11:02', '普通管理员');
