package com.beyond.hello;

import org.objectweb.asm.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassMetadata metadata = new ClassMetadata();
        ClassReader classReader = new ClassReader("com.beyond.hello.Target");
        ClassPrinter classVisitor = new ClassPrinter(metadata);
        classReader.accept(classVisitor, 0);

        System.out.println(metadata);
    }

    private static class MethodAnnotationPrinter extends MethodVisitor{

        MethodMetadata metadata;

        public MethodAnnotationPrinter(MethodMetadata metadata) {
            super(Opcodes.ASM5);
            this.metadata = metadata;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            System.out.println(descriptor);
            if (metadata.getAnnotationMetadatas() == null){
                metadata.setAnnotationMetadatas(new ArrayList<AnnotationMetadata>());
            }
            AnnotationMetadata annotationMetadata = new AnnotationMetadata();
            annotationMetadata.setName(descriptor);
            metadata.getAnnotationMetadatas().add(annotationMetadata);
            return super.visitAnnotation(descriptor, visible);
        }
    }


    private static class ClassPrinter extends ClassVisitor {
        ClassMetadata metadata;
        public ClassPrinter( ClassMetadata metadata) {
            super(Opcodes.ASM5);
            this.metadata = metadata;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces);
            System.out.println(superName);
            System.out.println(name);
            metadata.setName(name);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            System.out.println("methodName: " + name);
            if (metadata.getMethodMetaDatas() == null){
                metadata.setMethodMetaDatas(new ArrayList<MethodMetadata>());
            }
            MethodMetadata methodMetadata = new MethodMetadata();
            methodMetadata.setName(name);
            metadata.getMethodMetaDatas().add(methodMetadata);
            return new MethodAnnotationPrinter(methodMetadata);
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            System.out.println("fieldName:" + name);
            return super.visitField(access, name, descriptor, signature, value);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            System.out.println(descriptor);
            return super.visitAnnotation(descriptor, visible);
        }
    }
}
