package com.riwi.persistences.crud;

import java.util.List;

public interface ReadAllModel<Entity>{
    //INTERFAZ PARA LEER TODA LA ENTIDAD
    public List<Entity> readAll(int size, int numberPage);
}
