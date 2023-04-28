import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transfer {
    public double getBalance(int accountNumber) {
        double balance = 0;
        try {
            String sql = "SELECT balance FROM bank_account WHERE accountNumber = ?";
            PreparedStatement statement = DBConnector.connection.prepareStatement(sql);
            statement.setInt(1, accountNumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return balance;
    }
    public void transfer(int customer_account, int recipient_account, double transfer_amount) {
        PreparedStatement statement1;
        try {
            // Get the customer's current balance
            String sql1 = "SELECT balance FROM bank_account WHERE accountNumber = ?";
            statement1 = DBConnector.connection.prepareStatement(sql1);
            statement1.setInt(1, customer_account);
            ResultSet rs1 = statement1.executeQuery();

            if (rs1.next()) {
                double customer_balance = rs1.getDouble("balance");
                if (customer_balance >= transfer_amount) {
                    String sql2 = "UPDATE bank_account SET balance = balance - ? WHERE accountNumber = ?";
                    PreparedStatement statement2 = DBConnector.connection.prepareStatement(sql2);
                    statement2.setDouble(1, transfer_amount);
                    statement2.setInt(2, customer_account);
                    statement2.executeUpdate(); // execute the SQL statement before closing the PreparedStatement
                    statement2.close();

                    String sql3 = "UPDATE bank_account SET balance = balance + ? WHERE accountNumber = ?";
                    PreparedStatement statement3 = DBConnector.connection.prepareStatement(sql3);
                    statement3.setDouble(1, transfer_amount);
                    statement3.setInt(2, recipient_account);
                    statement3.executeUpdate(); // execute the SQL statement before closing the PreparedStatement
                    statement3.close();

                    String sql4 = "INSERT INTO transaction (senderAccount, recipientAccount, transferAmount) VALUES (?, ?, ?)";
                    PreparedStatement statement4 = DBConnector.connection.prepareStatement(sql4);
                    statement4.setInt(1, customer_account);
                    statement4.setInt(2, recipient_account);
                    statement4.setDouble(3, transfer_amount);
                    statement4.executeUpdate();

                    System.out.println("Transfer successful.");

                } else {
                    System.out.println();
                    System.out.println("Transfer failed.");
                    System.out.println("Insufficient balance.");
                    System.out.println("Current balance: " + String.format("%,.2f", customer_balance));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
