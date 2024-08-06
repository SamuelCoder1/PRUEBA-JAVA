package com.riwi.models;

import com.riwi.entities.QualificationEntity;
import com.riwi.persistences.configDB.ConfigDB;
import com.riwi.persistences.imodel.IQualificationByIdModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QualificationByIdModel implements IQualificationByIdModel {
    
    //METODO CREATE
    @Override
    public QualificationEntity create(QualificationEntity request) {

        //SE ABRE LA CONEXION A LA DB
        Connection connection = ConfigDB.openConnection();

        //SE HACE LA SQL QUERY
        String sqlQuery = "INSERT INTO qualifications (qualification, description, id_course, id_student) values (?, ?, ?, ?);";

        try {
            //SE HACE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY Y LE PEDIMOS QUE RETORNE NUESTRAS LLAVES GENERADAS AUTOMATICAMENTE
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //SE TRAEN LOS DATOS CON LOS METODOS GET DE LA ENTIDAD Qualification Y SE ENVIAN
            preparedStatement.setInt(1, request.getQualification());
            preparedStatement.setString(2, request.getDescription());
            preparedStatement.setInt(3,request.getId_course());
            preparedStatement.setInt(4,request.getId_student());

            //SE EJECUTA EL PREPARED STATEMENT
            preparedStatement.execute();

            //SE TRAEN LAS LLAVES GENERADAS
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            //SE HACE UN CICLO WHILE AL RESULT SET PARA QUE NOS TRAIGA EL ID GENERADO
            while (resultSet.next()){
                int generated_id = resultSet.getInt(1);
                request.setId_qualification(generated_id);
            }

            //SE CIERRA EL PREPARED STATEMENT
            preparedStatement.close();

            //SE CIERRA EL RESULT SET
            resultSet.close();

            //SE REALIZA EL CATCH (MANEJO DE ERRORES)
        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }
        //SE CIERRA LA CONEXION A LA DB
        ConfigDB.closeConnection();

        //SE RETORNA LA REQUEST
        return request;
    }

    //METODO DELETE
    @Override
    public boolean delete(Integer id) {

        //SE ABRE LA CONEXION A LA DB
        Connection connection = ConfigDB.openConnection();

        //SE DECLARA UNA VARIABLE BOOLEANA "STATUS"
        boolean status;

        try {
            //SE REALIZA LA SQL QUERY
            String sqlQuery = "DELETE FROM qualifications WHERE id_qualification = ?;";

            //SE REALIZA EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //SE LE ENVIA EL ID A ELIMINAR
            preparedStatement.setInt(1, id);

            //SE EJECUTA EL PREPARED STATEMENT EN LA VARIABLE "STATUS"
            status = preparedStatement.execute();

            //SE CIERRA EL PREPARED STATEMENT
            preparedStatement.close();

            //SE HACE EL CATCH (MANEJO DE ERRORES)
        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }

        //SE CIERRA LA CONEXION A LA DB
        ConfigDB.closeConnection();

        //SE RETORNA LA VARIABLE "STATUS"
        return status;
    }

    //METODO READ BY ID
    @Override
    public Object read(Integer id) {

        //SE ABRE LA CONEXION
        Connection connection = ConfigDB.openConnection();

        //SE HACE UNA INSTANCIA DE LA ENTIDAD Qualification Y SE DECLARA COMO NULL
        QualificationEntity qualification = null;

        try {
            //SE HACE LA SQL QUERY
            String sqlQuery = "SELECT * FROM qualifications WHERE id_qualification = ?;";

            //SE HACE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //SE LE PASA EL ID A LEER
            preparedStatement.setInt(1, id);

            //SE EJECUTA EL PREPARED STATEMENT
            preparedStatement.execute();

            //SE ABRE EL RESULT SET Y SE TRAE EL RESULTADO DEL PREPARED STATEMENT
            ResultSet result = preparedStatement.getResultSet();

            //SE HACE UN CICLO WHILE PARA TRAER TODOS LOS DATOS DEL ESTUDIANTE
            while (result.next()){
                qualification = new QualificationEntity(

                        result.getInt("id_qualification"),

                        result.getInt("qualification"),

                        result.getString("description"),

                        result.getInt("id_course"),

                        result.getInt("id_student")

                );
            }

            //SE CIERRA EL RESULT SET
            result.close();

            //SE CIERRA EL PREPARED STATEMENT
            preparedStatement.close();

            //SE HACE EL CATCH (MANEJO DE ERRORES)
        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }

        //SE CIERRA LA CONEXION A LA DB
        ConfigDB.closeConnection();

        //SE RETORNA EL ESTUDIANTE
        return qualification;
    }

    //METODO UPDATE
    @Override
    public QualificationEntity update(QualificationEntity entityQualification, Integer id) {

        //SE HACE LA SQL QUERY
        String sqlQuery = "UPDATE qualifications SET qualification = ?, description = ?, id_course = ?, id_student = ? WHERE id_qualification = ?;";

        //SE ABRE LA CONEXION A LA DB
        Connection connection = ConfigDB.openConnection();

        //SE DECLARA UNA VARIABLE BOOLEANA "STATUS"
        boolean status = false;

        try {
            //SE ABRE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //SE TRAEN LOS DATOS CON LOS METODOS GET DE LA ENTIDAD Qualification Y SE ENVIAN
            preparedStatement.setInt(1, entityQualification.getQualification());
            preparedStatement.setString(2, entityQualification.getDescription());
            preparedStatement.setInt(3, entityQualification.getId_course());
            preparedStatement.setInt(4, entityQualification.getId_student());
            preparedStatement.setInt(5, id);

            //SE EJECUTA EL PREPARED STATEMENT EN UNA VARIABLE "VALIDATION"
            int validation = preparedStatement.executeUpdate();

            //SE HACE UNA CONDICION QUE VALIDA SI SE ACTUALIZO EL ESTUDIANTE O NO
            if (validation == 1){
                JOptionPane.showMessageDialog(null,"Qualification update successful");
                status = true;
            }else{
                JOptionPane.showMessageDialog(null,"Qualification not found");
            }

            //SE SETEA EL ID AL ESTUDIANTE PARA QUE RETORNE TODOS LOS DATOS DE ESTE MISMO
            entityQualification.setId_qualification(id);

            //SE HACE EL CATCH (MANEJO DE ERRORES)
        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }

        //SE RETORNA EL ESTUDIANTE
        return entityQualification;
    }
}
