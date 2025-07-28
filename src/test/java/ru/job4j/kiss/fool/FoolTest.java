package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static ru.job4j.kiss.fool.Fool.*;

import static org.assertj.core.api.Assertions.assertThat;

class FoolTest {
    @Test
    void whenInputIs1ThenReturn1() {
        assertThat("1").isEqualTo(getAnswer(1));
    }

    @Test
    void whenInputIs3ThenReturnFizz() {
        assertThat("Fizz").isEqualTo(getAnswer(3));
    }

    @Test
    void whenInputIs5ThenReturnBuzz() {
        assertThat("Buzz").isEqualTo(getAnswer(5));
    }

    @Test
    void whenInputIs15ThenReturnFizzBuzz() {
        assertThat("FizzBuzz").isEqualTo(getAnswer(15));
    }
}