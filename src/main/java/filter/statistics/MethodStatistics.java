package filter.statistics;

class MethodStatistics {

    private final long total;

    private final Modifiers modifiers;

    MethodStatistics(
        final long total,
        final Modifier... modifier
    ) {
        this(total, new Modifiers(modifier));
    }

    MethodStatistics(
        final long total,
        final Modifiers modifiers
    ) {
        this.total = total;
        this.modifiers = modifiers;
    }

    long total() {
        return this.total;
    }

    boolean isInstancePrivate() {
        return this.modifiers.isInstancePrivate();
    }

    boolean isInstancePackagePrivate() {
        return this.modifiers.isInstancePackage();
    }

    boolean isInstancePublicOverridden() {
        return this.modifiers.isInstanceOverridden();
    }

    boolean isInstancePublic() {
        return this.modifiers.isInstancePublic();
    }

    boolean isStaticPackagePrivate() {
        return this.modifiers.isStaticPackagePrivate();
    }

    boolean isStaticPublic() {
        return this.modifiers.isStaticPublic();
    }

    boolean isStaticPrivate() {
        return this.modifiers.isStaticPrivate();
    }

    boolean isConstructor() {
        return this.modifiers.isConstructor();
    }

    public boolean isNotFound() {
        return this.modifiers.isNotFound();
    }
}
