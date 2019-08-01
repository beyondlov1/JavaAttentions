package com.beyond.hello;

import java.util.List;

public class ClassMetadata extends Metadata {

    private ClassMetadata superClass;
    private List<InterfaceMetadata> interfaces;
    private List<FieldMetadata> fields;
    private List<MethodMetadata> methods;
    private List<AnnotationMetadata> annotations;
    private String[] interfaceNames;
    private String superName;

    public ClassMetadata getSuperClass() {
        return superClass;
    }

    public void setSuperClass(ClassMetadata superClass) {
        this.superClass = superClass;
    }

    public List<InterfaceMetadata> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<InterfaceMetadata> interfaces) {
        this.interfaces = interfaces;
    }

    public List<FieldMetadata> getFields() {
        return fields;
    }

    public void setFields(List<FieldMetadata> fields) {
        this.fields = fields;
    }

    public List<MethodMetadata> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodMetadata> methods) {
        this.methods = methods;
    }

    public List<AnnotationMetadata> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationMetadata> annotations) {
        this.annotations = annotations;
    }


    public void setInterfaceNames(String[] interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getSuperName() {
        return superName;
    }
}
