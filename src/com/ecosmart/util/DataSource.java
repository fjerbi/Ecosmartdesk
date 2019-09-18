/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author firas
 */
public class DataSource {

    private static DataSource instance = null;
    public final static String DB_URL = "jdbc:mysql://localhost:3306/ecosmart";
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "";
    private Connection conn;

    /**
     * Constructor
     */
    private DataSource() {

        super();
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println(DB_URL + ": DB Connection created successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public final static DataSource getInstance() {

        if (DataSource.instance == null) {

            DataSource.instance = new DataSource();

        }
        return DataSource.instance;
    }

    public final Connection getConnection() {
        return conn;
    }

}
