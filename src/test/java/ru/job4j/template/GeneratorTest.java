package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {
    @Test
    public void whenTemplateHasKeysThenReplaceWithValues() {
        Generator generator = new GeneratorImpl();
        Map<String, String> args = Map.of("key1", "John Cena", "key2", "you");
        String template = "I am a ${key1}, Who are ${key2}? ";
        String expected = "I am a John Cena, Who are you? ";
        assertThat(expected).isEqualTo(generator.produce(template, args));
    }

    @Test
    public void whenTemplateHasMissingKeysThenGetException() {
        Generator generator = new GeneratorImpl();
        Map<String, String> args = Map.of("key1", "John Cena", "key2", "you");
        String template = "I am a ${key3}, Who are ${key4}? ";
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapHasExtraKeysThenGetException() {
        Generator generator = new GeneratorImpl();
        Map<String, String> args = Map.of("key1", "John Cena", "key2", "you", "key3", "your");
        String template = "I am a ${key1}, Who are ${key2}? ";
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}