CREATE DATABASE riwiAcademyDB;

use riwiAcademyDB;

CREATE TABLE student(
	id_student int primary key auto_increment not null,
    document_student varchar(20) not null,
    name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar (150) unique not null,
    status enum('ACTIVE','INACTIVE') default 'ACTIVE'
);

CREATE TABLE courses(
	id_course int primary key auto_increment not null,
    name_course varchar(100) not null unique,
    description text
);

CREATE TABLE inscription(
	id_inscription int primary key auto_increment,
    title varchar(50) not null,
    id_course int,
    id_student int,
    FOREIGN KEY (id_course) references courses(id_course) ON DELETE CASCADE, 
    FOREIGN KEY (id_student) references student(id_student) ON DELETE CASCADE
);

CREATE TABLE qualifications(
	id_qualification int primary key auto_increment not null,
    qualification int not null,
    description text,
    id_course int,
    id_student int,
    FOREIGN KEY (id_course) references courses(id_course) ON DELETE CASCADE,
    FOREIGN KEY (id_student) references student(id_student) ON DELETE CASCADE
);

drop table student;