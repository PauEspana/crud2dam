package me.pauep.crud2dam.dao.impl;

import me.pauep.crud2dam.dao.DegreeDAO;
import me.pauep.crud2dam.domain.model.Degree;
import me.pauep.crud2dam.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DegreeDAOImpl implements DegreeDAO {
    @Override
    public Degree createDegree(Degree degree) {
        final String sql = "INSERT INTO degree (degree_name, degree_teacher_id) VALUES (?, ?)";

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, degree.getName());
                statement.setInt(2, degree.getTeacherId());

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    System.out.println("Creating degree failed, no rows affected.");
                    return null;
                }

                int generatedId = DatabaseManager.getStatementId(statement) == null ? 0 : (int) DatabaseManager.getStatementId(statement);
                if (generatedId == 0) throw new SQLException();
                degree.setId(generatedId);

                return degree;
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while creating the degree");
            return null;
        }
    }

    @Override
    public Degree updateDegree(int id, Degree newDegree) {
        final String sql = "UPDATE degree SET degree_name = ?, degree_teacher_id = ? WHERE degree_id = ?";

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newDegree.getName());
                statement.setInt(2, newDegree.getTeacherId());
                statement.setInt(3, id);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    return null;
                }

                return getDegreeById(id);
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while updating the degree with id: " + id);
            return null;
        }
    }

    @Override
    public Degree deleteDegreeById(int id) {
        final String sql = "DELETE FROM degree WHERE degree_id = ?";

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                Degree existing = getDegreeById(id);
                if (existing == null) throw new SQLException();

                statement.setInt(1, id);
                int affected = statement.executeUpdate();
                if (affected == 0) {
                    return null;
                }
                return existing;
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while deleting the degree with id: " + id);
            return null;
        }
    }

    @Override
    public List<Degree> getAllDegrees() {
        final String sql = "SELECT * FROM degree ORDER BY degree_id";
        List<Degree> result = new ArrayList<>();

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        Degree d = getDegreeById(resultSet.getInt("degree_id"));
                        result.add(d);
                    }
                }
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while fetching all degrees");
        }
        return result;
    }

    @Override
    public Degree getDegreeById(int id) {
        final String sql = "SELECT * FROM degree WHERE degree_id = ?";

        try (Connection connection = DatabaseManager.getDatabaseConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (!resultSet.next()) return null;

                    Degree d = new Degree();
                    d.setId(resultSet.getInt("degree_id"));
                    d.setName(resultSet.getString("degree_name"));
                    d.setTeacherId(resultSet.getInt("degree_teacher_id"));

                    return d;
                }
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while finding a degree with id: " + id);
            return null;
        }
    }
}
