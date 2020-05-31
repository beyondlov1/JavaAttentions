package com.beyond.hello;

import org.objectweb.asm.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        List<String> classNames = PackageScanner.scanForClassName("com.beyond.hello");
        List<ClassMetadata> classMetadataList = new ArrayList<ClassMetadata>();
        for (String className : classNames) {
            classMetadataList.add(main.generate(className));
        }

        for (ClassMetadata classMetadata : classMetadataList) {
            System.out.println(classMetadata.getName());
        }
    }


    public ClassMetadata generate(String className) throws IOException {
        ClassMetadata metadata = new ClassMetadata();
        ClassReader classReader = new ClassReader(className);
        ClassMetadataCollector classVisitor = new ClassMetadataCollector(metadata);
        classReader.accept(classVisitor, 0);
        return metadata;
    }

    private class MethodMetadataCollector extends MethodVisitor{

        MethodMetadata metadata;

        public MethodMetadataCollector(MethodMetadata metadata) {
            super(Opcodes.ASM5);
            this.metadata = metadata;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            if (metadata.getAnnotations() == null){
                metadata.setAnnotations(new ArrayList<AnnotationMetadata>());
            }
            AnnotationMetadata annotationMetadata = new AnnotationMetadata();
            annotationMetadata.setName(descriptor);
            metadata.getAnnotations().add(annotationMetadata);
            return super.visitAnnotation(descriptor, visible);
        }
    }


    private class ClassMetadataCollector extends ClassVisitor {
        ClassMetadata metadata;
        public ClassMetadataCollector(ClassMetadata metadata) {
            super(Opcodes.ASM5);
            this.metadata = metadata;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces);
            metadata.setName(name);
            metadata.setSuperName(superName);
            metadata.setInterfaceNames(interfaces);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            if (metadata.getMethods() == null){
                metadata.setMethods(new ArrayList<MethodMetadata>());
            }
            MethodMetadata methodMetadata = new MethodMetadata();
            methodMetadata.setName(name);
            metadata.getMethods().add(methodMetadata);
            return new MethodMetadataCollector(methodMetadata);
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            return super.visitField(access, name, descriptor, signature, value);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            return super.visitAnnotation(descriptor, visible);
        }
    }
}
