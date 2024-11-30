package expressivo;

import java.util.Objects;

/** Represents a constant numeric value in an expression. */
public class Number implements Expression {
    private final double value;

    // Abstraction function: represents the constant value `value`.
    // Rep invariant: value must be finite (not NaN or infinite).
    // Safety from rep exposure: field is private and final.

    /** Make a new number. */
    public Number(double value) {
        this.value = value;
        checkRep();
    }

    private void checkRep() {
        assert Double.isFinite(value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Number)) return false;
        Number that = (Number) obj;
        return Double.compare(this.value, that.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
