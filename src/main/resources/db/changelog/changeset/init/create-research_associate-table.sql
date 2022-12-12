CREATE TABLE research_associate
(
    social_security_number BIGINT PRIMARY KEY,
    name                   VARCHAR(100) NOT NULL,
    email                  VARCHAR(255) NOT NULL,
    field_of_study         TEXT NOT NULL
);