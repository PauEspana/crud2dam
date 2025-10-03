package me.pauep.crud2dam;

import me.pauep.crud2dam.dao.impl.TeacherDAOImpl;
import me.pauep.crud2dam.domain.model.Teacher;
import me.pauep.crud2dam.util.DatabaseManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseManager.getDatabaseConnection();
        TeacherDAOImpl teacherDAO = new TeacherDAOImpl();

        Teacher teacher = new Teacher("tEST", "tol");
        teacherDAO.createTeacher(teacher);
        teacherDAO.getTeacherById(1);
        
        teacherDAO.deleteTeacher(7);
        
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        for (Teacher teacher1 : teachers) {
            System.out.println(teacher1.toString());
        }
    }
}