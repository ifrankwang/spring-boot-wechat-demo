DROP TABLE IF EXISTS `user`;
CREATE TABLE user
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  name     VARCHAR(10) NULL
  COMMENT '姓名',
  username VARCHAR(10) NULL
  COMMENT '用户名',
  password VARCHAR(10) NULL
  COMMENT '密码',
  gender   VARCHAR(6)  NULL
  COMMENT '性别',
  open_id  VARCHAR(32) NULL
  COMMENT '微信用户id'
)
  ENGINE = InnoDB
  COLLATE = utf8_bin;

INSERT INTO test.user (id, name, username, password, gender) VALUES (1, 'Jack', 'jack', 'jack', 'male');
INSERT INTO test.user (id, name, username, password, gender) VALUES (2, 'Jone', 'jone', 'jone', 'female');