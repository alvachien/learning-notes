package com.alvachien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductNavUtility {
    ResultSet resultSet = null;

    public ProductNavUtility(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
    public void printForward() throws Exception {
        while(resultSet.next()) {
            String name = resultSet.getString("productName");
            System.out.println("    " + name);
        }
    }
    public void printFirst() throws Exception {
        if (resultSet.first()) {
            String name = resultSet.getString("productName");
            System.out.println("    " + name);            
        }
    }
    public void printLast() throws Exception {
        if (resultSet.last()) {
            String name = resultSet.getString("productName");
            System.out.println("    " + name);            
        }
    }
    public void printAt(int position) throws Exception {
        if (resultSet.absolute(position)) {
            if (resultSet.first()) {
                String name = resultSet.getString("productName");
                System.out.println("    " + name);            
            }    
        }
    }
    public void printRelative(int position) throws Exception {
        if (resultSet.relative(position)) {
            if (resultSet.first()) {
                String name = resultSet.getString("productName");
                System.out.println("    " + name);            
            }    
        }
    }
    public void pritnReverse() throws Exception {
        resultSet.afterLast();
        while(resultSet.previous()) {
            if (resultSet.first()) {
                String name = resultSet.getString("productName");
                System.out.println("    " + name);            
            }    
        }
    }
}
