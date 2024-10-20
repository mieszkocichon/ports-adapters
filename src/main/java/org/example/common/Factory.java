package org.example.common;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Factory {
}
