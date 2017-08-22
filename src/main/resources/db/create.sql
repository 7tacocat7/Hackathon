SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
  teamId int PRIMARY KEY auto_increment,
  teamName VARCHAR,
  description VARCHAR

);

CREATE TABLE IF NOT EXISTS members (
    memberId int PRIMARY KEY auto_increment,
    memberName VARCHAR
);