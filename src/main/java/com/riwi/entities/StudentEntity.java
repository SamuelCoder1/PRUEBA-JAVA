package com.riwi.entities;

import com.riwi.utils.StatusEnum;

public class StudentEntity {

    //SE INSTANCIAN LOS ATRIBUTOS
    private int id_student;
    private String document_student;
    private String name;
    private String last_name;
    private String email;
    private StatusEnum status;

    //SE HACE UN CONSTRUCTOR VACIO
    public StudentEntity() {
    }

    //SE HACE UN CONSTRUCTOR QUE NO PIDA EL ID DEL ESTUDIANTE
    public StudentEntity(String document_student, String name, String last_name, String email, StatusEnum status) {
        this.document_student = document_student;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.status = status;
    }

    //SE HACE UN CONSTRUCTOR LLENO
    public StudentEntity(int id_student, String document_student, String name, String last_name, String email, StatusEnum status) {
        this.id_student = id_student;
        this.document_student = document_student;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.status = status;
    }

    //SE REALIZAN LOS GETTER Y LOS SETTERS
    public int getId_student() {
        return this.id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getDocument_student() {
        return this.document_student;
    }

    public void setDocument_student(String document_student) {
        this.document_student = document_student;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    //SE REALIZA EL TO STRING
    @Override
    public String toString() {
        return "STUDENT:" + "\n" +
                "Id_student= " + this.id_student + "\n" +
                "Document_student= " + this.document_student + "\n" +
                "Name= " + this.name + "\n" +
                "Last_name= " + this.last_name + "\n" +
                "Email= " + this.email + "\n" +
                "Status= " + this.status
        ;
    }
}
