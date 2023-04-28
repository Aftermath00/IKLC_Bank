import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class Account {
    public String customerName;
    public int accountNumber;
    public double balance;
    public String motherName;
    static ArrayList<Account> accountList;

    public static void loadAccount(){
        accountList = new ArrayList<Account>();
        Account account;

        try {
            Statement stmt = DBConnector.connection.createStatement();

            String sql = "SELECT * FROM bank.bank_account";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                account = new Account();
                account.accountNumber = rs.getInt("accountNumber");
                account.customerName = rs.getString("customerName");
                account.motherName = rs.getString("motherName");
                account.balance = rs.getDouble("balance");

                accountList.add(account);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
