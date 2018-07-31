package org.javalessons.basic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void shouldCompare2Numbers(){
        assertTrue( "Fix the condition!", 1 > 100);
    }

    public void shouldCheckConditionals(){
        int seed = 84;
        Integer multiplier = 3;

        if (seed > 20 || multiplier.toString() == null){
            fail();
        } else if (seed * multiplier < 100){
            fail();
        } else {
            assertEquals(42, multiplier * seed);
        }
    }



}
