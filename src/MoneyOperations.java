public interface MoneyOperations {
    public void withDrawMoney(double balance) throws InsufficientBalanceExceptions;
    public void depositMoney(double balance);
}
