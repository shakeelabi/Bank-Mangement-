package com.pace.bankmanagement.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pace.bankmanagement.model.Bank;
public class BankDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/Bank";
    private String jdbcUsername = "root";
    private String jdbcPassword = "shakeelabi@123";
    private static final String INSERT_BankDetails_SQL = "INSERT INTO BankDetails" + "  (account_holder,account_number,phone_number,balence) VALUES " +
            " (?, ?, ?, ?); ";
    private static final String SELECT_BankDetails_BY_ID = "select id,account_holder,account_number,phone_number,balence from BankDetails where id =?";
    private static final String SELECT_ALL_BankDetails = "select * from BankDetails";
    private static final String DELETE_BankDetails_SQL = "delete from BankDetails where id = ?;";
    private static final String UPDATE_BankDetails_SQL = "update BankDetails set  account_holder= ?,account_number= ?, phone_number =?, balence= ? where id = ?;";
    public BankDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public void insertBank(Bank bank) throws SQLException {
        System.out.println(INSERT_BankDetails_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BankDetails_SQL)) {
            
            preparedStatement.setString(1, bank.getAccount_holder());
            preparedStatement.setString(2, bank.getAccount_number());
            preparedStatement.setString(3, bank.getPhone_number());
            preparedStatement.setInt(4, bank.getBalence());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
	public Bank selectBank(int id) {
        Bank bank = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BankDetails_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String account_holder = rs.getString("account_holder");
                String  account_number = rs.getString("account_number");
                String phone_number = rs.getString("phone_number");
                int balence=rs.getInt("balence");
                bank = new Bank(id,account_holder,account_number,phone_number,balence);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return bank;
    }

    public List < Bank > selectBankdetails() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Bank > bank = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BankDetails);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int id=rs.getInt("id");
                String account_holder = rs.getString("account_holder");
                String account_number = rs.getString("account_number");
                String phone_number = rs.getString("phone_number");
                int balence = rs.getInt("balence");
                bank.add(new Bank(id, account_holder, account_number, phone_number,balence));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return bank;
    }
    public boolean deleteBank(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_BankDetails_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public boolean updateBank(Bank bank) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_BankDetails_SQL);) {
            
        	  
            statement.setString(1, bank.getAccount_holder());
            statement.setString(2, bank.getAccount_number());
            statement.setString(3, bank.getPhone_number());
            statement.setInt(4, bank.getBalence());
            statement.setInt(5, bank.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
