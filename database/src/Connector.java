/*
 * Copyright (c) by Christian Trummer
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    public static void main(String[] argv) {

        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.22:1521:orcl",
                    "hr", "hr");

            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, "HR", "%", null);
            while (rs.next()) {
                System.out.println(rs.getString(3));
            }

            Statement stmt = connection.createStatement();
            String sql = "SELECT JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY FROM JOBS";
            rs = stmt.executeQuery(sql);
            // STEP 5: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name

                String job_id = rs.getString("JOB_ID");
                String job_title = rs.getString("JOB_TITLE");

                // Display values
                System.out.print("ID: " + job_id + "\t\t");
                System.out.println(", Job Title: " + job_title);
                // System.out.print(", First: " + first);
                // System.out.println(", Last: " + last);
            }
            rs.close();

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}