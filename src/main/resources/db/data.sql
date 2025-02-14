-- data.sql
-- 程序启动时自动执行的 DML
INSERT INTO t_transfer (from_account, to_account, amount)
VALUES ('AccountA', 'AccountB', 1000),
       ('AccountC', 'AccountD', 2000);
