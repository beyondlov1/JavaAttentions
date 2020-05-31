package com.beyond.hello;

import java.util.List;

public class InterfaceMetadata extends Metadata{
    private List<MethodMetadata> methods;
    private List<AnnotationMetadata> annotations;

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
}
