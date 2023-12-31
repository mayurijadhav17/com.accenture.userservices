CREATE TABLE organisation
(
    id     Long auto_increment PRIMARY KEY,
    name   VARCHAR(100),
    domain VARCHAR(100)
);
CREATE TABLE roles
(
    id   Long auto_increment PRIMARY KEY,
    name VARCHAR(100)

);

CREATE TABLE user_details
(
    id              Long auto_increment PRIMARY KEY,
    name            VARCHAR(100),
    email           VARCHAR(100),
    status          VARCHAR(100),
    password        VARCHAR(100),
    role_id Long,
    organisation_id Long
);

ALTER TABLE user_details
    ADD CONSTRAINT fk_organisation_user FOREIGN KEY (organisation_id) REFERENCES organisation (id) ON DELETE CASCADE;
ALTER TABLE user_details
    ADD CONSTRAINT fk_role_user FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE;

CREATE TABLE email_verification
(
    id             Long auto_increment PRIMARY KEY,
    total_Attempts int,
    token          int,
    expiry_Date    DATE,
    user_id        Long
);
ALTER TABLE email_verification
    ADD CONSTRAINT fk_email_user FOREIGN KEY (user_id) REFERENCES user_details (id) ON DELETE CASCADE;

---Initial Data --
INSERT INTO organisation(name, domain)
values ('Accenture', 'accenture.com');

INSERT INTO roles(name)
VALUES ('ROLE_USER');
INSERT INTO roles(name)
VALUES ('ROLE_ADMIN');


