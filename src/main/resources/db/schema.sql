CREATE TABLE poll (
    id BIGSERIAL PRIMARY KEY,
    name TEXT,
    start TIMESTAMP,
    finish TIMESTAMP,
    active BOOLEAN
);

CREATE TABLE question (
    id BIGSERIAL PRIMARY KEY,
    content TEXT,
    display_order INT,
    poll_id BIGINT REFERENCES poll(id)
);