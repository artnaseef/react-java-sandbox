package com.artnaseef.sandbox.react;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by art on 7/24/19.
 */
public class Main {
    private ClassPathXmlApplicationContext applicationContext;

    public static void main(String[] args) {
        Main mainInstance = new Main();
        mainInstance.instanceMain(args);
    }

    public void instanceMain(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext("spring-context/application-context.xml");
    }
}
