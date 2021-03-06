DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE news(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  newsHeader  VARCHAR NOT NULL,
  dateTime    TIMESTAMP NOT NULL,
  textnews    TEXT NOT NULL,
  imageName   VARCHAR NOT NULL,
  userId      INTEGER NOT NULL,
  FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX news_unique_user_datetime_idx ON news(userId, dateTime);