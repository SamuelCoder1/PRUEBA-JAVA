package com.riwi.controllers;

import com.riwi.entities.QualificationEntity;
import com.riwi.models.QualificationByIdModel;

public class QualificationController {
    
    //SE REALIZA LA INSTANCIA DEL MODELO DE CALIFICACIONES
    QualificationByIdModel qualificationModel;
    public QualificationController(){
        this.qualificationModel = new QualificationByIdModel();
    }

    //SE REALIZA EL CONTROLADOR DE CREAR CALIFICACION
    public QualificationEntity create(int qualification, String description, int id_course, int id_student){
        return this.qualificationModel.create(new QualificationEntity(qualification, description, id_course, id_student));
    }

    //SE REALIZA EL CONTROLADOR DE LEER CALIFICACION
    public Object read(int id){
        return this.qualificationModel.read(id);
    }

    //SE REALIZA EL CONTROLADOR DE ELIMINAR CALIFICACION
    public Boolean delete(int id){
        return this.qualificationModel.delete(id);
    }

    //SE REALIZA EL CONTROLADOR DE ACTUALIZAR CALIFICACION
    public QualificationEntity update(QualificationEntity QualificationEntity, int id){
        return this.qualificationModel.update(QualificationEntity, id);
    }
}
