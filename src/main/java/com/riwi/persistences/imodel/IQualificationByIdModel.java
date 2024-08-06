package com.riwi.persistences.imodel;

import com.riwi.entities.QualificationEntity;
import com.riwi.persistences.crud.CreateModel;
import com.riwi.persistences.crud.DeleteModel;
import com.riwi.persistences.crud.ReadByIdModel;
import com.riwi.persistences.crud.UpdateModel;

public interface IQualificationByIdModel extends
        //SE EXTIENDEN TODAS LAS INTERFACES A LA CALIFICACION
        CreateModel<QualificationEntity>,
        DeleteModel<Integer>,
        UpdateModel<QualificationEntity, Integer>,
        ReadByIdModel<Integer> {
}
