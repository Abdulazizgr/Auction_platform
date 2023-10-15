public class BanckAccount {
    private int BankAccountID;
    private int UserID;
    private String BankName;
    private String AccountHolderName;
    private int AccountNumber;
    private double Balance;

    public BanckAccount(int bankAccountID, int userID, String bankName, String accountHolderName, int accountNumber,
            double balance) {
        BankAccountID = bankAccountID;
        UserID = userID;
        BankName = bankName;
        AccountHolderName = accountHolderName;
        AccountNumber = accountNumber;
        Balance = balance;
    }

    public int getBankAccountID() {
        return BankAccountID;
    }

    public void setBankAccountID(int bankAccountID) {
        BankAccountID = bankAccountID;
    }

    public int getUserID() {
        return UserID;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getAccountHolderName() {
        return AccountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        AccountHolderName = accountHolderName;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount [BankAccountID=" + BankAccountID + ", UserID=" + UserID + ", BankName=" + BankName
                + ", AccountHolderName=" + AccountHolderName + ", AccountNumber=" + AccountNumber + ", Balance="
                + Balance + "]";
    }
}
