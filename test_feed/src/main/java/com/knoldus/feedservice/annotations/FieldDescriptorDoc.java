package com.knoldus.feedservice.annotations;

import java.lang.annotation.*;

/** This annotation is used to describe the developer friendly description.
 *
 * @author Gaurav Kumar
 */
@Repeatable(FieldDescriptorDocs.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface FieldDescriptorDoc {
    String docName() default "";

    String value();

    boolean optional() default false;

    boolean ignored() default false;

    String type() default "";

    Class<?> recursiveType() default String.class;
}

