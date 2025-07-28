package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        Scanner input = new Scanner(System.in);
        var startAt = 1;
        while (startAt < 100) {
            System.out.println(getAnswer(startAt));
            startAt++;
            var userAnswer = input.nextLine().toLowerCase();
            var correctAnswer = getAnswer(startAt).toLowerCase();
            if (!correctAnswer.equals(userAnswer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
        System.out.println("Игра окончена");
    }

    public static String getAnswer(int startAt) {
        return startAt % 3 == 0 && startAt % 5 == 0 ? "FizzBuzz"
                : startAt % 3 == 0 ? "Fizz"
                : startAt % 5 == 0 ? "Buzz"
                : String.valueOf(startAt);
    }
}
