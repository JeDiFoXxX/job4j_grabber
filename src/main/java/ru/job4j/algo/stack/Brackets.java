package ru.job4j.algo.stack;

import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char cur : s.toCharArray()) {
            if (cur == '(' || cur == '{' || cur == '[') {
                stack.push(cur);
            } else {
                char top = !stack.empty() ? stack.pop() : '1';
                if ((cur == ')' && top != '(')
                        || (cur == '}' && top != '{')
                        || (cur == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.empty();
    }
}
