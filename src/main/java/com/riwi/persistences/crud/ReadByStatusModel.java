package com.riwi.persistences.crud;

import com.riwi.entities.StudentEntity;

import java.util.List;

public interface ReadByStatusModel<Entity>{
    //INTERFAZ PARA LEER POR STATUS
    public List<StudentEntity> readByStatus(String status);
}
