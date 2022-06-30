package com.alvachien;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.alvachien.util.Common;

public class HrComponent {
    public int replaceSalesManager(String managerBeingReplaced, String replacementManager) throws Exception {

        try(Connection conn = DriverManager.getConnection(Common.connectionUrl);
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE employees SET reportsTo = ? WHERE reportsTo = ?");) {
            preparedStatement.setString(1, replacementManager);
            preparedStatement.setString(2, managerBeingReplaced);
            int count = preparedStatement.executeUpdate();

            return count;
        }
    }

    public int addEmployee(String lastName, String firstName, String extension, String email,
        String officeCode, String jobTitle) throws Exception {

        try(Connection conn = DriverManager.getConnection(Common.connectionUrl);
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO employees (lastName, firstName, extension, email, officeCode, jobTitle) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, extension);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, officeCode);
            preparedStatement.setString(6, jobTitle);

            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                int autogenkey = 0;
                if (resultSet.next()) {
                    autogenkey = resultSet.getInt(1);
                }
                return autogenkey;
            }
        }    
    }

    public boolean deleteEmployee(String employeeNumber) throws Exception {

        try(Connection conn = DriverManager.getConnection(Common.connectionUrl);
            PreparedStatement preparedStatement = conn.prepareStatement(
                "DELETE FROM employees WHERE employeeNumber = ?");) {
            preparedStatement.setString(1, employeeNumber);
            int count = preparedStatement.executeUpdate();
            return count > 0 ? true: false;
        }
    }

    public String updateEmail(int employeeNumber, String newEmail) throws Exception {
        try(Connection conn = DriverManager.getConnection(Common.connectionUrl);
        CallableStatement callableStatement = conn.prepareCall("{call updateEmail(?, ?)}"); ) {
            callableStatement.setInt(1, employeeNumber);
            callableStatement.registerOutParameter(2, java.sql.Types.NVARCHAR);
            callableStatement.setString(2, newEmail);
            callableStatement.execute();

            String oldEmail = callableStatement.getString(2);
            return oldEmail;
        }
    }
}
