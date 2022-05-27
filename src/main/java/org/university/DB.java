package org.university;

import org.flywaydb.core.Flyway;

import java.sql.*;
import java.time.LocalDate;

public class DB {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public void selectAllStudents() throws SQLException {
        Connection connection = getDBConnection();
        String SelectQuery = "select * from Student";
        PreparedStatement selectPreparedStatement = connection.prepareStatement(SelectQuery);
        ResultSet rs = selectPreparedStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " "
                    + rs.getString("surname") + " " + rs.getString("middleName") + " "
                    + rs.getString("birthdate") + " " + rs.getString("studentGroup") + " ");
        }
        selectPreparedStatement.close();
    }

    public void deleteStudentById(int id) {
        Connection connection = getDBConnection();
        String SelectQuery = "DELETE from Student WHERE ID = (?)";
        try {
            PreparedStatement deletePreparedStatement = connection.prepareStatement(SelectQuery);
            deletePreparedStatement.setInt(1, id);
            int count = deletePreparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("SUCCESS!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNewStudent(String name, String surname, String middleName, LocalDate birthDate, String groupNumber) {
        Connection connection = getDBConnection();
        String insertQuery = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery);
            int newId = getRowCount() + 1;
            insertPreparedStatement.setInt(1, newId);
            insertPreparedStatement.setNString(2, name);
            insertPreparedStatement.setNString(3, surname);
            insertPreparedStatement.setNString(4, middleName);
            insertPreparedStatement.setDate(5, Date.valueOf(birthDate));
            insertPreparedStatement.setNString(6, groupNumber);

            int count = insertPreparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("SUCCESS!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getRowCount() {
        Connection connection = getDBConnection();
        String selectQuery = "SELECT MAX(id) AS max_id FROM Student";
        int count = 0;
        try {
            PreparedStatement deletePreparedStatement = connection.prepareStatement(selectQuery);
            ResultSet rs = deletePreparedStatement.executeQuery();
            if(rs.next()) {
                count = rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return count;
        }
        return count;
    }

    private Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public void migrateFlyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(DB_CONNECTION, DB_USER, DB_PASSWORD)
                .locations("db/migration")
                .load();
        flyway.migrate();
    }
}
