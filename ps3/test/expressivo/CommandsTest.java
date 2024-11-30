package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CommandsTest {

    @Test
    public void testDifferentiate() {
        assertEquals("1", Commands.differentiate("x", "x"));
        assertEquals("0", Commands.differentiate("y", "x"));
        assertEquals("(1.0 + 0.0)", Commands.differentiate("x + 3", "x"));
        assertEquals("((1.0 * y) + (x * 0.0))", Commands.differentiate("x * y", "x"));
    }

    @Test
    public void testSimplify() {
        Map<String, Double> environment = new HashMap<>();
        environment.put("x", 2.0);
        assertEquals("2.0", Commands.simplify("x", environment));
        assertEquals("5.0", Commands.simplify("x + 3", environment));
        assertEquals("(2.0 * 4.0)", Commands.simplify("x * y", environment));
    }
}
