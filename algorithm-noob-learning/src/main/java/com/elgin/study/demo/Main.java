package com.elgin.study.demo;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String origin = sc.next();
        int len = origin.length();
        if(origin.charAt(len - 1) == ','){
            origin = origin.substring(0,len - 1);
        }
        String[] arr = origin.split(",");
        int add = 0;
        int[] result = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int val = Integer.valueOf(arr[i]);
            int addVal = 0;
            if(i == arr.length - 1){
                addVal = val + add + 1;
            }else{
                addVal = val + add;
            }

            int newVal = 0;
            if(addVal > 10){
                add = 1;
                newVal = addVal - 10;
            }else{
                add = 0;
                newVal = addVal;
            }
            result[i] = newVal;
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(result).forEach(x -> {
            sb.append(x).append(",");
        });
        System.out.println(sb.toString().substring(0,sb.length() -1));
    }

}
