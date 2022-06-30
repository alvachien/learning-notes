package com.alvachien;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.alvachien.util.Common;

public class ProductsComponent {
    public boolean tryConnection() throws Exception {
        // Connection conn = DriverManager.getConnection(url);
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  

        Connection connection = DriverManager.getConnection(Common.connectionUrl);
        boolean isValid = connection.isValid(2);

        connection.close();
        return isValid;
    }

    public void printProductList(double lowPrice, double highPrice) throws Exception {

        try( Connection connection = DriverManager.getConnection(Common.connectionUrl);
            PreparedStatement preparedStatement = 
            connection.prepareStatement("SELECT * FROM products WHERE buyPrice BETWEEN ? AND ? ");
            // Statement statement = connection.createStatement();
            // ResultSet resultSet = preparedStatement.executeQuery()
            )
        {
            preparedStatement.setDouble(1, lowPrice);
            preparedStatement.setDouble(2, highPrice);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    String name = resultSet.getString("productName");
                    int quantity = resultSet.getInt("quantityInStock");
                    double price = resultSet.getDouble("buyPrice");
                    //System.out.println(name);
                    System.out.format("%-45s %5d %10.2f%n", name, quantity, price);
                }    
            }
    
        }
    }
    public void listProductsBy(String productLine) throws Exception {
        try( Connection connection = DriverManager.getConnection(Common.connectionUrl);
        CallableStatement callableStatement = connection.prepareCall("{call listProductsFor(?)}"); ) {
            callableStatement.setString(1, productLine);
            boolean success = callableStatement.execute();
            if (success) {
                try(ResultSet resultSet = callableStatement.getResultSet();) {
                    while(resultSet.next()) {
                        String name = resultSet.getString("productName");
                        System.out.println(name);
                    }
                }
            }
        }
    }

    public boolean storeCLOB(String prodLine, InputStreamReader inStream) throws Exception {
        String sqlString = "UPDATE productLines SET htmlDescription = ? WHERE productLine = ?";
        try (Connection connection = DriverManager.getConnection(Common.connectionUrl);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
            preparedStatement.setString(2, prodLine);
            preparedStatement.setCharacterStream(1, inStream);
            preparedStatement.executeUpdate();

            return true;
        }
    }

    public Reader readCLOB(String prodLine) throws Exception {
        String sqlString = "SELECT htmlDescription FROM productLines WHERE productLine = ?";
        try (Connection connection = DriverManager.getConnection(Common.connectionUrl);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
            preparedStatement.setString(1, prodLine);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Reader reader = resultSet.getCharacterStream(1);
                    int chr = 0;
                    while((chr = reader.read()) > 0) {
                        System.out.write(chr);
                    }
                    reader.close();
                    return reader;    
                }

                return null;
            }
        }
    }

    public boolean storeBLOB(String prodLine, FileInputStream inStream) throws Exception {
        String sqlString = "UPDATE productLines SET image = ? WHERE productLine = ?";

        try (Connection connection = DriverManager.getConnection(Common.connectionUrl);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
            preparedStatement.setBinaryStream(1, inStream);
            preparedStatement.setString(2, prodLine);
            preparedStatement.executeUpdate();

            return true;
        }
    }

    public InputStream readBLOB(String prodLine) throws Exception {
        String sqlString = "SELECT image FROM productLines WHERE productLine = ?";
        try (Connection connection = DriverManager.getConnection(Common.connectionUrl);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
            preparedStatement.setString(1, prodLine);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    InputStream stream = resultSet.getBinaryStream(1);
                    return stream;
                }

                return null;
            }
        }

    }
}
