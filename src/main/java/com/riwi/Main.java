package com.riwi;

import com.riwi.controllers.CourseController;
import com.riwi.controllers.InscriptionController;
import com.riwi.controllers.QualificationController;
import com.riwi.controllers.StudentController;
import com.riwi.entities.CourseEntity;
import com.riwi.entities.InscriptionEntity;
import com.riwi.entities.QualificationEntity;
import com.riwi.entities.StudentEntity;
import com.riwi.persistences.configDB.ConfigDB;
import com.riwi.utils.StatusEnum;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //SE REALIZA UNA VARIABLE NULA QUE NOS DA LA OPCION DE GESTIONAR LA ACADEMIA
        String optionManage;

        do {
            optionManage = JOptionPane.showInputDialog("" +
                    "Welcome to RIWI ACADEMY \n" +
                    "Options: \n" +
                    "1. Manage Students \n" +
                    "2. Manage Courses \n" +
                    "3. Manage Inscriptions \n" +
                    "4. Manage Qualifications \n" +
                    "5. Exit \n"
            );

            switch (optionManage) {
                case "1" -> {

                    //SE INSTANCIA EL CONTROLADOR
                    StudentController studentController = new StudentController();

                    //SE REALIZA UNA VARIABLE NULA QUE NOS DA LA OPCION DE GESTIONAR LOS ESTUDIANTES
                    String option;

                    do {
                        option = JOptionPane.showInputDialog("" +
                                "Manage Students \n" +
                                "1. Create Student \n" +
                                "2. Delete Student \n" +
                                "3. Read Student \n" +
                                "4. Update Student \n" +
                                "5. Read all Students \n"+
                                "6. Exit \n"
                        );

                        switch (option) {
                            case "1" -> {

                                //SE PIDE QUE INGRESE EL DOCUMENTO DEL ESTUDIANTE
                                String document_student = JOptionPane.showInputDialog("Insert student document");

                                //SE PIDE QUE INGRESE EL NOMBRE DEL ESTUDIANTE
                                String name = JOptionPane.showInputDialog("Insert student name");

                                //SE PIDE QUE INGRESE EL APELLIDO DEL ESTUDIANTE
                                String last_name = JOptionPane.showInputDialog("Insert student last name");

                                //SE PIDE QUE INGRESE EL EMAIL DEL ESTUDIANTE
                                String email = JOptionPane.showInputDialog("Insert student email");

                                //SE PIDE QUE INGRESE EL ESTADO DEL ESTUDIANTE
                                StatusEnum status = (StatusEnum) (JOptionPane.showInputDialog(
                                        null,
                                        "Choose to Status" +
                                                "",
                                        "",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        StatusEnum.values(),
                                        StatusEnum.values()
                                ));

                                //CREAMOS LOS DATOS QUE SE LE PIDIERON AL ADMINISTRADOR Y SE GUARDA EN UNA VARIABLE "RESULT"
                                Object result = studentController.create(document_student, name, last_name, email, status);

                                //SE IMPRIME EL RESULTADO
                                JOptionPane.showMessageDialog(null, result.toString());

                            }
                            case "2" -> {
                                //SE PIDE EL ID DEL ESTUDIANTE A ELIMINAR
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert student id"));
                                //SE ELIMINA EL ESTUDIANTE CON EL ID INGRESADO Y SE GUARDA EN UNA VARIABLE BOOLEANA
                                boolean status = studentController.delete(id);

                                //SE HACE UNA CONDICION
                                if (!status){
                                    //SI EL ESTUDIANTE CON EL ID EXISTE, SE ELIMINA
                                    JOptionPane.showMessageDialog(null, "Delete student successful");
                                } else {
                                    //SI NO, NOS IMPRIME ESTE MENSAJE
                                    JOptionPane.showMessageDialog(null, "Error when deleting student");
                                }
                            }
                            case "3" -> {

                                //SE REALIZA UNA VARIABLE NULA QUE NOS DA LA OPCION DE LEER POR ID O EMAIL
                                String optionRead;

                                do {

                                    //MENU PARA LEER POR ID O ESTUDIANTE
                                    optionRead = JOptionPane.showInputDialog("" +
                                            "Manage Students \n" +
                                            "1. Read By Id \n" +
                                            "2. Read By Email \n" +
                                            "3. Exit \n"
                                    );

                                    switch (optionRead) {
                                        case "1" -> {
                                            //SE PIDE EL ID DEL ESTUDIANTE
                                            int id = Integer.parseInt(JOptionPane.showInputDialog("Insert student id"));
                                            //SE LEE EL ESTUDIANTE CON EL ID ENCONTRADO
                                            Object student = studentController.read(id);
                                            JOptionPane.showMessageDialog(null, student.toString());
                                        }
                                        case "2" -> {
                                            //SE LEE EL ESTUDIANTE CON EL EMAIL ENCONTRADO
                                            String email = JOptionPane.showInputDialog("Insert student email");
                                            Object student = studentController.readByEmail(email);
                                            JOptionPane.showMessageDialog(null,student.toString());
                                        }
                                    }
                                }while (!option.equals("3"));

                            }
                            case "4" -> {

                                //SE PIDE EL ID DEL ESTUDIANTE A ACTUALIZAR
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert the id of the student to update"));

                                //SE LE PIDEN SUS DATOS AL ESTUDIANTE
                                String document_student = JOptionPane.showInputDialog("Insert student document");

                                String name = JOptionPane.showInputDialog("Insert student name");

                                String last_name = JOptionPane.showInputDialog("Insert student last name");

                                String email = JOptionPane.showInputDialog("Insert student email");

                                StatusEnum status = (StatusEnum) (JOptionPane.showInputDialog(
                                        null,
                                        "Choose to Status" +
                                                "",
                                        "",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        StatusEnum.values(),
                                        StatusEnum.values()
                                ));

                                //SE INTANCIA EL ESTUDIANTE Y SE LE PASAN LOS DATOS
                                StudentEntity student = new StudentEntity(document_student, name, last_name, email, status);

                                //SE ACTUALIZA EL ESTUDIANTE Y SE LE PASA EL ID
                                studentController.update(student, id);
                                JOptionPane.showMessageDialog(null, student.toString());
                            }
                            case "5" -> {

                                //SE REALIZA UNA VARIABLE NULA QUE NOS DA LA OPCION PARA LEER TODOO O LEER TODOO POR ESTADO
                                String optionReadAll;

                                do {

                                    //MENU DE LEER TODOO O LEER TODOO POR ESTADO
                                    optionReadAll = JOptionPane.showInputDialog("" +
                                            "Manage Students \n" +
                                            "1. Read All \n" +
                                            "2. Read All By Status \n"+
                                            "3. Exit \n"
                                    );

                                    switch (optionReadAll) {
                                        case "1" -> {
                                            String pageSize = JOptionPane.showInputDialog("How many students for page?");
                                            int numberPage = 1;

                                            String confirm;
                                            do {
                                                List<StudentEntity> studentList = studentController.readAll(Integer.parseInt(pageSize), numberPage);
                                                if (studentList.isEmpty()){
                                                    numberPage--;
                                                }

                                                confirm = JOptionPane.showInputDialog(null, "Page: "+ numberPage + "\n" + studentList
                                                        + "\n" + "prev or next");

                                                if (confirm.equalsIgnoreCase("prev")){
                                                    numberPage--;

                                                    if (numberPage<1){
                                                        numberPage = 1;
                                                    }
                                                } else if (confirm.equalsIgnoreCase("next")) {
                                                    numberPage++;
                                                }

                                            }while(confirm.equalsIgnoreCase("next") || confirm.equalsIgnoreCase("prev"));
                                        }
                                        case "2" -> {
                                            StatusEnum status = (StatusEnum) (JOptionPane.showInputDialog(
                                                    null,
                                                    "Choose to Status" +
                                                            "",
                                                    "",
                                                    JOptionPane.QUESTION_MESSAGE,
                                                    null,
                                                    StatusEnum.values(),
                                                    StatusEnum.values()
                                            ));
                                            Object student = studentController.readByStatus(String.valueOf(status));
                                            JOptionPane.showMessageDialog(null, student.toString());
                                        }
                                    }
                                }while (!option.equals("3"));
                                

                            }
                        }
                    }
                    while (!option.equals("6"));


                }
                case "2" -> {

                    //SE INSTANCIA EL CONTROLADOR
                    CourseController courseController = new CourseController();

                    //SE REALIZA UNA VARIABLE NULA QUE NOS DA LA OPCION DE GESTIONAR LOS CURSOS
                    String option;

                    do {
                        option = JOptionPane.showInputDialog("" +
                                "Manage Courses \n" +
                                "1. Create Course \n" +
                                "2. Delete Course \n" +
                                "3. Read Course \n" +
                                "4. Update Course \n" +
                                "5. Read All Courses \n"+
                                "6. Exit \n"
                        );

                        switch (option) {
                            case "1" -> {

                                String name_course = JOptionPane.showInputDialog("Insert Course name").toLowerCase();

                                String description = JOptionPane.showInputDialog("Insert Course description");

                                Object result = courseController.create(name_course, description);

                                JOptionPane.showMessageDialog(null, result.toString());

                            }
                            case "2" -> {
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert Course id"));
                                boolean status = courseController.delete(id);

                                if (!status) {
                                    JOptionPane.showMessageDialog(null, "Delete Course successful");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error when deleting Course");
                                }
                            }
                            case "3" -> {
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert Course id"));
                                Object course = courseController.read(id);
                                JOptionPane.showMessageDialog(null, course.toString());
                            }
                            case "4" -> {
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert the id of the Course to update"));

                                String name_course = JOptionPane.showInputDialog("Insert Course name");

                                String description = JOptionPane.showInputDialog("Insert Course description");

                                CourseEntity course = new CourseEntity(name_course, description);

                                courseController.update(course, id);
                                JOptionPane.showMessageDialog(null, course.toString());
                            }
                            case "5" -> {

                                String pageSize = JOptionPane.showInputDialog("How many courses for page?");
                                int numberPage = 1;

                                String confirm;
                                do {
                                    List<CourseEntity> courseList = courseController.readAll(Integer.parseInt(pageSize), numberPage);
                                    if (courseList.isEmpty()) {
                                        numberPage--;
                                    }

                                    confirm = JOptionPane.showInputDialog(null, "Page: " + numberPage + "\n" + courseList
                                            + "\n" + "prev or next");

                                    if (confirm.equalsIgnoreCase("prev")) {
                                        numberPage--;

                                        if (numberPage < 1) {
                                            numberPage = 1;
                                        }
                                    } else if (confirm.equalsIgnoreCase("next")) {
                                        numberPage++;
                                    }

                                } while (confirm.equalsIgnoreCase("next") || confirm.equalsIgnoreCase("prev"));
                            }
                        }
                        while (!option.equals("6"));

                    }while (!option.equals("5"));;
                }
                case "3" -> {

                    //SE INSTANCIA EL CONTROLADOR
                    InscriptionController inscriptionController = new InscriptionController();

                    //SE REALIZA UNA VARIABLE NULA QUE NOS DA LA OPCION DE GESTIONAR LAS INSCRIPCIONES
                    String option;

                    do {
                        option = JOptionPane.showInputDialog("" +
                                "Manage Inscriptions \n" +
                                "1. Create Inscription \n" +
                                "2. Delete Inscription \n" +
                                "3. Read Inscription \n" +
                                "4. Update Inscription \n" +
                                "5. Exit \n"
                        );

                        switch (option) {
                            case "1" -> {

                                String title = JOptionPane.showInputDialog("Insert Inscription title");

                                int id_course = Integer.parseInt(JOptionPane.showInputDialog("Insert course id"));

                                int id_student = Integer.parseInt(JOptionPane.showInputDialog("Insert student id"));

                                Object result = inscriptionController.create(title, id_course, id_student);

                                JOptionPane.showMessageDialog(null, result.toString());

                            }
                            case "2" -> {

                                String warning = JOptionPane.showInputDialog("\n" +
                                        "If you delete the registration you will lose your registration history.\n" +
                                        "student's grades, are you sure you want to delete the enrollment? \n" +
                                        "Press 1 to continue \n" +
                                        "Press 2 to cancel");

                                if (warning.equalsIgnoreCase("1")){
                                    int id = Integer.parseInt(JOptionPane.showInputDialog("Insert Course id"));
                                    boolean status = inscriptionController.delete(id);

                                    if (!status) {
                                        JOptionPane.showMessageDialog(null, "Delete Course successful");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error when deleting Course");
                                    }
                                }else{
                                    break;
                                }

                            }
                            case "3" -> {
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert Inscription id"));
                                Object inscription = inscriptionController.read(id);
                                JOptionPane.showMessageDialog(null, inscription.toString());
                            }
                            case "4" -> {
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert the id of the Inscription to update"));

                                String title = JOptionPane.showInputDialog("Insert Inscription title");

                                int id_course = Integer.parseInt(JOptionPane.showInputDialog("Insert course id"));

                                int id_student = Integer.parseInt(JOptionPane.showInputDialog("Insert student id"));

                                InscriptionEntity inscription = new InscriptionEntity(title, id_course, id_student);

                                inscriptionController.update(inscription, id);
                                JOptionPane.showMessageDialog(null, inscription.toString());
                            }
                        }
                    }
                    while (!option.equals("5"));;
                }
                case "4" -> {

                    //SE INSTANCIA EL CONTROLADOR
                    QualificationController qualificationController = new QualificationController();

                    //SE REALIZA UNA VARIABLE NULA QUE NOS DA LA OPCION DE GESTIONAR LAS CALIFICACIONES
                    String option;

                    do {
                        option = JOptionPane.showInputDialog("" +
                                "Manage Qualifications \n" +
                                "1. Create Qualification \n" +
                                "2. Delete Qualification \n" +
                                "3. Read Qualification \n" +
                                "4. Update Qualification \n" +
                                "5. Exit \n"
                        );

                        switch (option) {
                            case "1" -> {

                                int qualification = Integer.parseInt(JOptionPane.showInputDialog("Insert qualification"));

                                if (qualification > 100 || qualification < 0){
                                    JOptionPane.showMessageDialog(null, "The rating range can only be between 0-100");
                                    break;
                                }

                                String description = JOptionPane.showInputDialog("Inset qualification description");

                                int id_course = Integer.parseInt(JOptionPane.showInputDialog("Insert course id"));

                                int id_student = Integer.parseInt(JOptionPane.showInputDialog("Insert student id"));

                                Object result = qualificationController.create(qualification, description, id_course, id_student);

                                JOptionPane.showMessageDialog(null, result.toString());

                            }
                            case "2" -> {
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert Qualification id"));
                                boolean status = qualificationController.delete(id);

                                if (!status){
                                    JOptionPane.showMessageDialog(null, "Delete Qualification successful");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error when deleting Qualification");
                                }
                            }
                            case "3" -> {
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert Qualification id"));
                                Object qualification = qualificationController.read(id);
                                JOptionPane.showMessageDialog(null, qualification.toString());
                            }
                            case "4" -> {
                                int id = Integer.parseInt(JOptionPane.showInputDialog("Insert the id of the Qualification to update"));

                                int qualification = Integer.parseInt(JOptionPane.showInputDialog("Insert qualification"));

                                if (qualification > 100 || qualification < 0){
                                    JOptionPane.showMessageDialog(null, "The rating range can only be between 0-100");
                                    break;
                                }

                                String description = JOptionPane.showInputDialog("Inset qualification description");

                                int id_course = Integer.parseInt(JOptionPane.showInputDialog("Insert course id"));

                                int id_student = Integer.parseInt(JOptionPane.showInputDialog("Insert student id"));

                                QualificationEntity qualificationEntity = new QualificationEntity(qualification, description, id_course, id_student);

                                qualificationController.update(qualificationEntity, id);
                                JOptionPane.showMessageDialog(null, qualificationEntity.toString());
                            }
                        }
                    }
                    while (!option.equals("5"));;
                }
            }
        }
        while (!optionManage.equals("5"));
    }
}