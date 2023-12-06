CREATE TABLE organisation
(
    id     Long  auto_increment PRIMARY KEY,
    name   VARCHAR(100),
    domain VARCHAR(100)
);

CREATE TABLE users
(
    id              Long   auto_increment PRIMARY KEY,
    name            VARCHAR(100),
    address         VARCHAR(100),
    email           VARCHAR(100),
    phoneNumber     Varchar(100),
    organisation_id Long
);

ALTER TABLE users
    ADD CONSTRAINT fk_organisation_user FOREIGN KEY (organisation_id) REFERENCES organisation (id);

CREATE TABLE emailVerification
(
    id             Long  auto_increment PRIMARY KEY,
    email          VARCHAR(100),
    total_attempts INTEGER,
    code           VARCHAR(100),
    user_id        Long
);

ALTER TABLE emailVerification
    ADD CONSTRAINT fk_user_emailVerification FOREIGN KEY (user_id) REFERENCES users (id);

---Initial Data --
INSERT INTO organisation( name, domain)
values ('Accenture', 'accenture.com');

INSERT INTO users(name, address, email, phoneNumber, organisation_id)
values ('dummy_user', 'Amsterdam', 'dummy@accenture.com', '+31-32483249', 1);

INSERT INTO users(name, address, email, phoneNumber, organisation_id)
values ('dummy_user2', 'Berlin', 'user2@accenture.com', '+3242332483249', 1);

