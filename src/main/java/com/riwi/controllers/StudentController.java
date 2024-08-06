package com.riwi.controllers;

import com.riwi.entities.StudentEntity;
import com.riwi.models.StudentByIdModel;
import com.riwi.utils.StatusEnum;

import java.util.List;

public class StudentController {
    
    //SE REALIZA LA INSTANCIA DEL MODELO DE ESTUDIANTES
    StudentByIdModel studentModel;
    public StudentController(){
        this.studentModel = new StudentByIdModel();
    }

    //SE REALIZA EL CONTROLADOR DE CREAR ESTUDIANTE
    public StudentEntity create(String document_student, String name, String last_name, String email, StatusEnum status){
        return this.studentModel.create(new StudentEntity(document_student, name,last_name, email, status ));
    }

    //SE REALIZA EL CONTROLADOR DE LEER ESTUDIANTE
    public Object read(int id){
        return this.studentModel.read(id);
    }

    //SE REALIZA EL CONTROLADOR DE ELIMINAR ESTUDIANTE
    public Boolean delete(int id){
        return this.studentModel.delete(id);
    }

    //SE REALIZA EL CONTROLADOR DE ACTUALIZAR ESTUDIANTE
    public StudentEntity update(StudentEntity studentEntity, int id){
        return this.studentModel.update(studentEntity, id);
    }

    //SE REALIZA EL CONTROLADOR DE LEER TODOS LOS ESTUDIANTES
    public List<StudentEntity> readAll(int size, int numberPage) {
        return this.studentModel.readAll(size, numberPage);
    }

    //SE REALIZA EL CONTROLADOR DE LEER ESTUDIANTE POR EMAIL
    public Object readByEmail(String email) {
        return this.studentModel.readByEmail(email);
    }

    //SE REALIZA EL CONTROLADOR DE LEER ESTUDIANTE POR ESTADO
    public List<StudentEntity> readByStatus(String status) {
        return this.studentModel.readByStatus(status);
    }

}
