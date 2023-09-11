package filter.statistics;

class MethodStatistics {

    private final long count;

    private final Modifiers modifiers;

    MethodStatistics(
        final long count,
        final Modifier... modifier
    ) {
        this(count, new Modifiers(modifier));
    }

    MethodStatistics(
        final long count,
        final Modifiers modifiers
    ) {
        this.count = count;
        this.modifiers = modifiers;
    }

    long count() {
        return this.count;
    }

    boolean isInstancePrivate() {
        return this.modifiers.isInstancePrivate();
    }

    boolean isInstancePackagePrivate() {
        return this.modifiers.isInstancePackage();
    }

    boolean isInstancePublicOverridden() {
        return this.modifiers.isInstancePublicOverridden();
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

    public boolean isInstanceProtected() {
        return this.modifiers.isInstanceProtected();
    }

    public boolean isStaticProtected() {
        return this.modifiers.isStaticProtected();
    }

    public boolean isInstanceProtectedOverridden() {
        return this.modifiers.isInstanceProtectedOverridden();
    }
}
