package com.beyond.hello;

import java.util.List;

public class FieldMetadata extends Metadata{
    private List<AnnotationMetadata> annotations;

    public List<AnnotationMetadata> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationMetadata> annotations) {
        this.annotations = annotations;
    }
}
