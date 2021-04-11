package com.elgin.study.demo.leetcode;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 *  
 *
 * 提示：
 *
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author zxs
 * 2021/3/28
 */
public class AddStrings415 {


    public static void main(String[] args) {
        System.out.println(addStrings("408","5"));
        System.out.println(addStrings1("408","5"));
    }


    public static String addStrings(String num1, String num2) {
        char[] numArr1 = num1.toCharArray();
        char[] numArr2 = num2.toCharArray();
        int len1 = num1.length();
        int len2 = num2.length();
        int maxLen = len1 >= len2 ? len1 : len2;
        int nextAdd = 0;
        char[] charArray = new char[maxLen];
        //迭代次数
        int count = 1 ;

        //最后一位是否要计算进位
        boolean addValue = false;
        while (count <= maxLen){
            boolean paddValue = false;
            int lenValue1 = (len1- count)<0 ? 0 : Integer.valueOf(String.valueOf(numArr1[len1 - count]));
            int lenValue2 = (len2- count)<0 ? 0 : Integer.valueOf(String.valueOf(numArr2[len2 - count]));
            int sum = nextAdd + lenValue1 + lenValue2 ;
            nextAdd = 0;
            if(sum < 10){
                charArray[maxLen - count] = String.valueOf(sum).charAt(0);
            }else {
                nextAdd = 1;
                charArray[maxLen - count] = String.valueOf(sum - 10).charAt(0);
                paddValue = true;
            }
            count++;
            if(count > maxLen && nextAdd == 1 && paddValue){
                addValue = true;
            }
        }

        if(addValue){
            return nextAdd + new String(charArray);
        }

        return new String(charArray);
    }


    /**
     * 双指针
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings1(String num1, String num2){
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;
        StringBuilder sb = new StringBuilder();
        while (i>=0 || j>=0 || add!=0){
            int x = i>=0 ? num1.charAt(i) - '0' : 0;
            int y = j>=0 ? num2.charAt(j) - '0' : 0;
            int result = add + x + y;
            sb.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        sb = sb.reverse();
        return sb.toString();
    }

}
