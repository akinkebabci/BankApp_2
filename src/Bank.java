import java.util.HashMap;

public class Bank {
    private String name;
    private HashMap<String, Customer> customers = new HashMap<String, Customer>();


    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    public boolean isIBAN(String iban){
        return  customers.containsKey(customers.get(iban));
    }

    public void customerAdd(Customer customer) {
        if (!this.customers.containsKey(customer.getIBAN())) {
            customers.put(customer.getIBAN(), customer);
        }
    }

/*    public void showTransactions() { //
        for (String transaction : customers.keySet()) {
            transaction = customers.get(transaction).getTransactions().peek();
            System.out.println(customers.get(transaction).getFirstName() + " " + customers.get(transaction).getLastName() + "\n" + transaction);
        }
    }*/


    public void withDrawMoney(String IBAN,double balance) throws InsufficientBalanceExceptions {
        Customer customer = customers.get(IBAN);

        if (customer.getBalance()<balance){
            throw new InsufficientBalanceExceptions(customer.getBalance(), balance);
        }
        customer.setBalance(customer.getBalance()-balance);
        customer.addTransactions(/*this.firstName+ " " + this.lastName + "\n" +*/"Para Çekildi " + "Miktar : " + balance);

    }

    public void depositMoney(String IBAN,double balance) {
        Customer customer = customers.get(IBAN);
        customer.setBalance(customer.getBalance()+balance);
        customer.addTransactions(/*this.firstName+ " " + this.lastName + "\n" +*/"Para Yatırıldı " + "Miktar : " + balance);
    }


    public void moneyTransfer(String senderIBAN, String recipientIBAN, double balance)throws InsufficientBalanceExceptions {

        if (!(isIBAN(senderIBAN)) && !(isIBAN(recipientIBAN))) {
            Customer senderCustomer = customers.get(senderIBAN);
            Customer recipientCustomer = customers.get(recipientIBAN);
            if (senderCustomer.getBalance() < balance) {
                throw new InsufficientBalanceExceptions(senderCustomer.getBalance(),balance);
            }
            senderCustomer.setBalance(senderCustomer.getBalance() - balance);
            recipientCustomer.setBalance(recipientCustomer.getBalance() + balance);
            customers.get(senderIBAN).getTransactions().push("Para Gönderildi " + balance);
        }
    }




    @Override
    public String toString() {
        String customersInfo = getClass().getName() + ":" + this.name + "\n";
        for (String c : customers.keySet()) {
            customersInfo += customers.get(c).toString();

        }
        return customersInfo;
    }
}
