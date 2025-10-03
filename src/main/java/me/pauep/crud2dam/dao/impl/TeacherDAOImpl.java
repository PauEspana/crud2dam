package me.pauep.crud2dam.dao.impl;

import me.pauep.crud2dam.dao.TeacherDAO;
import me.pauep.crud2dam.domain.model.Teacher;
import me.pauep.crud2dam.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public Teacher createTeacher(Teacher teacher) {
        final String sql = "INSERT INTO teacher (teacher_name, teacher_surname) VALUES (?, ?)";
        
        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, teacher.getName());
                statement.setString(2, teacher.getSurname());

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    System.out.println("Creating teacher failed, no rows affected.");
                    return null;
                }

                int generatedId = DatabaseManager.getStatementId(statement) == null ? 0 : (int) DatabaseManager.getStatementId(statement);
                if (generatedId == 0) throw new SQLException();
                teacher.setId(generatedId);

                return teacher;
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while creating the teacher");
            return null;
        }
    }

    @Override
    public Teacher updateTeacher(int id, Teacher newTeacher) {
        final String sql = "UPDATE teacher SET teacher_name = ?, teacher_surname = ? WHERE teacher_id = ?";

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newTeacher.getName());
                statement.setString(2, newTeacher.getSurname());
                statement.setInt(3, id);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    return null;
                }

                return getTeacherById(id);
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while updating the teacher with id: " + id);
            return null;
        }
    }

    @Override
    public Teacher deleteTeacher(int id) {
        final String sql = "DELETE FROM teacher WHERE teacher_id = ?";

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                Teacher existing = getTeacherById(id);
                if (existing == null) throw new SQLException();

                statement.setInt(1, id);
                int affected = statement.executeUpdate();
                if (affected == 0) {
                    return null;
                }
                return existing;
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while deleting the teacher with id: " + id);
            return null;
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        final String sql = "SELECT * FROM teacher ORDER BY teacher_id";
        List<Teacher> result = new ArrayList<>();

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        Teacher t = new Teacher();
                        t.setId(resultSet.getInt("teacher_id"));
                        t.setName(resultSet.getString("teacher_name"));
                        t.setSurname(resultSet.getString("teacher_surname"));
                        result.add(t);
                    }
                }
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while fetching all teachers");
        }
        return result;
    }

    @Override
    public Teacher getTeacherById(int id) {
        final String sql = "SELECT * FROM teacher WHERE teacher_id = ?";

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (!resultSet.next()) return null;

                    Teacher t = new Teacher();
                    t.setId(resultSet.getInt("teacher_id"));
                    t.setName(resultSet.getString("teacher_name"));
                    t.setSurname(resultSet.getString("teacher_surname"));

                    return t;
                }
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while finding a teacher with id: " + id);
            return null;
        }
    }
}
