CREATE TABLE course
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    weekly_duration FLOAT NOT NULL
);