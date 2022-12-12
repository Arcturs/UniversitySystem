CREATE TABLE lectures
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    social_security_number BIGINT REFERENCES research_associate ON DELETE CASCADE,
    course_id              BIGINT REFERENCES course (id) ON DELETE CASCADE
);