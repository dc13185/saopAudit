//初始化表结构;
create table cust (id int,name varchar2(32));

insert into cust(id,name)values(10001,'ABC001');
insert into cust(id,name)values(10002,'ABC002');
insert into cust(id,name)values(10003,'ABC003');

create table t_user (id int,name varchar2(32),accountId varchar2(32),passWord varchar2(32),idCard varchar2(32),createDate timestamp,status int,wrongTimes int);

insert into t_user (id,name,accountId,passWord,idCard,createDate,status,wrongTimes) values (1000001,'神人','meteor','abcdefg','350102198802033445',CURRENT_TIMESTAMP,0,0);
insert into t_user (id,name,accountId,passWord,idCard,createDate,status,wrongTimes) values (1000002,'登入检查','checkman','12','350102198802033445',CURRENT_TIMESTAMP,0,0);
insert into t_user (id,name,accountId,passWord,idCard,createDate,status,wrongTimes) values (1000004,'锁定','lockman','12','350102198802033445',CURRENT_TIMESTAMP,0,0);
insert into t_user (id,name,accountId,passWord,idCard,createDate,status,wrongTimes) values (1000003,'被锁定','unlockman','12','350102198802033445',CURRENT_TIMESTAMP,-1,0);
insert into t_user (id,name,accountId,passWord,idCard,createDate,status,wrongTimes) values (1000005,'TOM','tom','12','350102198802033445',CURRENT_TIMESTAMP,0,0);

create sequence SEQ_USER_ID
minvalue 100000000
maxvalue 999999999
start with 100000000
increment by 1
nocache;

create sequence SEQ_ID
minvalue 100000000
maxvalue 999999999
start with 100000000
increment by 1
nocache;

