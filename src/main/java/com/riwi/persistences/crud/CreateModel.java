package com.riwi.persistences.crud;

public interface CreateModel<Entity>{
    //INTERFAZ PARA HACER UN CREATE
    Entity create(Entity request);
}
