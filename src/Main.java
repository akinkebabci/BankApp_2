import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = null;
        try {

            BufferedReader reader = new BufferedReader(new FileReader("info.txt"));

            while (true) {

                String s = reader.readLine();

                if (s == null) {
                    break;
                }

                String[] arr = s.split(":");

                if (arr[0].equals("Bank")) {
                    bank = new Bank(arr[1]);


                } else if (arr[0].equals("Customer")) {

                    String[] arr2 = arr[1].split(" ");
                    double castBalance = Double.parseDouble(arr2[3]);
                    Customer customer = new Customer(arr2[0], arr2[1], arr2[2],castBalance);
                    assert bank != null;
                    bank.customerAdd(customer);
                    //System.out.println(Arrays.toString(banks.get(0).getCustomers().keySet().toArray(new String[0])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        while (true) {

            Scanner getInput = new Scanner(System.in);
            String IBANandBalance;
            String[] arrIBANandBalance;
            double castBalance;
            System.out.println(
                    """
                            --Para çekmek için 1'e Basınız--
                            --Para yatırmak için 2'e Basınız--
                            --Para Transferi için 3'e basınız--
                            --Çıkış yapmak için 4'e basınız--""");

            System.out.print("Lütfen Yapmak İstediğiniz İşlemi Seçiniz : ");

            String input = getInput.nextLine();
            if (input.equals("1")) {
                System.out.print("IBAN ve Miktar Giriniz : ");
                IBANandBalance = getInput.nextLine();
                arrIBANandBalance = IBANandBalance.split(" ");
                castBalance = Double.parseDouble(arrIBANandBalance[1]);

                assert bank != null;
                if (!(bank.isIBAN(arrIBANandBalance[0]))) {
                    try {
                        bank.withDrawMoney(arrIBANandBalance[0],castBalance);

                       /* Customer customer = bank.getCustomers().get(arrIBANandBalance[0]);
                        customer.withDrawMoney(castBalance);*/
                    } catch (InsufficientBalanceExceptions e) {
                        System.out.println(e.getMessage());
                    }


                }

            } else if (input.equals("2")) {
                System.out.print("IBAN ve Miktar Giriniz : ");
                IBANandBalance = getInput.nextLine();
                arrIBANandBalance = IBANandBalance.split(" ");
                castBalance = Double.parseDouble(arrIBANandBalance[1]);

                assert bank != null;
                if (!(bank.isIBAN(arrIBANandBalance[0]))) {
                    bank.depositMoney(arrIBANandBalance[0],castBalance);
                    /*Customer customerIBAN = bank.getCustomers().get(arrIBANandBalance[0]);
                    customerIBAN.depositMoney(castBalance);*/
                }
            } else if (input.equals("3")) {
                System.out.print("Gönderici IBAN / Alıcı IBAN / Tutar Giriniz : ");
                IBANandBalance = getInput.nextLine();
                arrIBANandBalance = IBANandBalance.split(" ");
                castBalance = Double.parseDouble(arrIBANandBalance[2]);
                assert bank != null;
                if (!(bank.isIBAN(arrIBANandBalance[0]))&& !(bank.isIBAN(arrIBANandBalance[1]))) {
                    try {
                        Customer senderCustomer = bank.getCustomers().get(arrIBANandBalance[0]);
                        Customer recipientCustomer = bank.getCustomers().get(arrIBANandBalance[1]);
                        bank.moneyTransfer(senderCustomer.getIBAN(), recipientCustomer.getIBAN(), castBalance);
                    }catch (InsufficientBalanceExceptions e){
                        System.out.println(e.getMessage());
                    }


                }
            } else if (input.equals("4")) {
                System.out.println("Çıkılıyor!!!");

                assert bank != null;
                for (String iban:bank.getCustomers().keySet()) {
                    Customer customer = bank.getCustomers().get(iban);
                    customer.showTransactions();
                    customer.transactionsLogFile("output.txt");
                }

                /*for (HashMap.Entry<String, Customer> set : bank.getCustomers().entrySet()) {
                    Customer customer = set.getValue();
                    customer.showTransactions();
                    customer.transactionsLogFile("out.txt");
                }*/
                break;

            } else {
                System.out.println("Geçersiz Seçim");
            }
        }
    }
}