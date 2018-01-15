DROP TABLE IF EXISTS `user`;
CREATE TABLE user
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  name     VARCHAR(10) NULL,
  username VARCHAR(10) NULL,
  password VARCHAR(10) NULL,
  gender   VARCHAR(6)  NULL
)
  ENGINE = InnoDB
  COLLATE = utf8_bin;

INSERT INTO test.user (id, name, username, password, gender) VALUES (1, 'Jack', 'jack', 'jack', 'male');
INSERT INTO test.user (id, name, username, password, gender) VALUES (2, 'Jone', 'jone', 'jone', 'female');