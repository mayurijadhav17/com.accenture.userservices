CREATE TABLE organisation
(
    id     Long auto_increment PRIMARY KEY,
    name   VARCHAR(100),
    domain VARCHAR(100)
);

CREATE TABLE user_details
(
    id              Long auto_increment PRIMARY KEY,
    name            VARCHAR(100),
    email           VARCHAR(100),
    organisation_id Long
);

ALTER TABLE user_details
    ADD CONSTRAINT fk_organisation_user FOREIGN KEY (organisation_id) REFERENCES organisation (id) ON DELETE CASCADE;

CREATE TABLE email_verification
(
    id            Long auto_increment PRIMARY KEY,
    totalAttempts INTEGER,
    token         VARCHAR(100),
    expiryDate    DATE,
    user_id       Long
);

---Initial Data --
INSERT INTO organisation(name, domain)
values ('Accenture', 'accenture.com');

INSERT INTO user_details(name, email, organisation_id)
values ('Mayuri', 'mayuri@accenture.com', 1);

INSERT INTO user_details(name, email, organisation_id)
values ('dummy_user2', 'user2@accenture.com', 1);

