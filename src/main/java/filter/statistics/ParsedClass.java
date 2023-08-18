package filter.statistics;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.TypeDeclaration;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class ParsedClass {

    private final String name;
    private final Collection<TypeDeclaration<?>> unit;

    private ParsedClass(
        final String name,
        final TypeDeclaration<?> unit
    ) {
        this(name, Collections.singleton(unit));
    }

    private ParsedClass(
        final String name,
        final Collection<TypeDeclaration<?>> unit
    ) {
        this.name = name;
        this.unit = unit;
    }

    static Stream<ParsedClass> parse(final Path path) {
        try {
            StaticJavaParser.getConfiguration().setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_17);
            return ParsedClass.classes(StaticJavaParser.parse(path));
        } catch (final ParseProblemException | IOException ex) {
            Logger.getLogger("PARSER").warning(
                () -> String.format("Can't parse class '%s', reason: '%s'", path, ex.getMessage())
            );
            return Stream.empty();
        }
    }

    private static Stream<ParsedClass> classes(final Node node) {
        if (ParsedClass.isClass(node)) {
            final String name = ((TypeDeclaration<?>) node).getFullyQualifiedName()
                .orElseThrow(IllegalStateException::new);
            final ParsedClass parsedClass = new ParsedClass(name, (TypeDeclaration<?>) node);
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
        return this.unit.stream()
            .flatMap(u -> u.getMethods().stream())
            .map(method -> new ParsedMethod(method, this))
            .collect(Collectors.toSet());
    }

    ParsedClass add(ParsedClass klass) {
        return new ParsedClass(
            this.name,
            Stream.concat(
                this.unit.stream(),
                klass.unit.stream()
            ).collect(Collectors.toList())
        );
    }

    /**
     * Check whether node is class or not.
     * @param node Any node.
     * @return True if test class.
     */
    private static boolean isClass(final Node node) {
        return node instanceof TypeDeclaration<?>
            && ((TypeDeclaration<?>) node).getFullyQualifiedName().isPresent();
    }
}
