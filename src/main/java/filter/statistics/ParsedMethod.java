package filter.statistics;

import com.github.javaparser.ast.body.MethodDeclaration;
import java.util.HashSet;
import java.util.Set;

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
            "%s.%s",
            this.klass.name(),
            this.method.getSignature().asString()
        );
    }

    Modifiers modifiers() {
        final Set<Modifier> modifiers = new HashSet<>();
        if (this.method.isStatic()) {
            modifiers.add(Modifier.STATIC);
        } else {
            modifiers.add(Modifier.INSTANCE);
        }
        if (this.method.getAnnotationByClass(Override.class).isPresent()) {
            modifiers.add(Modifier.OVERRIDDEN);
        }
        if (this.method.isPublic()) {
            modifiers.add(Modifier.PUBLIC);
        } else if (this.method.isPrivate()) {
            modifiers.add(Modifier.PRIVATE);
        } else if(this.method.isProtected()){
            modifiers.add(Modifier.PROTECTED);
        } else {
            modifiers.add(Modifier.PACKAGE_PRIVATE);
        }
        return new Modifiers(modifiers);
    }
}
