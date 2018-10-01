package org.javalessons.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        int seed = 14;
        Integer multiplier = 3;

        if (seed > 20 || multiplier.toString() == null){
            fail();
        } else if (seed * multiplier > 100){
            fail();
        } else {
            assertEquals(42, multiplier * seed);
        }
    }

    @Test
    public void shouldIterateCollection(){
        List<String> list = new ArrayList<String>(){{
            add("two");
            add("three");
            add("four");
            add("five");
            add("six");
            add("seven");
            add("foo");

        }};

        for(String s: list){
            if (s.equals("foo")){
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
        // deposit money here
        assertEquals(100 + deposit, bank.getCoins());
    }

    @Test
    public void shouldLetToWithdrawMoneyFromBankAccount(){
        BankAccount bank = new BankAccount(100);

        int moneyToWidthraw = 50;
        // deposit money here
        assertEquals(50 + moneyToWidthraw, bank.getCoins());
    }

    @Test
    public void shouldNotAddDuplicatesToHashMap(){
        HashMap<String, String> map = new HashMap<>();

        map.put("1", "Lena");
        map.put("1", "Olena");

        if (!map.isEmpty()) {
            assertEquals("Olena", map.get("1"));
        }

    }
    @Test
    public void hashmapPutTwoKey()

    {
        HashMap<String, String> map = new HashMap<>();
        map.put("Hello", "Hello");
        map.put("1", "1");
        assertEquals("Hello", map.get("Hello"));
        assertEquals("1", map.get("1"));
        assertFalse(!map.containsKey("1"));
    }
}

