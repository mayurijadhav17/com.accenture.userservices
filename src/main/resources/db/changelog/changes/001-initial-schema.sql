CREATE TABLE Users
(
    id          Long PRIMARY KEY,
    name        VARCHAR,
    address     VARCHAR,
    email       VARCHAR,
    phonenumber Varchar
);

CREATE TABLE Organisation
(
    id          Long PRIMARY KEY,
    name        VARCHAR,
);

INSERT INTO Organisation(id,name) VALUES (1,'Accenture');