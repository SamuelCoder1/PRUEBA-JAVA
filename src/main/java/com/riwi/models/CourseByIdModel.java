package com.riwi.models;

import com.riwi.entities.CourseEntity;
import com.riwi.persistences.configDB.ConfigDB;
import com.riwi.persistences.imodel.ICourseByIdModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseByIdModel implements ICourseByIdModel {

    //METODO CREATE
    @Override
    public CourseEntity create(CourseEntity request) {

        //SE ABRE LA CONEXION A LA DB
        Connection connection = ConfigDB.openConnection();

        //SE HACE LA SQL QUERY
        String sqlQuery = "INSERT INTO courses(name_course, description) values (? , ?);";

        try {
            //SE HACE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY Y LE PEDIMOS QUE RETORNE NUESTRAS LLAVES GENERADAS AUTOMATICAMENTE
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //SE TRAEN LOS DATOS CON LOS METODOS GET DE LA ENTIDAD COURSE Y SE ENVIAN
            preparedStatement.setString(1, request.getName_course());
            preparedStatement.setString(2, request.getDescription());

            //SE EJECUTA EL PREPARED STATEMENT
            preparedStatement.execute();

            //SE TRAEN LAS LLAVES GENERADAS
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            //SE HACE UN CICLO WHILE AL RESULT SET PARA QUE NOS TRAIGA EL ID GENERADO
            while (resultSet.next()){
                int generated_id = resultSet.getInt(1);
                request.setId_course(generated_id);
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
            String sqlQuery = "DELETE FROM courses WHERE id_course = ?;";

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

        //SE HACE UNA INSTANCIA DE LA ENTIDAD Course Y SE DECLARA COMO NULL
        CourseEntity course = null;

        try {
            //SE HACE LA SQL QUERY
            String sqlQuery = "SELECT * FROM courses WHERE id_course = ?;";

            //SE HACE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //SE LE PASA EL ID A LEER
            preparedStatement.setInt(1, id);

            //SE EJECUTA EL PREPARED STATEMENT
            preparedStatement.execute();

            //SE ABRE EL RESULT SET Y SE TRAE EL RESULTADO DEL PREPARED STATEMENT
            ResultSet result = preparedStatement.getResultSet();

            //SE HACE UN CICLO WHILE PARA TRAER TODOS LOS DATOS DEL CURSO
            while (result.next()){
                course = new CourseEntity(

                        result.getInt("id_course"),

                        result.getString("name_course"),

                        result.getString("description")

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

        //SE RETORNA EL CURSO
        return course;
    }

    //METODO UPDATE
    @Override
    public CourseEntity update(CourseEntity entityCourse, Integer id) {

        //SE HACE LA SQL QUERY
        String sqlQuery = "UPDATE courses SET name_course = ?, description = ? WHERE id_course = ?;";

        //SE ABRE LA CONEXION A LA DB
        Connection connection = ConfigDB.openConnection();

        //SE DECLARA UNA VARIABLE BOOLEANA "STATUS"
        boolean status = false;

        try {
            //SE ABRE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //SE TRAEN LOS DATOS CON LOS METODOS GET DE LA ENTIDAD Course Y SE ENVIAN
            preparedStatement.setString(1, entityCourse.getName_course());
            preparedStatement.setString(2, entityCourse.getDescription());
            preparedStatement.setInt(3, id);

            //SE EJECUTA EL PREPARED STATEMENT EN UNA VARIABLE "VALIDATION"
            int validation = preparedStatement.executeUpdate();

            //SE HACE UNA CONDICION QUE VALIDA SI SE ACTUALIZO EL CURSO O NO
            if (validation == 1){
                JOptionPane.showMessageDialog(null,"Course update successful");
                status = true;
            }else{
                JOptionPane.showMessageDialog(null,"Course not found");
            }

            //SE SETEA EL ID AL CURSO PARA QUE RETORNE TODOS LOS DATOS DE ESTE MISMO
            entityCourse.setId_course(id);

            //SE HACE EL CATCH (MANEJO DE ERRORES)
        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }

        //SE RETORNA EL CURSO
        return entityCourse;
    }

    @Override
    public List<CourseEntity> readAll(int size, int numberPage) {
        List<CourseEntity> courseEntities = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM courses LIMIT ? OFFSET ?; ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (numberPage-1)*size);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                CourseEntity course = new CourseEntity(
                        result.getInt("id_course"),
                        result.getString("name_course"),
                        result.getString("description")
                );
                courseEntities.add(course);
            }
            preparedStatement.close();
            result.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return courseEntities;
    }


    
}
