package com.example.labapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
class LabApiApplicationTests {

    BigInteger factorial(int n) {
        return IntStream.rangeClosed(2, n).parallel().mapToObj(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply);
    }

    @Test
    void test() {
        for (int i = 0; i < 10000; i++) {
            long now = System.nanoTime();

            final BigInteger factorial = factorial(i);
            System.out.println("factorial("+i+")="+ factorial);
            System.out.println((System.nanoTime() - now) );
            System.out.println(factorial.toString().length());

        }
//        System.out.println(factorial(1000000));
//        System.out.println((System.nanoTime() - now) * 1.0 / 1_000_000_000);
    }

    @Test
    void test2(){
        String s1 = "asda dsf dhkfhskdq dhkfhskde k";
        String s = Stream.of(s1.split("%d")).max(Comparator.comparingInt(String::length)).orElseThrow();

        char character = s.charAt(0);
        char character1 = s.charAt(s.length()-1);
        String word = character1+s.substring(2) + character;
        String s2 = s1.replace(s, word);
        System.out.println(s2);
    }
}
