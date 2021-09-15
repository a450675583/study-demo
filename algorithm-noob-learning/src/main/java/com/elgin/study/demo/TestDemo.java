package com.elgin.study.demo;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 *
 *
 * 有效字符串需满足：
 *
 *
 *
 * 左括号必须用相同类型的右括号闭合。
 *
 * 左括号必须以正确的顺序闭合。
 */
public class TestDemo {

    public static void main(String[] args) {
        System.out.println(isValidStr("()"));
    }

    private static boolean isValidStr(String str){
        int len = str.length();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < len){
            Character character = str.charAt(i++);
            if(character.equals('(') || character.equals('[') || character.equals('{')){
                stack.push(character);
            }else if(character.equals(')')){
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else{
                    return false;
                }
            }else if(character.equals('}')){
                if(!stack.isEmpty() && stack.peek() == '{'){
                    stack.pop();
                }else{
                    return false;
                }
            }else if(character.equals(']')){
                if(!stack.isEmpty() && stack.peek() == '['){
                    stack.pop();
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }
}
