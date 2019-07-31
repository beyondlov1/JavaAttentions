package com.beyond.hello;

import java.util.List;

public class MethodMetadata extends Metadata{
    private List<AnnotationMetadata> annotationMetadatas;

    public List<AnnotationMetadata> getAnnotationMetadatas() {
        return annotationMetadatas;
    }

    public void setAnnotationMetadatas(List<AnnotationMetadata> annotationMetadatas) {
        this.annotationMetadatas = annotationMetadatas;
    }

    @Override
    public String toString() {
        return "MethodMetadata{" +
                "annotationMetadatas=" + annotationMetadatas +
                "} " + super.toString();
    }
}
