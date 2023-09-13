--liquibase formatted sql
--changeset iyiola.oluwatosin:20230913103500_create_user_table


CREATE TABLE IF NOT EXISTS users(
    id              UUID PRIMARY KEY,
    username        VARCHAR(30) NULL UNIQUE,
    email           VARCHAR(50) NOT NULL UNIQUE,
    role            VARCHAR(20) NOT NULL,
    password        TEXT NOT NULL,
    created_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL,
    updated_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL
);

CREATE INDEX IF NOT EXISTS users_username_index ON users (username);
CREATE INDEX IF NOT EXISTS users_email_index ON users (email);
