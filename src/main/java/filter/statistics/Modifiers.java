package filter.statistics;

import java.util.Arrays;
import java.util.Collection;

public class Modifiers {

    private final Collection<Modifier> modifiers;

    Modifiers(Modifier... modifiers) {
        this(Arrays.asList(modifiers));
    }

    Modifiers(final Collection<Modifier> modifiers) {
        this.modifiers = modifiers;
    }

    boolean isInstancePrivate() {
        return this.modifiers.contains(Modifier.PRIVATE)
            && this.modifiers.contains(Modifier.INSTANCE);
    }

    boolean isInstancePackage() {
        return this.modifiers.contains(Modifier.PACKAGE_PRIVATE)
            && this.modifiers.contains(Modifier.INSTANCE);
    }

    boolean isInstanceProtected() {
        return this.modifiers.contains(Modifier.PROTECTED)
            && this.modifiers.contains(Modifier.INSTANCE)
            && !this.modifiers.contains(Modifier.OVERRIDDEN);
    }

    boolean isInstancePublic() {
        return this.modifiers.contains(Modifier.PUBLIC)
            && this.modifiers.contains(Modifier.INSTANCE)
            && !this.modifiers.contains(Modifier.OVERRIDDEN);
    }

    boolean isInstancePublicOverridden() {
        return this.modifiers.contains(Modifier.PUBLIC)
            && this.modifiers.contains(Modifier.INSTANCE)
            && this.modifiers.contains(Modifier.OVERRIDDEN);
    }

    boolean isInstanceProtectedOverridden() {
        return this.modifiers.contains(Modifier.PROTECTED)
            && this.modifiers.contains(Modifier.INSTANCE)
            && this.modifiers.contains(Modifier.OVERRIDDEN);
    }

    boolean isStaticPrivate() {
        return this.modifiers.contains(Modifier.PRIVATE)
            && this.modifiers.contains(Modifier.STATIC);
    }

    boolean isStaticPackagePrivate() {
        return this.modifiers.contains(Modifier.PACKAGE_PRIVATE)
            && this.modifiers.contains(Modifier.STATIC);
    }

    boolean isStaticProtected() {
        return this.modifiers.contains(Modifier.PROTECTED)
            && this.modifiers.contains(Modifier.STATIC);
    }

    boolean isStaticPublic() {
        return this.modifiers.contains(Modifier.PUBLIC)
            && this.modifiers.contains(Modifier.STATIC);
    }

    boolean isConstructor() {
        return this.modifiers.contains(Modifier.CONSTRUCTOR);
    }

    boolean isNotFound() {
        return this.modifiers.contains(Modifier.NOT_FOUND);
    }
}
