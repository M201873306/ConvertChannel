/*这个SQL只由SYSDBA实现，且为第一个SQL*/
/*创建low两个用户，并创建数据库pay*/
create user low identified by 123456789;


/*授予LOW该有的系统权限*/
grant CREATE TABLE to "LOW";

grant CREATE VIEW to "LOW";

grant CREATE PROCEDURE to "LOW";

grant CREATE SEQUENCE to "LOW";

grant CREATE TRIGGER to "LOW";

grant CREATE INDEX to "LOW";

grant INSERT TABLE to "LOW";

grant UPDATE TABLE to "LOW";

grant DELETE TABLE to "LOW";

grant SELECT TABLE to "LOW";

grant SELECT ANY TABLE to "LOW";

grant GRANT TABLE to "LOW";

grant INSERT VIEW to "LOW";

grant UPDATE VIEW to "LOW";

grant DELETE VIEW to "LOW";

grant SELECT VIEW to "LOW";

grant GRANT VIEW to "LOW";