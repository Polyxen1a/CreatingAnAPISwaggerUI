-- liquibase formatted sql

-- changeset polyxenia:2

CREATE TABLE faculties
(
    id         SERIAL,
    name       VARCHAR UNIQUE,
    color      VARCHAR UNIQUE,
    faculty_id INTEGER
);
CREATE TABLE students
(
    id         SERIAL,
    name       VARCHAR,
    age        INTEGER check ( age > 15 ),
    faculty_id INTEGER,
    avatar     INTEGER,
    students   INTEGER[]
);
CREATE TABLE avatars
(
    id         SERIAL,
    filePath   VARCHAR,
    fileSize   INTEGER,
    mediaType  VARCHAR,
    data       SMALLINT[],
    student_id INTEGER
);