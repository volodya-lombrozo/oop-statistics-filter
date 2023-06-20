package filter;

import com.github.javaparser.ast.body.MethodDeclaration;

final class ParsedMethod {

    private final MethodDeclaration method;
    private final ParsedClass klass;

    ParsedMethod(
        final MethodDeclaration method,
        final ParsedClass parent
    ) {
        this.method = method;
        this.klass = parent;
    }

    String name() {
        return String.format(
            "%s.%s.%s",
            this.klass.pckg().orElse(""),
            this.klass.definedName(),
            this.method.getSignature().asString()
        );
    }

    boolean isStatic() {
        return this.method.isStatic();
    }
}
