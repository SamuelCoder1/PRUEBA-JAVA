package com.riwi.persistences.imodel;

import com.riwi.entities.StudentEntity;
import com.riwi.persistences.crud.*;

public interface IStudentByIdModel extends
        //SE EXTIENDEN TODAS LAS INTERFACES AL ESTUDIANTE
        CreateModel<StudentEntity>,
        DeleteModel<Integer>,
        UpdateModel<StudentEntity, Integer>,
        ReadByIdModel<Integer>,
        ReadAllModel<StudentEntity>,
        ReadByEmail<String>,
        ReadByStatusModel<StudentEntity> {
}
