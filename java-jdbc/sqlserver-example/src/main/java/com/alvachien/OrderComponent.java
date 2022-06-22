package com.alvachien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.alvachien.util.Common;

public class OrderComponent {
    public void updateOrderQuantity(int orderNumber, String productCode, int newQuantity) throws Exception {
        String query = "UPDATE orderdetails SET quantityOrdered = ? WHERE orderNumber = ? AND productCode = ?";

        try (Connection conn = DriverManager.getConnection(Common.connectionUrl);
            PreparedStatement preparedStatement = conn.prepareStatement(query);) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, orderNumber);
            preparedStatement.setString(3, productCode);

            preparedStatement.executeUpdate();
        }
    }
}
