package filter;

import java.util.Collection;

public class Modifiers {

    private final Collection<Modifier> modifiers;

    Modifiers(final Collection<Modifier> modifiers) {
        this.modifiers = modifiers;
    }

    public boolean isStatic() {
        return this.modifiers.contains(Modifier.STATIC);
    }
}
