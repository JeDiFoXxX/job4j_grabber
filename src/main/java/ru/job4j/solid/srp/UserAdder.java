package ru.job4j.solid.srp;

import java.util.*;

public class UserAdder {
    List<String> users = new ArrayList<>();
    Map<String, Integer> usersAgeList = new HashMap<>();

    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        users.add(scanner.nextLine());
    }

    public void deleteUser() { }
}
