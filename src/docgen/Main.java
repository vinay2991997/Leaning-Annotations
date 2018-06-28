package docgen;

import math.MathUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {

        DocProcessor.process(MathUtils.class);

        /*
        Class clazz = MathUtils.class;
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {

            // Method names
            System.out.println("Name : " + method.getName());

            // Method paramerter
            System.out.println("\t#params : " + method.getParameterCount());

            // Method return type
            System.out.println("\tReturn Type : " + method.getReturnType().getSimpleName());

            // Method modifiers
            int mod = method.getModifiers();
            String modifier = Modifier.toString(mod);
            System.out.println("\tModifier : " + modifier);
        }
        */
    }
}
