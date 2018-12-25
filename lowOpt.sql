/*这个SQL由LOW用户操作，第二个SQL*/
/*新建员工表*/
CREATE TABLE "LOW"."EMPLOYEE"
(
"id" INTEGER NOT NULL,
"name" VARCHAR(50) NOT NULL,
"date" DATE,
"sex" CHAR(10),
"nativeplace" VARCHAR(50),
"eduback" CHAR(10),
"term" INTEGER,
"tag" INTEGER DEFAULT 0,
CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;
COMMENT ON TABLE "LOW"."EMPLOYEE" IS '员工表';
COMMENT ON COLUMN "LOW"."EMPLOYEE"."id" IS '员工编号';
COMMENT ON COLUMN "LOW"."EMPLOYEE"."name" IS '姓名';
COMMENT ON COLUMN "LOW"."EMPLOYEE"."date" IS '出生日期';
COMMENT ON COLUMN "LOW"."EMPLOYEE"."sex" IS '性别';
COMMENT ON COLUMN "LOW"."EMPLOYEE"."nativeplace" IS '籍贯';
COMMENT ON COLUMN "LOW"."EMPLOYEE"."eduback" IS '学历';
COMMENT ON COLUMN "LOW"."EMPLOYEE"."term" IS '轮数';
COMMENT ON COLUMN "LOW"."EMPLOYEE"."tag" IS '标志';

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