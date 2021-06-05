ALTER/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/5/27 11:13:48                           */
/*==============================================================*/

CREATE DATABASE dormitory;
USE dormitory;

DROP TABLE IF EXISTS t_class;

DROP TABLE IF EXISTS t_repair;

DROP TABLE IF EXISTS t_room;

DROP TABLE IF EXISTS t_student;

DROP TABLE IF EXISTS t_user;

/*==============================================================*/
/* Table: t_class                                               */
/*==============================================================*/
CREATE TABLE t_class
(
   pk_classId           INT NOT NULL AUTO_INCREMENT COMMENT '班级编号',
   c_className          VARCHAR(20) COMMENT '班级名称',
   c_startDate          DATE COMMENT '开班时间',
   c_headmaster         VARCHAR(20) COMMENT '带班老师',
   PRIMARY KEY (pk_classId)
);

ALTER TABLE t_class COMMENT '班级表';
-- 插入记录
INSERT INTO t_class(c_className,c_startDate,c_headmaster)
VALUES('GT06','2020-09-01','张老师'),
('GT07','2020-10-02','胡老师'),
('GT08','2020-11-03','韩老师'),
('GT09','2020-12-04','闵老师'),
('GT10','2020-01-05','闫老师');



DROP TABLE t_class;
TRUNCATE TABLE t_class;
SELECT*FROM t_class;
/*==============================================================*/
/* Table: t_facility                                              */
/*==============================================================*/
CREATE TABLE t_facility
(
   pk_facilityId          INT AUTO_INCREMENT COMMENT '维护编号',
   f_describe           VARCHAR(50) COMMENT '损坏描述',
   f_reportDate         DATE COMMENT '报损日期',
   f_whetherSolve       ENUM('未解决','已解决') COMMENT '是否解决',
   fk_roomId            INT,-- 房间编号
   PRIMARY KEY (pk_facilityId)
);

ALTER TABLE t_facility COMMENT '房间设施维护表';
-- 插入记录
INSERT INTO t_facility( f_describe, f_reportDate,f_whetherSolve,fk_roomId)
VALUES('水龙头坏了','2019-05-09','未解决',1),
('灯泡儿烧了','2020-05-07','已解决',1),
('床板穿了','2019-09-01','未解决',2),
('房顶漏水','2021-04-01','已解决',2),
('空调坏了','2020-01-01','未解决',3),
('电源开关漏电','2020-12-01','未解决',4),
('天然气泄露','2020-06-20','未解决',5),
('床板穿了','2019-09-01','未解决',1),
('房顶漏水','2021-04-01','已解决',3),
('空调坏了','2020-01-01','未解决',2),
('电源开关漏电','2020-12-01','未解决',2),
('天然气泄露','2020-06-20','未解决',2);

DROP TABLE t_facility;
truncate table t_facility;
SELECT*FROM t_facility;

-- select f.`f_describe`, f.`f_reportDate`, f.`f_whetherSolve` from t_facility f where f.`fk_roomId` = 1;
-- select count(f.`f_whetherSolve`) cf from t_facility f where f.`fk_roomId`=1 and f.`f_whetherSolve`="已解决" ;
-- delete from t_facility where fk_roomId=5;
select f.`pk_facilityId`, f.`f_describe`, f.`f_reportDate`, f.`f_whetherSolve` from t_facility f where f.`fk_roomId` = 1;



/*==============================================================*/
/* Table: t_room                                                */
/*==============================================================*/
CREATE TABLE t_room
(
   pk_roomId            INT NOT NULL AUTO_INCREMENT COMMENT '房间编号',
   r_address            VARCHAR(30) COMMENT '房间地址',
   r_capacity           INT COMMENT '可容纳人数',
   r_roomStatus         ENUM('正常','设施损坏') COMMENT '房间状态',
   r_roomType           ENUM('男生宿舍','女生宿舍') COMMENT '房间类型',
   PRIMARY KEY (pk_roomId)
);


ALTER TABLE t_room COMMENT '房间表';
-- 插入记录
INSERT INTO t_room( r_address,r_capacity,r_roomStatus,r_roomType)
VALUES('红星路7号',5,'设施损坏','男生宿舍'),
('科华北路3号',8,'正常','女生宿舍'),
('二仙桥桥洞',5,'设施损坏','男生宿舍'),
('共和路5号',10,'设施损坏','男生宿舍'),
('解放路12号',4,'设施损坏','女生宿舍');

