package org.javalessons.basic;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void shouldCompare2Numbers(){
        assertTrue( "Fix the condition!", 1 < 100);
    }

    @Test
    public void shouldCheckConditionals(){
        int seed = 84;
        Integer multiplier = 3;

        if (seed < 20 || multiplier.toString() == null){
            fail();
        } else if (seed * multiplier < 100){
            fail();
        } else {
            assertEquals(252, multiplier * seed);
        }
    }

    @Test
    public void shouldIterateCollection(){
        List<String> list = new ArrayList<String>(){{
            add("one");
            add("two");
            add("three");
            add("four");
            add("five");
            add("six");
            add("seven");
            add("foo");
        }};

        for(String s: list){
            if (s.contains("foo")){
               assertEquals("foo", s);
               return;
            }
        }

        fail();
    }

    @Test
    public void shouldLetToDepositMoneyToBankAccount(){
        BankAccount bank = new BankAccount(100);

        int deposit = 50;
        bank.putMoney(deposit);
        assertEquals(100 + deposit, bank.getCoins());
    }

    @Test
    public void shouldLetToWithdrawMoneyFromBankAccount(){
        BankAccount bank = new BankAccount(100);

        int moneyToWidthraw = 50;
        bank.withdrawMoney(moneyToWidthraw);

        assertEquals(100 - moneyToWidthraw, bank.getCoins());
    }

    @Test
    public void hashmapCheckIfTwoValues(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Tom.Jefferson@gmail.com","Laptop");
        hashMap.put("Don.Trump@gmail.com", "Penthause");
        hashMap.put("Tom.Jefferson@gmail.com","Keyboard");
        hashMap.put("Tom.Jefferson@gmail.com","Mouse");

        assertThat(hashMap, not(IsMapContaining.hasEntry("Tom.Jefferson@gmail.com", "Laptop")));
    }

    @Test
    public void removeValueWithNotExistingKey(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Tom.Jefferson@gmail.com","Laptop");
        hashMap.put("Don.Trump@gmail.com", "Penthause");
        hashMap.remove("Tom.Jefferson@gmail.com");
        assertThat(hashMap, not(IsMapContaining.hasValue("Laptop")));
    }

}