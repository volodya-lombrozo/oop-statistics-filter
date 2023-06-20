package filter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class ParsedClass {

    private final String name;
    private final ClassOrInterfaceDeclaration unit;

    private ParsedClass(
        final String name,
        final ClassOrInterfaceDeclaration unit
    ) {
        this.name = name;
        this.unit = unit;
    }

    static Stream<ParsedClass> parse(final Path path) {
        try {
            return  ParsedClass.classes(StaticJavaParser.parse(path));
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static Stream<ParsedClass> classes(final Node node) {
        if (ParsedClass.isClass(node)) {
            final String name = ((TypeDeclaration<?>) node).getFullyQualifiedName().get();
            final ParsedClass parsedClass = new ParsedClass(name, (ClassOrInterfaceDeclaration) node);
            return Stream.concat(
                node.getChildNodes().stream().flatMap(ParsedClass::classes),
                Stream.of(parsedClass)
            );
        } else {
            return node.getChildNodes().stream().flatMap(ParsedClass::classes);
        }
    }

    String name() {
        return this.name;
    }

    Collection<ParsedMethod> methods() {
        return this.unit.getMethods()
            .stream()
            .map(method -> new ParsedMethod(method, this))
            .collect(Collectors.toSet());
    }

    /**
     * Check whether node is class or not.
     * @param node Any node.
     * @return True if test class.
     */
    private static boolean isClass(final Node node) {
        return node instanceof TypeDeclaration<?>;
    }
}
