package com.example.demo.testing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CifruTest {
    private Cifru cifru;
    @Mock
    private Date dateMock;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void Test(){
        Cifru cifru = new Cifru("ABC", null);
        assertEquals("BCD", cifru.check(Complexity.WEAK));
    }

    @Test
    public void Test1(){
        Cifru cifru = new Cifru("Ana",null);
        assertEquals("Bob", cifru.check(Complexity.WEAK));
    }

    @Test
    public void Test2(){
        Cifru cifru = new Cifru("ANA", null);
        assertEquals("BOB", cifru.check(Complexity.WEAK));
    }
    @Test
    public void Test3(){
        Cifru cifru = new Cifru("JAVA", null);
        assertEquals("KBWB", cifru.check(Complexity.WEAK));
    }

    @Test
    public void Test4(){
        Cifru cifru = new Cifru("JAVA",null);
        assertEquals("NEZE", cifru.check(Complexity.AVERAGE));
    }

    @Test
    public void Test5(){
        Cifru cifru = new Cifru("ABC", null);
        assertEquals("EFG", cifru.check(Complexity.AVERAGE));
    }

    @Test
    public void Test8(){
        Cifru cifru = new Cifru("ZxY", null);
        assertEquals("CaB", cifru.check(Complexity.AVERAGE));
    }

    @Test
    public void Test6(){
        Cifru cifru = new Cifru("XyZ", null);
        assertEquals("YzA", cifru.check(Complexity.WEAK));
    }

    @Test
    public void Test7(){
        Cifru cifru = new Cifru("Anz",null);
        assertEquals("Erc", cifru.check(Complexity.AVERAGE));
    }



    @Test
    public void Test9(){
        Cifru cifru = new Cifru("Ana", null);
        assertEquals("Huh", cifru.check(Complexity.COMPLEX));
    }

   /* @Test
    public void Test10(){
        Cifru cifru = new Cifru("Azx",1 );
        assertEquals("Bay", cifru.check(Complexity.COMPLEX));
    }*/

    @Test
    public void test11 (){
        when(dateMock.getDate()).thenReturn(1);
        Cifru cifru = new Cifru("Abc", dateMock);
        assertEquals("Bcd", cifru.check(Complexity.COMPLEX));

    }


}
