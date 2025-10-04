CREATE DATABASE IF NOT EXISTS 2dam_crud;
USE 2dam_crud;

CREATE TABLE IF NOT EXISTS teacher (
    teacher_id int primary key auto_increment,
    teacher_name varchar(255) not null,
    teacher_surname varchar(255)
);

CREATE TABLE IF NOT EXISTS degree (
    degree_id int primary key auto_increment,
    degree_name varchar(255),
    degree_teacher_id int references teacher ON DELETE SET NULL
);

INSERT INTO teacher (teacher_name, teacher_surname)
VALUES ('JOAN', 'COLL'),
       ('DAVID', 'LOZANO'),
       ('JEP', 'MASSANAS');

INSERT INTO degree (degree_name, degree_teacher_id)
VALUES ('Accés a dades', 1),
       ('Programació de serveis i processos', 3),
       ('Programació multimèdia i dispositius mòbils', 2)