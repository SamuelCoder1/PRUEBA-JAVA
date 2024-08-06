package com.riwi.persistences.crud;

public interface UpdateModel<Entity,ID>{
    //INTERFAZ PARA HACER UN UPDATE
    public Entity update(Entity request, ID id);
}
