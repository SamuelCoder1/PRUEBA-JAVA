package com.riwi.persistences.imodel;

import com.riwi.entities.InscriptionEntity;
import com.riwi.persistences.crud.CreateModel;
import com.riwi.persistences.crud.DeleteModel;
import com.riwi.persistences.crud.ReadByIdModel;
import com.riwi.persistences.crud.UpdateModel;

public interface IInscriptionByIdModel extends
        //SE EXTIENDEN TODAS LAS INTERFACES A LA INSCRIPCION
        CreateModel<InscriptionEntity>,
        DeleteModel<Integer>,
        UpdateModel<InscriptionEntity, Integer>,
        ReadByIdModel<Integer> {
}
