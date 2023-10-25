package org.example;
import java.util.Scanner;
import java.util.ArrayList;

public class AccountHandler {
    private String cardHolder;
    private int cardNumber;
    private String cardType;
    private int accountBalance;
    private String pin;
    private String pinCheck;
    ArrayList<AccountHandler> accountsList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public AccountHandler(String cardHolder, int cardNumber, String cardType, int accountBalance, String pin){
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.accountBalance = accountBalance;
        this.pin = pin;
    }

    public AccountHandler(){
        accountsList.add(new AccountHandler("Big J", 1345625, "Gisa", 54050, "1234"));
        accountsList.add(new AccountHandler("Lil J", 9402471, "SenseiCard", 12, "0561"));
        accountsList.add(new AccountHandler("Henry Nataša", 9021991, "Gisa", 750034, "4023"));
        accountsList.add(new AccountHandler("Laure Edmao", 1370133, "SenseiCard", 841558444, "7717"));
        accountsList.add(new AccountHandler("Fran Bala", 9819311, "SenseiCard", 456, "4579"));
        accountsList.add(new AccountHandler("Floris Chidiebele", 2527195, "Gisa", 7603, "9394"));
        accountsList.add(new AccountHandler("Amista Jyoti", 4275578, "Gisa", 3, "4004"));
        accountsList.add(new AccountHandler("Rohan Iris", 3121609, "Gisa", 4500000, "3421"));
    }
    public void menu(){

        boolean looping = true;
        while (looping) {
            System.out.println("What would you like to do?");
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw funds");
            System.out.println("3. Deposit funds");
            System.out.println("4. Open new account");
            System.out.println("5. Check account details");
            System.out.println("6. Remove Card");
            String input = scan.nextLine();

            switch (input) {
                case "1":
                    balanceCheck();
                    break;
                case "2":
                    withdrawal();
                    break;
                case "3":
                    deposit();
                    break;
                case "4":
                    addAccount();
                    break;
                case "5":
                    accountDetails();
                    break;
                case "6":
                    looping = false;
                    break;
            }
        }
    }

    public void beginInteraction(){
        boolean pinCheck = false;
        boolean run = true;
        while(run) {
            System.out.println("Enter your PIN-code:");
            String pin_check = scan.nextLine();
            for (AccountHandler account : accountsList) {
                if (account.pin.equals(pin_check)) {
                    run = false;
                    pinCheck = true;
                    System.out.println("Weclome to Gobbledigook Banking Services, would you like to begin? y/n");
                    String check = scan.nextLine();
                    if (check.equals("y")) {
                        setPinCheck(pin_check);
                        menu();
                    } else if (check.equals("n")) {
                        System.exit(0);
                    } else {
                        System.out.println("Invalid input. Will only accept 'y' or 'n'");
                    }
                }
            }
            if (!pinCheck) {
                System.out.println("Invalid PIN-code!");
            }
        }
    }
    public void addAccount(){
        System.out.println("What is the account holder's name?");
        String cardHolder = scan.nextLine();
        int cardNumber = getPositive("What is the card number?", "Card Number");
        System.out.println("What is the card type?");
        String cardType = scan.nextLine();
        int accountBalance = getPositive("What is the account holder's current bank balance?", "Account balance");
        System.out.println("What is the card's PIN-code");
        String pin = scan.nextLine();
        accountsList.add(new AccountHandler(cardHolder, cardNumber, cardType, accountBalance, pin));
    }
    public void accountDetails(){
        for (AccountHandler account : accountsList){
            if (account.pin.equals(getPinCheck())){
                System.out.println("Account holder is " + account.getCardHolder() + ", card number is " + account.getCardNumber() + ",\nthe card is of " + account.getCardType() + " type and current balance is " + account.getAccountBalance());
            }
        }
    }

    public int getPositive(String message, String varName) {
        int number = -1;
        System.out.println(message);
        while (number < 0) {
            try {
                String input = scan.nextLine();
                number = Integer.parseInt(input);
                if (number < 0) {
                    System.out.println(varName + " cannot be negative");
                }
            } catch (Exception ex) {
                System.out.println(varName + " in numbers please!");
            }
        }
        return number;
    }

    public void balanceCheck(){
        for (AccountHandler account : accountsList){
            if (account.pin.equals(getPinCheck())){
                System.out.println("You have " + account.getAccountBalance() + "kr in your account.");;
            }
        }

    }

    public void withdrawal(){
        for (AccountHandler account : accountsList) {
            if (account.pin.equals(getPinCheck())) {
                System.out.println("How much would you like to withdraw?");
                try {
                    int check = Integer.parseInt(scan.nextLine());
                    if (check < account.accountBalance) {
                        setAccountBalance(account.accountBalance - check);
                        System.out.println("You have successfully withdrawn " + check  + "kr from your account. You now have " + getAccountBalance() + "kr remaining.");
                    }
                    else {
                        System.out.println("You don't have enough coverage for this withdrawal.");
                    }
                }
                catch (Exception ex){
                    System.out.println("Please enter only whole numbers.");
                }
            }
        }
    }

    public void deposit(){
        for (AccountHandler account : accountsList) {
            if (account.pin.equals(getPinCheck())) {
                int hund, fifty, twenty, single;
                try {
                    System.out.println("How many hundred bills would you like to deposit?");
                    hund = Integer.parseInt(scan.nextLine());
                    System.out.println("How many fifty bills would you like to deposit?");
                    fifty = Integer.parseInt(scan.nextLine());
                    System.out.println("How many twenty bills would you like to deposit?");
                    twenty = Integer.parseInt(scan.nextLine());
                    System.out.println("How many coins would you like to deposit?");
                    single = Integer.parseInt(scan.nextLine());
                    int check = (hund*100) + (fifty*50) + (twenty*20) + single;
                    setAccountBalance(account.accountBalance + check);
                    System.out.println("You have successfully deposited " + check  + "kr into your account. You now have " + getAccountBalance() + "kr.");
                }
                catch (Exception ex){
                    System.out.println("Please enter only whole numbers.");
                }
            }
        }
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPinCheck() {
        return pinCheck;
    }

    public void setPinCheck(String pinCheck) {
        this.pinCheck = pinCheck;
    }
}



