package org.javalessons.basic;

public class BankAccount {

    private int coins;

    public BankAccount(int coins) {
        this.coins = coins;
    }

    public void putMoney(int coinsToDeposit){
        this.coins = this.coins + coinsToDeposit;
    }

    public void withdrawMoney(int coinsToWithdraw){
        this.coins = this.coins- coinsToWithdraw;

    }

    public int getCoins() {
        return coins;
    }
}
