import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Stack;

public class Customer implements MoneyOperations{
    private String firstName;
    private String lastName;
    private final String IBAN;
    private double balance;
    private Stack<String> transactions = new Stack<String>();

    public Customer(String firstName, String lastName, String IBAN,double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.IBAN = IBAN;
        if (balance<0){
            balance = 0;
        }
        this.balance =balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            balance= 0;
        }
        this.balance = balance;
    }

    public Stack<String> getTransactions() {
        return transactions;
    }

    public void addTransactions(String transactions) {
        this.transactions.push(transactions);
    }

    @Override
    public void depositMoney(double balance) {
        this.balance += balance;
        this.transactions.push(/*this.firstName+ " " + this.lastName + "\n" +*/"Para Yatırıldı " + "Miktar : " + balance);
    }

    @Override
    public void withDrawMoney(double balance) throws InsufficientBalanceExceptions {

        if (this.balance<balance){
            throw new InsufficientBalanceExceptions(this.balance, balance);
        }
        this.balance -= balance;
        this.transactions.push(/*this.firstName+ " " + this.lastName + "\n" +*/"Para Çekildi " + "Miktar : " + balance);

    }

    public void showTransactions() {
        for (int i = transactions.size()-1; i >=0 ; i--) {
            System.out.println(transactions.get(i));
        }

    }

    public void transactionsLogFile(String path) {
        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (int i = transactions.size()-1; i >=0 ; i--) {
                String messageLog = transactions.get(i);
                bufferedWriter.write(this.firstName + " " + this.lastName + "\n" + messageLog + "\n");
            }

            bufferedWriter.close();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" +
                this.firstName + " " +
                this.lastName + " " +
                this.IBAN + "\n";
    }
}
