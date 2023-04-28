import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Register {
    private String customerName;
    private int accountNumber;
    private double balance;
    private String motherName;
    private ArrayList<Register> accountList;

    public Register() {
        this.accountList = new ArrayList<Register>();
    }
    public void addAccount(Register register) {
        accountList.add(register);
    }
//     Getter and setter methods for customerName
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter and setter methods for accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Getter and setter methods for balance
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Getter and setter methods for motherName
    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void insertDataRegister(){
        try{
            String sql = "INSERT INTO bank_account (customerName, accountNumber, balance, motherName) VALUES (?,?,?,?);";
            PreparedStatement statement = DBConnector.connection.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setInt(2,accountNumber);
            statement.setDouble(3, balance);
            statement.setString(4, motherName);

            int rowInserted = statement.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Successfully adding new account!");
            }

            statement.close();

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
