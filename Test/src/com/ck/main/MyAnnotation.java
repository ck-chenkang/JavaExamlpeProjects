package com.ck.main;

import java.lang.annotation.Annotation;

@Test2("Hello World")
public class MyAnnotation {

    public static void main(String[] args) {
        try {
            Class aClass = Class.forName("com.ck.main.MyAnnotation");
            Annotation[] annotations = aClass.getAnnotations();

            for (Annotation annotation : annotations) {
                System.out.println("annotation: " + annotation);

//                System.out.println((Test2)annotation.value());

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
