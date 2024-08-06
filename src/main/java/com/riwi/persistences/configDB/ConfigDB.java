package com.riwi.persistences.configDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    //SE INICIALIZA UNA CONEXION COMO NULA
    public static Connection conexion = null;


    //SE REALIZA EL METODO PARA ABRIR LA CONEXION
    public static Connection openConnection(){

        //URL DE LA BASE DE DATOS
        String URL = "jdbc:mysql://localhost:3306/riwiAcademyDB";
        //USUARIO DE LA BASE DE DATOS
        String user = "root";
        //CONTRASEÑA DE LA BASE DE DATOS
        String password = "Rlwl2023.";

        try {
            //SE ENVIA EL URL, USUARIO Y CONTRASEÑA A LA CONEXION
            conexion = DriverManager.getConnection(URL, user, password);
            JOptionPane.showMessageDialog(null,"Conecction successful");

            //SE HACE EL CATCH (MANEJO DE ERRORES)
        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }

        //SE RETORNA LA CONEXION
        return conexion;
    }

    //SE HACE EL METODO PARA CERRAR LA CONEXION
    public static void closeConnection(){

        //SE HACE UNA CONDICIONAL PARA SABER SI LA CONEXION EXISTE
        if (conexion != null){

            //SI EXISTE SE CIERRA LA CONEXION
            try {
                conexion.close();
                System.out.println("Conecction close successful");

                //SE HACE CATCH (MANEJO DE ERRORES)
            } catch (SQLException error) {
                throw new RuntimeException(error.getMessage());
            }
        }
    }
}

