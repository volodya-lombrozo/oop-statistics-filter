package jar;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithMembers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class Filter {

    final static Path csvPath = Paths.get(
        "/Users/lombrozo/Workspace/OpenSource/oop/results/apache-derby/method-list-cpu.csv"
    );

    final static Path repositoryPath = Paths.get(
        "/Users/lombrozo/Workspace/OpenSource/derby"
    );

    final static String pckg = "org.apache.derby";

    public static void main(final String[] args) {
//        Filter.parseClasses();
        Filter.parseCSV();
    }

    private static void parseCSV() {
        System.out.println("Hello world");
    }

    private static void parseClasses() {
        try (Stream<Path> files = Files.walk(Filter.repositoryPath)) {
            final Map<String, ParsedClass> classes = files
                .filter(Files::exists)
                .filter(Files::isRegularFile)
                .parallel()
                .filter(path -> path.toString().endsWith(".java"))
                .map(Filter::parse)
                .filter(parsed -> parsed.pckg().isPresent())
                .collect(
                    Collectors.toMap(
                        parsed -> parsed.name(),
                        parsed -> parsed
                    )
                );
            final ParsedClass parsedClass = classes.get(
                "/Users/lombrozo/Workspace/OpenSource/derby/java/org.apache.derby.engine/org/apache/derby/impl/jdbc/EmbedResultSet.java");
            parsedClass.methods().stream().map(ParsedMethod::name)
                .forEach(System.out::println);

        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    private static ParsedClass parse(final Path path) {
        try {
            return new ParsedClass(path);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private static class ParsedClass {

        private final String name;
        private final CompilationUnit unit;

        private ParsedClass(final Path path) throws IOException {
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

    private static class ParsedMethod {

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
}
