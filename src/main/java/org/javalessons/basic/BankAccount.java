package org.javalessons.basic;

public class BankAccount {

    private int coins;

    public BankAccount(int coins) {
        this.coins = coins;
    }

    public void putMoney(int deposit){
        this.coins = this.coins+deposit;
    }

    public void withdrawMoney(int withdraw){
        this.coins = this.coins-withdraw;
    }


    public int getCoins() {
        return coins;
    }

}
