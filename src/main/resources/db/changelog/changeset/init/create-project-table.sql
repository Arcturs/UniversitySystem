CREATE TABLE project
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    starting_date TIMESTAMP,
    end_date      TIMESTAMP
);