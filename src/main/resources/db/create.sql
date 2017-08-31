SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
  id int PRIMARY KEY auto_increment,
  teamName VARCHAR,
  description VARCHAR,
  memberid INTEGER

);

CREATE TABLE IF NOT EXISTS members (
   id int PRIMARY KEY auto_increment,
    memberName VARCHAR,
    teamId INTEGER
);