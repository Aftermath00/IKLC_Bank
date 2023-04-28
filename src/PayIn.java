import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayIn {
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
    public void pay(int customer_account, double transfer_amount) {
        try {
            String sql1 = "UPDATE bank_account SET balance = balance + ? WHERE accountNumber = ?";
            PreparedStatement statement1 = DBConnector.connection.prepareStatement(sql1);
            statement1.setDouble(1, transfer_amount);
            statement1.setInt(2, customer_account);
            statement1.executeUpdate(); // execute the SQL statement before closing the PreparedStatement
            statement1.close();
            System.out.println("Pay in successful.");
            System.out.println("Your balance is "+ String.format("%,.2f",getBalance(customer_account)));

            String sql2 = "INSERT INTO transaction (senderAccount, recipientAccount, transferAmount) VALUES (?, ?, ?)";
            PreparedStatement statement2 = DBConnector.connection.prepareStatement(sql2);
            statement2.setInt(1, customer_account);
            statement2.setInt(2, customer_account);
            statement2.setDouble(3, transfer_amount);
            statement2.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
