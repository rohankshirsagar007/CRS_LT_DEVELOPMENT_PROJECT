/*
MySQL Data Transfer
Source Host: localhost
Source Database: test_1
Target Host: localhost
Target Database: test_1
Date: 21-12-2021 00:07:40
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(255) NOT NULL,
  `admin_password` varchar(255) NOT NULL,
  PRIMARY KEY  (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL auto_increment,
  `course_name` varchar(255) NOT NULL,
  `course_cost` double NOT NULL,
  PRIMARY KEY  (`course_id`),
  UNIQUE KEY `course_name` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course_professor
-- ----------------------------
DROP TABLE IF EXISTS `course_professor`;
CREATE TABLE `course_professor` (
  `course_id` int(11) default NULL,
  `professor_id` int(11) default NULL,
  KEY `course_id` (`course_id`),
  KEY `professor_id` (`professor_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `professor_id` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`professor_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course_professor_student
-- ----------------------------
DROP TABLE IF EXISTS `course_professor_student`;
CREATE TABLE `course_professor_student` (
  `course_id` int(11) default NULL,
  `professor_id` int(11) default NULL,
  `student_id` int(11) default NULL,
  KEY `course_id` (`course_id`),
  KEY `professor_id` (`professor_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `course_professor_student_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_professor_student_ibfk_2` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`professor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_professor_student_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for professor
-- ----------------------------
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
  `professor_id` int(11) NOT NULL auto_increment,
  `professor_name` varchar(255) NOT NULL,
  `professor_password` varchar(255) NOT NULL,
  `professor_department` varchar(255) NOT NULL,
  PRIMARY KEY  (`professor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL auto_increment,
  `student_name` varchar(255) NOT NULL,
  `student_password` varchar(255) NOT NULL,
  `student_department` varchar(255) NOT NULL,
  PRIMARY KEY  (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for student_grade
-- ----------------------------
DROP TABLE IF EXISTS `student_grade`;
CREATE TABLE `student_grade` (
  `course_id` int(11) NOT NULL,
  `professor_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `mark` double NOT NULL,
  `grade` varchar(255) NOT NULL,
  PRIMARY KEY  (`course_id`,`professor_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'diwakr', 'pass');
INSERT INTO `course` VALUES ('1', 'C', '100');
INSERT INTO `course` VALUES ('2', 'C++', '100');
INSERT INTO `course` VALUES ('3', 'java', '100');
INSERT INTO `course` VALUES ('4', 'angular', '200');
INSERT INTO `course` VALUES ('5', '.net', '100');
INSERT INTO `course` VALUES ('6', 'python', '100');
INSERT INTO `course` VALUES ('7', 'data structures', '100');
INSERT INTO `course` VALUES ('8', 'node js', '100');
INSERT INTO `course` VALUES ('9', 'mongo db', '100');
INSERT INTO `course` VALUES ('10', 'angular js', '100');
INSERT INTO `course` VALUES ('11', 'react js', '100');
INSERT INTO `course_professor` VALUES ('1', '1');
INSERT INTO `course_professor` VALUES ('1', '2');
INSERT INTO `course_professor` VALUES ('2', '3');
INSERT INTO `course_professor` VALUES ('2', '4');
INSERT INTO `course_professor` VALUES ('3', '5');
INSERT INTO `course_professor` VALUES ('3', '6');
INSERT INTO `course_professor` VALUES ('4', '7');
INSERT INTO `course_professor` VALUES ('4', '8');
INSERT INTO `course_professor` VALUES ('5', '9');
INSERT INTO `course_professor` VALUES ('5', '10');
INSERT INTO `course_professor` VALUES ('6', '1');
INSERT INTO `course_professor` VALUES ('6', '2');
INSERT INTO `course_professor` VALUES ('7', '3');
INSERT INTO `course_professor` VALUES ('7', '4');
INSERT INTO `course_professor` VALUES ('8', '5');
INSERT INTO `course_professor` VALUES ('8', '6');
INSERT INTO `course_professor` VALUES ('9', '7');
INSERT INTO `course_professor` VALUES ('9', '8');
INSERT INTO `course_professor` VALUES ('10', '11');
INSERT INTO `course_professor_student` VALUES ('1', '1', '2');
INSERT INTO `course_professor_student` VALUES ('1', '1', '3');
INSERT INTO `course_professor_student` VALUES ('1', '1', '4');
INSERT INTO `course_professor_student` VALUES ('1', '1', '5');
INSERT INTO `course_professor_student` VALUES ('1', '1', '6');
INSERT INTO `course_professor_student` VALUES ('1', '1', '7');
INSERT INTO `course_professor_student` VALUES ('1', '1', '8');
INSERT INTO `course_professor_student` VALUES ('1', '1', '9');
INSERT INTO `course_professor_student` VALUES ('1', '2', '12');
INSERT INTO `course_professor_student` VALUES ('1', '1', '10');
INSERT INTO `course_professor_student` VALUES ('1', '2', '11');
INSERT INTO `course_professor_student` VALUES ('1', '2', '13');
INSERT INTO `course_professor_student` VALUES ('1', '2', '14');
INSERT INTO `course_professor_student` VALUES ('1', '2', '15');
INSERT INTO `course_professor_student` VALUES ('1', '2', '16');
INSERT INTO `course_professor_student` VALUES ('1', '2', '17');
INSERT INTO `course_professor_student` VALUES ('1', '2', '18');
INSERT INTO `course_professor_student` VALUES ('1', '2', '19');
INSERT INTO `course_professor_student` VALUES ('1', '2', '20');
INSERT INTO `course_professor_student` VALUES ('3', '5', '1');
INSERT INTO `course_professor_student` VALUES ('4', '7', '1');
INSERT INTO `course_professor_student` VALUES ('6', '1', '3');
INSERT INTO `course_professor_student` VALUES ('1', '1', '1');
INSERT INTO `professor` VALUES ('1', 'prof1', 'prof1', 'cse');
INSERT INTO `professor` VALUES ('2', 'prof2', 'prof2', 'cse');
INSERT INTO `professor` VALUES ('3', 'prof3', 'prof3', 'cse');
INSERT INTO `professor` VALUES ('4', 'prof4', 'prof4', 'cse');
INSERT INTO `professor` VALUES ('5', 'prof5', 'prof5', 'cse');
INSERT INTO `professor` VALUES ('6', 'prof6', 'prof6', 'cse');
INSERT INTO `professor` VALUES ('7', 'prof7', 'prof7', 'cse');
INSERT INTO `professor` VALUES ('8', 'prof8', 'prof8', 'cse');
INSERT INTO `professor` VALUES ('9', 'prof9', 'prof9', 'cse');
INSERT INTO `professor` VALUES ('10', 'prof10', 'prof10', 'cse');
INSERT INTO `professor` VALUES ('11', 'prof11', 'prof11', 'eee');
INSERT INTO `professor` VALUES ('12', 'prof12', 'prof12', 'ece');
INSERT INTO `professor` VALUES ('13', 'e', 'e', 'e');
INSERT INTO `professor` VALUES ('14', 'a', 'a', 'a');
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'professor');
INSERT INTO `role` VALUES ('3', 'student');
INSERT INTO `student` VALUES ('1', 'stud1', 'stud1', 'cse');
INSERT INTO `student` VALUES ('2', 'stud2', 'stud2', 'cse');
INSERT INTO `student` VALUES ('3', 'stud3', 'stud3', 'cse');
INSERT INTO `student` VALUES ('4', 'stud4', 'stud4', 'cse');
INSERT INTO `student` VALUES ('5', 'stud5', 'stud5', 'cse');
INSERT INTO `student` VALUES ('6', 'stud6', 'stud6', 'cse');
INSERT INTO `student` VALUES ('7', 'stud7', 'stud7', 'cse');
INSERT INTO `student` VALUES ('8', 'stud8', 'stud8', 'cse');
INSERT INTO `student` VALUES ('9', 'stud9', 'stud9', 'cse');
INSERT INTO `student` VALUES ('10', 'stud10', 'stud10', 'cse');
INSERT INTO `student` VALUES ('11', 'stud11', 'stud11', 'cse');
INSERT INTO `student` VALUES ('12', 'stud12', 'stud12', 'cse');
INSERT INTO `student` VALUES ('13', 'stud13', 'stud13', 'cse');
INSERT INTO `student` VALUES ('14', 'stud14', 'stud14', 'cse');
INSERT INTO `student` VALUES ('15', 'stud15', 'stud15', 'eee');
INSERT INTO `student` VALUES ('16', 'stud16', 'stud16', 'ece');
INSERT INTO `student` VALUES ('17', 'stud17', 'stud17', 'cse');
INSERT INTO `student` VALUES ('18', 'stud18', 'stud18', 'cse');
INSERT INTO `student` VALUES ('19', 'stud19', 'stud19', 'cse');
INSERT INTO `student` VALUES ('20', 'stud20', 'stud20', 'cse');
INSERT INTO `student` VALUES ('21', 'stud21', 'stud21', 'cse');
INSERT INTO `student` VALUES ('22', '34', '35', '35');
INSERT INTO `student_grade` VALUES ('1', '1', '1', '67', 'D');
INSERT INTO `student_grade` VALUES ('1', '1', '4', '55', 'E');
INSERT INTO `student_grade` VALUES ('1', '1', '9', '34', 'Fail');
INSERT INTO `student_grade` VALUES ('1', '1', '10', '45', 'Fail');
INSERT INTO `student_grade` VALUES ('2', '3', '1', '73', 'C');
INSERT INTO `student_grade` VALUES ('3', '5', '1', '45', 'Fail');
INSERT INTO `student_grade` VALUES ('6', '1', '3', '45', 'Fail');
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('1', '2');
INSERT INTO `user_role` VALUES ('1', '3');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('2', '3');
INSERT INTO `user_role` VALUES ('3', '2');
INSERT INTO `user_role` VALUES ('3', '3');
INSERT INTO `user_role` VALUES ('4', '2');
INSERT INTO `user_role` VALUES ('4', '3');
INSERT INTO `user_role` VALUES ('5', '2');
INSERT INTO `user_role` VALUES ('5', '3');
INSERT INTO `user_role` VALUES ('6', '3');
INSERT INTO `user_role` VALUES ('7', '2');
INSERT INTO `user_role` VALUES ('7', '3');
INSERT INTO `user_role` VALUES ('8', '2');
INSERT INTO `user_role` VALUES ('8', '3');
INSERT INTO `user_role` VALUES ('9', '2');
INSERT INTO `user_role` VALUES ('9', '3');
INSERT INTO `user_role` VALUES ('10', '2');
INSERT INTO `user_role` VALUES ('10', '3');
INSERT INTO `user_role` VALUES ('11', '3');
INSERT INTO `user_role` VALUES ('12', '3');
INSERT INTO `user_role` VALUES ('13', '2');
INSERT INTO `user_role` VALUES ('13', '3');
INSERT INTO `user_role` VALUES ('14', '2');
INSERT INTO `user_role` VALUES ('14', '3');
INSERT INTO `user_role` VALUES ('15', '3');
INSERT INTO `user_role` VALUES ('16', '3');
INSERT INTO `user_role` VALUES ('17', '3');
INSERT INTO `user_role` VALUES ('18', '3');
INSERT INTO `user_role` VALUES ('19', '3');
INSERT INTO `user_role` VALUES ('20', '3');
INSERT INTO `user_role` VALUES ('21', '3');
