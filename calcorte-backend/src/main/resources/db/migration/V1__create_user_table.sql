-- create schema if not exists users;

CREATE TABLE IF NOT EXISTS users
(
    id bigserial NOT NULL,
    email character varying(255),
    full_name character varying(255),
    password character varying(255),
    CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT users_uk UNIQUE (email)
);