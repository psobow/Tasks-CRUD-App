
DROP DATABASE IF EXISTS databasetest;
DROP USER IF EXISTS 'usertest'@'localhost';

CREATE DATABASE IF NOT EXISTS databasetest;
CREATE USER IF NOT EXISTS 'usertest'@'localhost' IDENTIFIED BY 'witam';
ALTER USER 'usertest'@'localhost' IDENTIFIED WITH mysql_native_password BY 'witam';

GRANT ALL ON databasetest.* TO 'usertest'@'localhost';

SHOW DATABASES;
SELECT USER FROM mysql.user;
SHOW GRANTS FOR 'usertest'@'localhost';


