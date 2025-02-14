-- 程序启动时自动执行的 DDL
-- 创建数据表
CREATE TABLE IF NOT EXISTS t_transfer
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_account VARCHAR(255) NOT NULL,
    to_account   VARCHAR(255) NOT NULL,
    amount       INT          NOT NULL
);