drop table t_room;
TRUNCATE TABLE t_room;
SELECT*FROM t_room;

SELECT pk_roomId,r.`r_capacity`,COUNT(s.`pk_studentId`) s,r_address,r_roomStatus,r_roomType FROM t_room r LEFT JOIN t_student s ON r.`pk_roomId`=s.`fk_roomId`GROUP BY r.`pk_roomId`;


-- insert into t_room(r_address,r_capacity,r_roomStatus,r_roomType)values()
select count(r_address) from t_room r left join t_student  s on r.`pk_roomId` = s.`fk_roomId` 
where r.`pk_roomId` = 2;

SELECT pk_roomId,r.`r_capacity`,COUNT(s.`pk_studentId`) s,r_address,r_roomStatus,r_roomType FROM t_room r LEFT JOIN t_student s ON r.`pk_roomId`=s.`fk_roomId` where 1=1 GROUP BY r.`pk_roomId` ;
SELECT * FROM t_student s  JOIN t_room r ON s.`fk_roomId`=r.`pk_roomId`WHERE r_roomType='女生宿舍' GROUP BY r_address HAVING r_capacity>COUNT(pk_studentId);


/*==============================================================*/
/* Table: t_student                                             */
/*==============================================================*/
DROP TABLE t_student;
CREATE TABLE t_student
(
   pk_studentId         INT NOT NULL AUTO_INCREMENT COMMENT '学生编号',
   s_name               VARCHAR(20) COMMENT '姓名',
   s_sex                ENUM('男','女') COMMENT '性别',
   fk_roomId            INT,
   fk_classId           INT,
   s_phone              VARCHAR(20) COMMENT '联系电话',
   s_intoDate           DATE COMMENT '入住时间',
   s_imgpath            VARCHAR(20) COMMENT '头像路径',
   PRIMARY KEY (pk_studentId)
);

ALTER TABLE t_student COMMENT '学生表';
-- 插入记录
INSERT INTO t_student(s_name,s_sex,fk_classId,fk_roomId,s_phone,s_intoDate,s_imgPath)
VALUES('张三','男',1,3,'13998475860','2020-01-01','1.gif'),
('李四','男',3,1,'13698465861','2020-02-02','2.gif'),
('王五','女',2,2,'13498456862','2020-03-03','3.gif'),
('赵六','男',4,1,'13598475863','2020-04-04','4.gif'),
('田七','女',2,5,'13698475864','2020-05-05','2.gif'),
('王八','男',5,4,'13798475865','2020-06-06','3.gif');


TRUNCATE TABLE t_student;
SELECT*FROM t_student;

-- select r.`pk_roomId`, count(s.`s_name`) sn from t_student s right join t_room r on s.`fk_roomId`=r.`pk_roomId` where r.`pk_roomId` = 4group by r.`pk_roomId`;
-- select s.`fk_classId`, count(s.`s_name`) from t_student s Right join t_class c on s.`fk_classId`=c.`pk_classId` where s.`fk_classId` = 1 group by s.`fk_classId`;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
drop table t_user;
CREATE TABLE t_user
(
   pk_userId            INT NOT NULL AUTO_INCREMENT COMMENT '用户编号',
   u_name               VARCHAR(20) COMMENT '用户名',
   u_pwd                VARCHAR(20) COMMENT '用户密码',
   u_realName           VARCHAR(20) COMMENT '用户真实姓名',
   u_grade              ENUM('班主任','房间管理员','系统管理员','超级管理员') COMMENT '用户等级',
   PRIMARY KEY (pk_userId)
);

ALTER TABLE t_user COMMENT '用户管理表';
-- 插入记录
INSERT INTO t_user(u_name,u_pwd,u_realName,u_grade)
VALUES('aaa','123','朱志威','班主任'),
('bbb','234','宋佳斌','房间管理员'),
('ccc','345','蔡果','系统管理员'),
('ddd','456','喻海洋','超级管理员');

truncate table t_user;
SELECT*FROM t_user;

ALTER TABLE t_repair ADD CONSTRAINT FK_Reference_2 FOREIGN KEY (fk_roomId)
      REFERENCES t_room (pk_roomId) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_student ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (fk_classId)
      REFERENCES t_class (pk_classId) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_student ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (fk_roomId)
      REFERENCES t_room (pk_roomId) ON DELETE RESTRICT ON UPDATE RESTRICT;
      
      SELECT * FROM t_student;