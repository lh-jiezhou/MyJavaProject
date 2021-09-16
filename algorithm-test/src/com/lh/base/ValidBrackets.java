package com.lh.base;

import java.util.*;


/**
 * 括号匹配
 *      注意：LinkedList 中 push(); offer() 插入元素的位置
 *      push() 插入 相当于栈;  相当于 offerFirst()
 *      offer() 插入 相当于队列;  相当于 offerLast()
 */
public class ValidBrackets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        System.out.println(isValidStr(str));

    }

    public static boolean isValidStr (String str) {
        // write code here
        if(str.length() == 0 || str.length()%2==1){
            return false;
        }
        char[] arr = str.toCharArray();
        Deque<Character> stack = new LinkedList();

        for(int i=0; i<arr.length; i++){
            if(arr[i] == '<' || arr[i] == '[' || arr[i] == '{' || arr[i] == '('){
                stack.push(arr[i]);
            } else if(stack.isEmpty()) {
                return false;
            } else {
                char c = stack.peek();
                if((arr[i] == ')' && c == '(') || (arr[i] == ']' && c == '[') || (arr[i] == '}' && c == '{') || (arr[i] == '>' && c == '<')){
                    stack.pop();
                } else {
                    return false;
                }

            }
        }
        return stack.isEmpty() ? true: false;
    }
}