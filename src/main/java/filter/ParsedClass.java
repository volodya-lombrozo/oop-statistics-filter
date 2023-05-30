package filter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithMembers;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ParsedClass {

    private final String name;
    private final CompilationUnit unit;

    ParsedClass(final Path path) throws IOException {
        this(path.toString(), StaticJavaParser.parse(path));
    }

    private ParsedClass(final String name, final CompilationUnit unit) {
        this.name = name;
        this.unit = unit;
    }

    String name() {
        return name;
    }

    String definedName() {
        return this.unit.getPrimaryTypeName().orElse("");
    }

    Optional<String> pckg() {
        return this.unit.getPackageDeclaration()
            .map(PackageDeclaration::getName)
            .map(Object::toString);
    }

    Collection<ParsedMethod> methods() {
        return this.unit.getChildNodes()
            .stream()
            .filter(ParsedClass::isClass)
            .flatMap(this::methods)
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

    /**
     * Checks methods in class.
     *
     * @param node The child node.
     * @return Stream of test cases.
     */
    private Stream<ParsedMethod> methods(final Node node) {
        return ((NodeWithMembers<TypeDeclaration<?>>) node)
            .getMethods()
            .stream()
            .map(method -> new ParsedMethod(method, this));
    }
}
