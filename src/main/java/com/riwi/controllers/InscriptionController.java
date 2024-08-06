package com.riwi.controllers;

import com.riwi.entities.InscriptionEntity;
import com.riwi.models.InscriptionByIdModel;

public class InscriptionController {
    //SE INSTANCIA EL MODELO DE LA INSCRIPCION
    InscriptionByIdModel inscriptionModel;
    public InscriptionController(){
        this.inscriptionModel = new InscriptionByIdModel();
    }

    //SE REALIZA EL CONTROLADOR DE CREAR INSCRIPCION
    public InscriptionEntity create(String title, int id_course, int id_student){
        return this.inscriptionModel.create(new InscriptionEntity(title, id_course, id_student));
    }

    //SE REALIZA EL CONTROLADOR DE LEER INSCRIPCION
    public Object read(int id){
        return this.inscriptionModel.read(id);
    }

    //SE REALIZA EL CONTROLADOR DE ELIMINAR INSCRIPCION
    public Boolean delete(int id){
        return this.inscriptionModel.delete(id);
    }

    //SE REALIZA EL CONTROLADOR DE ACTUALIZAR INSCRIPCION
    public InscriptionEntity update(InscriptionEntity inscriptionEntity, int id){
        return this.inscriptionModel.update(inscriptionEntity, id);
    }
}
