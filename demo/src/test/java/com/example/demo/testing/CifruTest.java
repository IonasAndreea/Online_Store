package com.example.demo.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CifruTest {
    private Cifru cifru;

    @Test
    void Test(){
        Cifru cifru = new Cifru("ABC");
        assertEquals("BCD", cifru.check(Complexity.WEAK));
    }

    @Test
    void Test1(){
        Cifru cifru = new Cifru("Ana");
        assertEquals("Bob", cifru.check(Complexity.WEAK));
    }

    @Test
    void Test2(){
        Cifru cifru = new Cifru("ANA");
        assertEquals("BOB", cifru.check(Complexity.WEAK));
    }
    @Test
    void Test3(){
        Cifru cifru = new Cifru("JAVA");
        assertEquals("KBWB", cifru.check(Complexity.WEAK));
    }

    @Test
    void Test4(){
        Cifru cifru = new Cifru("JAVA");
        assertEquals("NEZE", cifru.check(Complexity.AVERAGE));
    }

    @Test
    void Test5(){
        Cifru cifru = new Cifru("ABC");
        assertEquals("EFG", cifru.check(Complexity.AVERAGE));
    }

    @Test
    void Test8(){
        Cifru cifru = new Cifru("ZxY");
        assertEquals("CaB", cifru.check(Complexity.AVERAGE));
    }

    @Test
    void Test6(){
        Cifru cifru = new Cifru("XyZ");
        assertEquals("YzA", cifru.check(Complexity.WEAK));
    }

    @Test
    void Test7(){
        Cifru cifru = new Cifru("Anz");
        assertEquals("Erc", cifru.check(Complexity.AVERAGE));
    }



    @Test
    void Test9(){
        Cifru cifru = new Cifru("Ana");
        assertEquals("Huh", cifru.check(Complexity.COMPLEX));
    }

    @Test
    void Test10(){
        Cifru cifru = new Cifru("Azx");
        assertEquals("Hge", cifru.check(Complexity.COMPLEX));
    }

}
