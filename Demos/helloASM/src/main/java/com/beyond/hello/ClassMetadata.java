package com.beyond.hello;

import java.util.List;

public class ClassMetadata extends Metadata {
    private List<FieldMetadata> fieldMetadatas;
    private List<MethodMetadata> methodMetaDatas;
    private List<AnnotationMetadata> annotationMetadatas;

    public List<FieldMetadata> getFieldMetadatas() {
        return fieldMetadatas;
    }

    public void setFieldMetadatas(List<FieldMetadata> fieldMetadatas) {
        this.fieldMetadatas = fieldMetadatas;
    }

    public List<MethodMetadata> getMethodMetaDatas() {
        return methodMetaDatas;
    }

    public void setMethodMetaDatas(List<MethodMetadata> methodMetaDatas) {
        this.methodMetaDatas = methodMetaDatas;
    }

    public List<AnnotationMetadata> getAnnotationMetadatas() {
        return annotationMetadatas;
    }

    public void setAnnotationMetadatas(List<AnnotationMetadata> annotationMetadatas) {
        this.annotationMetadatas = annotationMetadatas;
    }

    @Override
    public String toString() {
        return "ClassMetadata{" +
                "fieldMetadatas=" + fieldMetadatas +
                ", methodMetaDatas=" + methodMetaDatas +
                ", annotationMetadatas=" + annotationMetadatas +
                "} " + super.toString();
    }
}
