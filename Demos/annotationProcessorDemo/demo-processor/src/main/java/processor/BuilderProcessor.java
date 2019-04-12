package processor;

import annotation.Builder;
import com.squareup.javapoet.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.io.IOException;
import java.util.Set;

import static javax.lang.model.element.Modifier.STATIC;

/**
 * @author beyondlov1
 * @date 2019/04/12
 */
@SupportedAnnotationTypes("annotation.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BuilderProcessor extends AbstractProcessor {
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Builder.class);
        elementsAnnotatedWith.forEach(element -> {
            if (element.getKind() != ElementKind.CLASS) {
                System.out.println("not class");
                return;
            }
            TypeMirror elementTypeMirror = element.asType();
            String builderClassName = element.getSimpleName() + "Builder";
            TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(builderClassName)
                    .addModifiers(Modifier.FINAL, Modifier.PUBLIC);
            MethodSpec.Builder builderMethod = MethodSpec.methodBuilder("build")
                    .returns(TypeName.get(element.asType()))
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("$T instance = new $T()", TypeName.get(elementTypeMirror), TypeName.get(elementTypeMirror));
            element.getEnclosedElements().forEach(field -> {
                if (field.getKind() == ElementKind.FIELD) {
                    boolean isStatic = field.getModifiers().contains(STATIC);
                    if (isStatic) {
                        System.out.println(field.getSimpleName() + " is static");
                        return;
                    }
                    String fieldName = field.getSimpleName().toString();
                    System.out.println(fieldName);
                    MethodSpec methodSpec = MethodSpec.methodBuilder( fieldName)
                            .returns(TypeName.get(element.asType()))
                            .returns(ClassName.get("com.beyond.gen", builderClassName))
                            .addModifiers(Modifier.PUBLIC)
                            .addParameter(TypeName.get(field.asType()), field.getSimpleName().toString())
                            .addStatement("this.$L = $L;return this", field.getSimpleName().toString()
                                    , field.getSimpleName().toString())
                            .build();
                    FieldSpec fieldSpec = FieldSpec.builder(TypeName.get(field.asType()),
                            fieldName, Modifier.PRIVATE).build();
                    String fieldSetName = upperFirstChar(fieldName);
                    builderMethod.addStatement("instance.set$L(this.$L)", fieldSetName, fieldName);
                    typeBuilder.addField(fieldSpec);
                    typeBuilder.addMethod(methodSpec);
                }
            });
            builderMethod.addStatement("return instance");
            typeBuilder.addMethod(builderMethod.build());
            TypeSpec typeSpec = typeBuilder.build();
            try {
                JavaFile.builder("com.beyond.gen", typeSpec).build().writeTo(System.out);
                JavaFile.builder("com.beyond.gen", typeSpec).build().writeTo( processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    private static String upperFirstChar(String name) {
        if (name.length() < 1) {
            return name;
        }
        String firstChar = name.substring(0, 1).toUpperCase();
        if (name.length() > 1) {
            return firstChar + name.substring(1);
        }
        return firstChar;
    }
}
