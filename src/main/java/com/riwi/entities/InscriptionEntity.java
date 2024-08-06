package com.riwi.entities;

public class InscriptionEntity {

    //SE INSTANCIAN LOS ATRIBUTOS
    private int id_inscription;
    private String title;
    private int id_course;
    private int id_student;

    //SE HACE UN CONSTRUCTOR VACIO
    public InscriptionEntity() {
    }

    //SE HACE UN CONSTRUCTOR QUE NO PIDA EL ID DE LA INCRIPCION
    public InscriptionEntity(String title, int id_course, int id_student) {
        this.title = title;
        this.id_course = id_course;
        this.id_student = id_student;
    }

    //SE HACE UN CONSTRUCTOR LLENO
    public InscriptionEntity(int id_inscription, String title, int id_course, int id_student) {
        this.id_inscription = id_inscription;
        this.title = title;
        this.id_course = id_course;
        this.id_student = id_student;
    }

    //SE REALIZAN LOS GETTER Y LOS SETTERS
    public int getId_inscription() {
        return this.id_inscription;
    }

    public void setId_inscription(int id_inscription) {
        this.id_inscription = id_inscription;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_course() {
        return this.id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public int getId_student() {
        return this.id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    //SE REALIZA EL TO STRING
    @Override
    public String toString() {
        return "INSCRIPTION " + "\n" +
                "Id_inscription= " + this.id_inscription + "\n" +
                "Title= " + this.title + "\n" +
                "Id_course= " + this.id_course + "\n" +
                "Id_student= " + this.id_student
        ;
    }
}
