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
    status          VARCHAR(100),
    organisation_id Long
);

ALTER TABLE user_details
    ADD CONSTRAINT fk_organisation_user FOREIGN KEY (organisation_id) REFERENCES organisation (id) ON DELETE CASCADE;

CREATE TABLE email_verification
(
    id             Long auto_increment PRIMARY KEY,
    total_Attempts int,
    token          int,
    expiry_Date    DATE,
    user_id        Long
);
ALTER TABLE email_verification
    ADD CONSTRAINT fk_email_user FOREIGN KEY (user_id) REFERENCES user_details(id) ON DELETE CASCADE;

---Initial Data --
INSERT INTO organisation(name, domain)
values ('Accenture', 'accenture.com');

-- INSERT INTO user_details(name, email,status, organisation_id)
-- values ('Mayuri', 'mayuri@accenture.com','active' 1);
--
-- INSERT INTO user_details(name, email, status,organisation_id)
-- values ('dummy_user2', 'user2@accenture.com','inactive' 1);

