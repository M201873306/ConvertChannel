/*这个SQL由LOW用户操作，第二个SQL*/
/*新建员工表*/
create table "LOW"."EMPLOYEE"
(
	"id" INTEGER not null ,
	"name" VARCHAR(50) not null ,
	"date" DATE,
	"sex" CHAR(10),
	"nativeplace" VARCHAR(50),
	"eduback" CHAR(10),
	primary key("id")
)
storage(initial 1, next 1, minextents 1, fillfactor 0)
;

comment on table "LOW"."EMPLOYEE" is '员工表';

comment on column "LOW"."EMPLOYEE"."id" is '员工编号';

comment on column "LOW"."EMPLOYEE"."name" is '姓名';

comment on column "LOW"."EMPLOYEE"."date" is '出生日期';

comment on column "LOW"."EMPLOYEE"."sex" is '性别';

comment on column "LOW"."EMPLOYEE"."nativeplace" is '籍贯';

comment on column "LOW"."EMPLOYEE"."eduback" is '学历';

/*新建工资表*/
create table "LOW"."PAYROLL"
(
	"id" INTEGER,
	"year" INTEGER,
	"month" TINYINT,
	"salary" DOUBLE(53)
)
storage(initial 1, next 1, minextents 1, fillfactor 0)
;

comment on table "LOW"."PAYROLL" is '工资单';

comment on column "LOW"."PAYROLL"."id" is '员工编号';

comment on column "LOW"."PAYROLL"."year" is '年份';

comment on column "LOW"."PAYROLL"."month" is '月份';

comment on column "LOW"."PAYROLL"."salary" is '工资实发数';

alter table "LOW"."PAYROLL" add constraint foreign key("id") references "LOW"."EMPLOYEE"("id") on update cascade;


/*初始化数据*/
insert "LOW"."EMPLOYEE"("id","name","date","sex","nativeplace","eduback") values(1,'zrq','2018-12-9','男','福建','硕士');
insert "LOW"."EMPLOYEE"("id","name","date","sex","nativeplace","eduback") values(2,'wxk','2018-12-9','男','安徽','硕士');


insert "LOW"."PAYROLL"("id","year","month","salary") values(1,2018,12,8000);
insert "LOW"."PAYROLL"("id","year","month","salary") values(2,2018,12,8000);