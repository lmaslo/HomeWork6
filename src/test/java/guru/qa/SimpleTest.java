package guru.qa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс с простыми тестами")
public class SimpleTest {

    @Test
    @DisplayName("Green test")
        // Название теста, можно RU и с пробелами
    void simpleGreenTest() {
        assertTrue(3 > 2); //assertTrue - проверяет выражение в скобках истеное или ложное
    }

    @Test
    @DisplayName("Red test")
    void simpleRedTest() {
        assertTrue(3 < 2);
    }

    @Test
    @Disabled("bug Jira-1234") // игнор теста, можно и к классу
    void simpleBrokenTest() {
        throw new IllegalStateException("Broken");
    }


}
