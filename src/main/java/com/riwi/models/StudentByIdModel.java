package com.riwi.models;

import com.riwi.entities.StudentEntity;
import com.riwi.persistences.configDB.ConfigDB;
import com.riwi.persistences.imodel.IStudentByIdModel;
import com.riwi.utils.StatusEnum;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentByIdModel implements IStudentByIdModel {

    //METODO CREATE
    @Override
    public StudentEntity create(StudentEntity request) {

        //SE ABRE LA CONEXION A LA DB
        Connection connection = ConfigDB.openConnection();

        //SE HACE LA SQL QUERY
        String sqlQuery = "INSERT INTO student(document_student, name, last_name, email, status) values (?, ?, ?, ?, ?);";

        try {
            //SE HACE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY Y LE PEDIMOS QUE RETORNE NUESTRAS LLAVES GENERADAS AUTOMATICAMENTE
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //SE TRAEN LOS DATOS CON LOS METODOS GET DE LA ENTIDAD STUDENT Y SE ENVIAN
            preparedStatement.setString(1, request.getDocument_student());
            preparedStatement.setString(2, request.getName());
            preparedStatement.setString(3,request.getLast_name());
            preparedStatement.setString(4,request.getEmail());
            preparedStatement.setString(5,request.getStatus().name());

            //SE EJECUTA EL PREPARED STATEMENT
            preparedStatement.execute();

            //SE TRAEN LAS LLAVES GENERADAS
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            //SE HACE UN CICLO WHILE AL RESULT SET PARA QUE NOS TRAIGA EL ID GENERADO
            while (resultSet.next()){
                int generated_id = resultSet.getInt(1);
                request.setId_student(generated_id);
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
            String sqlQuery = "DELETE FROM student WHERE id_student = ?;";

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

        //SE HACE UNA INSTANCIA DE LA ENTIDAD STUDENT Y SE DECLARA COMO NULL
        StudentEntity student = null;

        try {
            //SE HACE LA SQL QUERY
            String sqlQuery = "SELECT * FROM student WHERE id_student = ?;";

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
                student = new StudentEntity(

                        result.getInt("id_student"),

                        result.getString("document_student"),

                        result.getString("name"),

                        result.getString("last_name"),

                        result.getString("email"),

                        StatusEnum.valueOf(result.getString("status"))

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
        return student;
    }

    //METODO UPDATE
    @Override
    public StudentEntity update(StudentEntity entityStudent, Integer id) {

        //SE HACE LA SQL QUERY
        String sqlQuery = "UPDATE student SET document_student = ?, name = ?, last_name = ?, email = ?, status = ? WHERE id_student = ?;";

        //SE ABRE LA CONEXION A LA DB
        Connection connection = ConfigDB.openConnection();

        //SE DECLARA UNA VARIABLE BOOLEANA "STATUS"
        boolean status = false;

        try {
            //SE ABRE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //SE TRAEN LOS DATOS CON LOS METODOS GET DE LA ENTIDAD STUDENT Y SE ENVIAN
            preparedStatement.setString(1, entityStudent.getDocument_student());
            preparedStatement.setString(2, entityStudent.getName());
            preparedStatement.setString(3, entityStudent.getLast_name());
            preparedStatement.setString(4, entityStudent.getEmail());
            preparedStatement.setString(5,entityStudent.getStatus().name());
            preparedStatement.setInt(6, id);

            //SE EJECUTA EL PREPARED STATEMENT EN UNA VARIABLE "VALIDATION"
            int validation = preparedStatement.executeUpdate();

            //SE HACE UNA CONDICION QUE VALIDA SI SE ACTUALIZO EL ESTUDIANTE O NO
            if (validation == 1){
                JOptionPane.showMessageDialog(null,"Student update successful");
                status = true;
            }else{
                JOptionPane.showMessageDialog(null,"Student not found");
            }

            //SE SETEA EL ID AL ESTUDIANTE PARA QUE RETORNE TODOS LOS DATOS DE ESTE MISMO
            entityStudent.setId_student(id);

            //SE HACE EL CATCH (MANEJO DE ERRORES)
        } catch (SQLException error) {
            throw new RuntimeException(error.getMessage());
        }

        //SE RETORNA EL ESTUDIANTE
        return entityStudent;
    }

    @Override
    public List<StudentEntity> readAll(int size, int numberPage) {
        List<StudentEntity> StudentEntities = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM student LIMIT ? OFFSET ?; ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (numberPage-1)*size);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                StudentEntity Student = new StudentEntity(
                        result.getInt("id_student"),
                        result.getString("document_student"),
                        result.getString("name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        StatusEnum.valueOf(result.getString("status"))
                );
                StudentEntities.add(Student);
            }
            preparedStatement.close();
            result.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return StudentEntities;
    }

    @Override
    public Object readByEmail(String email) {
        //SE ABRE LA CONEXION
        Connection connection = ConfigDB.openConnection();

        //SE HACE UNA INSTANCIA DE LA ENTIDAD STUDENT Y SE DECLARA COMO NULL
        StudentEntity student = null;

        try {
            //SE HACE LA SQL QUERY
            String sqlQuery = "SELECT * FROM student WHERE email LIKE ?;";

            //SE HACE EL PREPARED STATEMENT Y SE LE PASA LA SQL QUERY
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //SE LE PASA EL EMAIL A LEER
            preparedStatement.setString(1, "%"+email+"%");

            //SE EJECUTA EL PREPARED STATEMENT
            preparedStatement.execute();

            //SE ABRE EL RESULT SET Y SE TRAE EL RESULTADO DEL PREPARED STATEMENT
            ResultSet result = preparedStatement.getResultSet();

            //SE HACE UN CICLO WHILE PARA TRAER TODOS LOS DATOS DEL ESTUDIANTE
            while (result.next()){
                student = new StudentEntity(

                        result.getInt("id_student"),

                        result.getString("document_student"),

                        result.getString("name"),

                        result.getString("last_name"),

                        result.getString("email"),

                        StatusEnum.valueOf(result.getString("status"))

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
        return student;
    }

    @Override
    public List<StudentEntity> readByStatus(String status) {
        List<StudentEntity> studentEntities = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM student where status like ?; ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%"+status+"%");

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                StudentEntity student = new StudentEntity(
                        result.getInt("id_student"),
                        result.getString("document_student"),
                        result.getString("name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        StatusEnum.valueOf(result.getString("status"))
                );
                studentEntities.add(student);
            }
            preparedStatement.close();
            result.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return studentEntities;
    }
}
