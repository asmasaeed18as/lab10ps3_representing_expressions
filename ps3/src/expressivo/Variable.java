package expressivo;

import java.util.Objects;

/** Represents a variable in an expression. */
public class Variable implements Expression {
    private final String name;

    // Abstraction function: represents the variable `name`.
    // Rep invariant: name is a nonempty string of letters.
    // Safety from rep exposure: field is private and final.

    /** Make a new variable. */
    public Variable(String name) {
        this.name = name;
        checkRep();
    }

    private void checkRep() {
        assert name.matches("[a-zA-Z]+");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) return false;
        Variable that = (Variable) obj;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
