package expressivo;

import java.util.Objects;

/** Represents a multiplication operation in an expression. */
public class Multiply implements Expression {
    private final Expression left;
    private final Expression right;

    // Abstraction function: represents the product of `left` and `right`.
    // Rep invariant: left and right are not null.
    // Safety from rep exposure: fields are private, final, and immutable.

    /** Make a new multiplication operation. */
    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        checkRep();
    }

    private void checkRep() {
        assert left != null && right != null;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " * " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Multiply)) return false;
        Multiply that = (Multiply) obj;
        return this.left.equals(that.left) && this.right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
