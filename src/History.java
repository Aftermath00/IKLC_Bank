import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class History {
    public void showHistory(int customer_account) {
        try {
            String sql = "SELECT transaction.transactionDate, sender.customerName AS 'Sender Name', recipient.customerName AS 'Recipient Name', FORMAT(transaction.transferAmount, 0) AS 'Transfer Amount'\n" +
                    "FROM transaction\n" +
                    "         JOIN bank_account AS sender ON transaction.senderAccount = sender.accountNumber\n" +
                    "         JOIN bank_account AS recipient ON transaction.recipientAccount = recipient.accountNumber\n" +
                    "WHERE transaction.senderAccount = ?";

            PreparedStatement statement = DBConnector.connection.prepareStatement(sql);

            statement.setInt(1, customer_account);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String transactionDate = rs.getString("transactionDate");
                String senderName = rs.getString("Sender Name");
                String recipientName = rs.getString("Recipient Name");
                String transferAmount = rs.getString("Transfer Amount");

                System.out.println();
                System.out.println("Transaction Date  : " + transactionDate);
                System.out.println("Sender Name       : " + senderName);
                System.out.println("Recipient Name    : " + recipientName);
                System.out.println("Transfer Amount   : " + transferAmount);
            }
            rs.close();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
