package com.beyond.hello;

import java.util.List;

public class FieldMetadata extends Metadata{
    private List<AnnotationMetadata> annotationMetadatas;

    public List<AnnotationMetadata> getAnnotationMetadatas() {
        return annotationMetadatas;
    }

    public void setAnnotationMetadatas(List<AnnotationMetadata> annotationMetadatas) {
        this.annotationMetadatas = annotationMetadatas;
    }
}
