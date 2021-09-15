package com.elgin.study.demo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomDemo {

    private Set<String> set;
    private Random random;

    public RandomDemo(){
        set = new HashSet<>();
        this.random = new Random();
    }

    public static void main(String[] args) {
        RandomDemo randomDemo = new RandomDemo();
        for (int i = 0; i < 999999; i++) {
            System.out.println(randomDemo.generateRandomToken());
        }
    }


    public String generateRandomToken(){
        String randomStr = randomStr();
        while (set.contains(randomStr)){
            randomStr = randomStr();
        }
        set.add(randomStr);
        return randomStr;
    }
    
    private String randomStr(){
        StringBuilder sb = new StringBuilder();
        for(int i=0 ; i<6 ;i++){
            int nextInt = random.nextInt(10);
            sb.append(nextInt);
        }
        return sb.toString();
    }
}
