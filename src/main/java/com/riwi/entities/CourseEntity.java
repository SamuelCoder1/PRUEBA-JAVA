package com.riwi.entities;

public class CourseEntity {

    //SE INSTANCIAN LOS ATRIBUTOS
    private int id_course;
    private String name_course;
    private String description;

    //SE HACE UN CONSTRUCTOR VACIO
    public CourseEntity() {
    }

    //SE HACE UN CONSTRUCTOR QUE NO PIDA EL ID DEL CURSO
    public CourseEntity(String name_course, String description) {
        this.name_course = name_course;
        this.description = description;
    }

    //SE HACE UN CONSTRUCTOR LLENO
    public CourseEntity(int id_course, String name_course, String description) {
        this.id_course = id_course;
        this.name_course = name_course;
        this.description = description;
    }

    //SE REALIZAN LOS GETTER Y LOS SETTERS
    public int getId_course() {
        return this.id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public String getName_course() {
        return this.name_course;
    }

    public void setName_course(String name_course) {
        this.name_course = name_course;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //SE REALIZA EL TO STRING
    @Override
    public String toString() {
        return "COURSE: " + "\n" +
                "Id_course= " + this.id_course + "\n" +
                "Name_course= " + this.name_course + "\n" +
                "Description= " + this.description
        ;
    }
}
