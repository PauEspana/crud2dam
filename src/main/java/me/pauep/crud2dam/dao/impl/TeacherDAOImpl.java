package me.pauep.crud2dam.dao.impl;

import me.pauep.crud2dam.dao.TeacherDAO;
import me.pauep.crud2dam.model.Teacher;
import me.pauep.crud2dam.util.DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public Teacher createTeacher(Teacher teacher) {
        try (Connection connection = DatabaseManager.getDatabaseConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO teachers (name, surname) VALUES (?, ?)");
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSurname());
            
            statement.execute();
            
            return teacher;
        } catch (SQLException _) {
            System.out.println("There was an issue while creating the teacher");
            return null;
        }
    }

    @Override
    public Teacher updateTeacher(int id, Teacher newTeacher) {
        return null;
    }

    @Override
    public Teacher deleteTeacher(int id) {
        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return List.of();
    }

    @Override
    public Teacher getTeacherById(int id) {
        return null;
    }
}
