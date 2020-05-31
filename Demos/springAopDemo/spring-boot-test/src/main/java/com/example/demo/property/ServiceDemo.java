package com.example.demo.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author beyondlov1
 * @date 2019/03/29
 */
@Service
public class ServiceDemo {
    @Autowired
    private PropertyDemo propertyDemo;

    public PropertyDemo getPropertyDemo() {
        return propertyDemo;
    }

    public void setPropertyDemo(PropertyDemo propertyDemo) {
        this.propertyDemo = propertyDemo;
    }
}
