package com.riwi.persistences.crud;

public interface ReadByIdModel<ID>{
    //INTERFAZ PARA LEER POR ID
    public Object read(ID id);
}
