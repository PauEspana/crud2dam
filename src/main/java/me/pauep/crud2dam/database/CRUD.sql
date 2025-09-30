CREATE DATABASE IF NOT EXISTS 2dam_crud;
USE 2dam_crud;

CREATE TABLE IF NOT EXISTS teacher (
                                       id int primary key auto_increment,
                                       name varchar(255) not null,
    surname varchar(255)
    );

CREATE TABLE IF NOT EXISTS degree (
                                      id int primary key auto_increment,
                                      name varchar(255),
    teacher_id int references teacher
    );

INSERT INTO teacher (name, surname)
VALUES ('JOAN', 'COLL'),
       ('DAVID', 'LOZANO'),
       ('JEP', 'MASSANAS');

INSERT INTO degree (name, teacher_id)
VALUES ('Accés a dades', 1),
       ('Programació de serveis i processos', 3),
       ('Programació multimèdia i dispositius mòbils', 2)