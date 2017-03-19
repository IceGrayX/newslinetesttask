DELETE FROM user_roles;
DELETE FROM news;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO news (newsHeader, datetime, textnews, imagename, userid) VALUES
  ('Hello', '2017-03-10 10:00:00', 'asdfasdfasdfadf', 'upload/image1.jpg', 100000),
  ('Hello World', '2017-03-11 11:00:00', 'gsfdfgadsfasdfasdfasdfadf', 'upload/image2.jpg', 100000),
  ('Hello World Wide Web', '2017-03-12 12:00:00', 'rwtresdfgasdfasdfasdfadf', 'upload/image3.jpg', 100000);