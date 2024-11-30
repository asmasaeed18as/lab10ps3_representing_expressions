package expressivo;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * Datatype definition:
 *   Expression = Number(value: double)
 *              + Variable(name: String)
 *              + Add(left: Expression, right: Expression)
 *              + Multiply(left: Expression, right: Expression)
 * 
 * Expression must be immutable and recursive.
 */
public interface Expression {

    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        // Remove any surrounding whitespace
        input = input.trim();

        // Parse a number
        if (input.matches("[0-9]+(\\.[0-9]+)?")) {
            return new Number(Double.parseDouble(input));
        }

        // Parse a variable
        if (input.matches("[a-zA-Z]+")) {
            return new Variable(input);
        }

        // Parse addition (left + right)
        if (input.contains("+")) {
            String[] parts = input.split("\\+", 2);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid addition expression: " + input);
            }
            Expression left = parse(parts[0].trim());
            Expression right = parse(parts[1].trim());
            return new Add(left, right);
        }

        // Parse multiplication (left * right)
        if (input.contains("*")) {
            String[] parts = input.split("\\*", 2);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid multiplication expression: " + input);
            }
            Expression left = parse(parts[0].trim());
            Expression right = parse(parts[1].trim());
            return new Multiply(left, right);
        }

        // If no valid expression could be identified
        throw new IllegalArgumentException("Invalid expression: " + input);
    }

    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);

    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
}
