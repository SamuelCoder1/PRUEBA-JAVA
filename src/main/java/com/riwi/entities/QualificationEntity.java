package com.riwi.entities;

public class QualificationEntity {

    //SE INSTANCIAN LOS ATRIBUTOS
    private int id_qualification;
    private int qualification;
    private String description;
    private int id_course;
    private int id_student;

    //SE HACE UN CONSTRUCTOR VACIO
    public QualificationEntity() {
    }

    //SE HACE UN CONSTRUCTOR QUE NO PIDA EL ID DE LA CALIFICACION
    public QualificationEntity(int qualification, String description, int id_course, int id_student) {
        this.qualification = qualification;
        this.description = description;
        this.id_course = id_course;
        this.id_student = id_student;
    }

    //SE HACE UN CONSTRUCTOR LLENO
    public QualificationEntity(int id_qualification, int qualification, String description, int id_course, int id_student) {
        this.id_qualification = id_qualification;
        this.qualification = qualification;
        this.description = description;
        this.id_course = id_course;
        this.id_student = id_student;
    }

    //SE REALIZAN LOS GETTER Y LOS SETTERS
    public int getId_qualification() {
        return this.id_qualification;
    }

    public void setId_qualification(int id_qualification) {
        this.id_qualification = id_qualification;
    }

    public int getQualification() {
        return this.qualification;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "QUALIFICATIONS: " + "\n" +
                "Id_qualification= " + this.id_qualification + "\n" +
                "Qualification= " + this.qualification + "\n" +
                "Description= " + this.description + "\n" +
                "Id_course=" + this.id_course + "\n" +
                "Id_student=" + this.id_student
        ;
    }
}
