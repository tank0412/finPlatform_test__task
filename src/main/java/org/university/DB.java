package org.university;

import java.sql.*;

public class DB {
    private static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    public static final String DB_USER = "";
    public static final String DB_PASSWORD = "";

    public static void selectAllStudents() throws SQLException {
        Connection connection = getDBConnection();
        String SelectQuery = "select * from Student";
        PreparedStatement selectPreparedStatement = connection.prepareStatement(SelectQuery);
        ResultSet rs = selectPreparedStatement.executeQuery();
        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " "
                    + rs.getString("surname")+ " " + rs.getString("middleName") + " "
                    + rs.getString("birthdate")+ " " + rs.getString("studentGroup")+ " ");
        }
        selectPreparedStatement.close();
    }

    private static Connection getDBConnection() {
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
}
