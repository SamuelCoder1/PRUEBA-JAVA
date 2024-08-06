package com.riwi.persistences.imodel;

import com.riwi.entities.CourseEntity;
import com.riwi.persistences.crud.*;

public interface ICourseByIdModel extends
        //SE EXTIENDEN TODAS LAS INTERFACES AL CURSO
        CreateModel<CourseEntity>,
        DeleteModel<Integer>,
        UpdateModel<CourseEntity, Integer>,
        ReadByIdModel<Integer>,
        ReadAllModel<CourseEntity> {
}
