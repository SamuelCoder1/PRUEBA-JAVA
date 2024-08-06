package com.riwi.controllers;

import com.riwi.entities.CourseEntity;
import com.riwi.models.CourseByIdModel;

import java.util.List;

public class CourseController {

    //SE INSTANCIA EL MODELO DEL CURSO
    CourseByIdModel courseModel;
    public CourseController(){
        this.courseModel = new CourseByIdModel();
    }

    //SE REALIZA EL CONTROLADOR DE CREAR CURSO
    public CourseEntity create(String name_course, String description){
        return this.courseModel.create(new CourseEntity(name_course, description));
    }

    //SE REALIZA EL CONTROLADOR DE LEER CURSO
    public Object read(int id){
        return this.courseModel.read(id);
    }

    //SE REALIZA EL CONTROLADOR DE ELIMINAR CURSO
    public Boolean delete(int id){
        return this.courseModel.delete(id);
    }

    //SE REALIZA EL CONTROLADOR DE ACTUALIZAR CURSO
    public CourseEntity update(CourseEntity courseEntity, int id){
        return this.courseModel.update(courseEntity, id);
    }

    //SE REALIZA EL CONTROLADOR DE LEER TODOS LOS CURSOS
    public List<CourseEntity> readAll(int size, int numberPage) {
        return this.courseModel.readAll(size, numberPage);
    }
}
