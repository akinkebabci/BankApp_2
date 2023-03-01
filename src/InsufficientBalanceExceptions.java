public class InsufficientBalanceExceptions extends  RuntimeException{
    private double myAccountBalance  ;
    private  double money;

    InsufficientBalanceExceptions(double myAccountBalance,double money){
        this.myAccountBalance = myAccountBalance;
        this.money = money;
    }


    @Override
    public String getMessage() {
        return "Bakiye Yetersiz" + "\n"+
                "Hesap Bakiyeniz " + this.myAccountBalance;
    }
}
