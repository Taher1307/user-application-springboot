CREATE TABLE IF NOT EXISTS user
(
    `id`    INT NOT NULL AUTO_INCREMENT,
    `username`  VARCHAR(50) NOT NULL,
    `first_name` VARCHAR(50),
    `last_name`  VARCHAR(50),
    `date_of_birth` VARCHAR(50),
    `marital_status` VARCHAR(50),
    `password` LONGTEXT,
    `otp_time` LONG,
    PRIMARY KEY(id),
    UNIQUE KEY(username)
);