create DATABASE springdb;

create user 'springdbuser'@'localhost' IDENTIFIED BY 'springdbuser';
create user 'springdbuser'@'%' IDENTIFIED BY 'springdbuser';

GRANT ALL PRIVILEGES ON springdb.* TO 'springdbuser'@'localhost';
GRANT ALL PRIVILEGES ON springdb.* TO 'springdbuser'@'%';