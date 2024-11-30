/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    // parse():
    //   - Valid inputs: number, variable, addition, multiplication
    //   - Invalid inputs: malformed expressions, unsupported operators
    // toString():
    //   - Number, variable, addition, multiplication
    // equals():
    //   - Same expression, different expressions
    // hashCode():
    //   - Same expression (consistent with equals), different expressions

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // Test cases for parse()
    @Test
    public void testParseNumber() {
        assertEquals(new Number(42), Expression.parse("42"));
        assertEquals(new Number(3.14), Expression.parse("3.14"));
        assertEquals(new Number(0), Expression.parse("0"));
        assertEquals(new Number(1.0), Expression.parse("1.0"));
    }

    @Test
    public void testParseVariable() {
        assertEquals(new Variable("x"), Expression.parse("x"));
        assertEquals(new Variable("y"), Expression.parse("y"));
        assertEquals(new Variable("abc"), Expression.parse("abc"));
        assertEquals(new Variable("XYZ"), Expression.parse("XYZ"));
    }

    @Test
    public void testParseAddition() {
        assertEquals(new Add(new Variable("x"), new Number(5)), Expression.parse("x + 5"));
        assertEquals(new Add(new Number(3), new Variable("y")), Expression.parse("3 + y"));
        assertEquals(new Add(new Number(2.5), new Number(4.5)), Expression.parse("2.5 + 4.5"));
        assertEquals(new Add(new Variable("a"), new Variable("b")), Expression.parse("a + b"));
    }

    @Test
    public void testParseMultiplication() {
        assertEquals(new Multiply(new Variable("x"), new Number(5)), Expression.parse("x * 5"));
        assertEquals(new Multiply(new Number(3), new Variable("y")), Expression.parse("3 * y"));
        assertEquals(new Multiply(new Number(2.5), new Number(4.5)), Expression.parse("2.5 * 4.5"));
        assertEquals(new Multiply(new Variable("a"), new Variable("b")), Expression.parse("a * b"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidExpressionNumber() {
        Expression.parse("3.14.15");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidExpressionVariable() {
        Expression.parse("x123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidExpressionOperator() {
        Expression.parse("x - 5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseIncompleteExpression() {
        Expression.parse("x + ");
    }

    // Test cases for toString()
    @Test
    public void testToStringNumber() {
        assertEquals("42.0", new Number(42).toString());
        assertEquals("3.14", new Number(3.14).toString());
    }

    @Test
    public void testToStringVariable() {
        assertEquals("x", new Variable("x").toString());
        assertEquals("abc", new Variable("abc").toString());
    }

    @Test
    public void testToStringAddition() {
        Expression addExpr = new Add(new Variable("x"), new Number(5));
        assertEquals("(x + 5.0)", addExpr.toString());
    }

    @Test
    public void testToStringMultiplication() {
        Expression multExpr = new Multiply(new Number(3), new Variable("y"));
        assertEquals("(3.0 * y)", multExpr.toString());
    }

    // Test cases for equals()
    @Test
    public void testEqualsNumber() {
        assertEquals(new Number(42), new Number(42));
        assertNotEquals(new Number(42), new Number(43));
    }

    @Test
    public void testEqualsVariable() {
        assertEquals(new Variable("x"), new Variable("x"));
        assertNotEquals(new Variable("x"), new Variable("y"));
    }

    @Test
    public void testEqualsAddition() {
        assertEquals(new Add(new Variable("x"), new Number(5)), new Add(new Variable("x"), new Number(5)));
        assertNotEquals(new Add(new Variable("x"), new Number(5)), new Add(new Variable("y"), new Number(5)));
    }

    @Test
    public void testEqualsMultiplication() {
        assertEquals(new Multiply(new Number(3), new Variable("y")), new Multiply(new Number(3), new Variable("y")));
        assertNotEquals(new Multiply(new Number(3), new Variable("y")), new Multiply(new Variable("x"), new Variable("y")));
    }

    // Test cases for hashCode()
    @Test
    public void testHashCodeNumber() {
        assertEquals(new Number(42).hashCode(), new Number(42).hashCode());
    }

    @Test
    public void testHashCodeVariable() {
        assertEquals(new Variable("x").hashCode(), new Variable("x").hashCode());
    }

    @Test
    public void testHashCodeAddition() {
        Expression addExpr1 = new Add(new Variable("x"), new Number(5));
        Expression addExpr2 = new Add(new Variable("x"), new Number(5));
        assertEquals(addExpr1.hashCode(), addExpr2.hashCode());
    }

    @Test
    public void testHashCodeMultiplication() {
        Expression multExpr1 = new Multiply(new Number(3), new Variable("y"));
        Expression multExpr2 = new Multiply(new Number(3), new Variable("y"));
        assertEquals(multExpr1.hashCode(), multExpr2.hashCode());
    }
}
