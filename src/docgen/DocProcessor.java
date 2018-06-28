package docgen;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class DocProcessor {

    // check for the class
    public static void process(Class clazz) {
        if (clazz.isAnnotationPresent(Doc.class)) {
            int errorsCount = 0;
            System.out.println("Analyzing \'" + clazz.getSimpleName() + "\'...");

            Doc classdoc = (Doc) clazz.getDeclaredAnnotation(Doc.class);
            if (classdoc.desc().isEmpty()) {
                System.out.println("\n\t=> Documentation for Class Desc is missing.");
                errorsCount++;
            }

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {


                int methodModifier = method.getModifiers();

                // check for the non private method
                if (!Modifier.isPrivate(methodModifier)) {

                    System.out.println();
                    System.out.println("\t" + method.getName() + " : ");

                    int errors = 0;
                    // check for the Doc annotation
                    if (method.isAnnotationPresent(Doc.class)) {
                        Doc doc = method.getAnnotation(Doc.class);

                        // method desc check
                        if (!isDescPresent(method, doc)) {
                            System.out.println("\t\t=> " + "Method Desc is missing");
                            ++errors;
                        }

                        // method params check
                        if (getMissingParams(method, doc) > 0) {
                            System.out.println("\t\t=> " + "Documtentation for " + getMissingParams(method, doc) + " parameter(s) is missing.");
                            ++errors;
                        }

                        // method returnType check
                        if (!isReturnTypePresent(method, doc)) {
                            System.out.println("\t\t=> " + "Documtentation for return Type is missing.");
                            ++errors;
                        }
                    }
                    else {
                        System.out.println("\t\t=> " + "@Doc Annotation is missing");
                        ++errors;
                    }
                    if (errors == 0) {
                        System.out.println("\t\t=> " + "No changes needed.");
                    }
                    errorsCount += errors;
                }
            }
            System.out.println();
            if (errorsCount > 0)
                System.out.println("DocProcessor has found " + errorsCount + " errors in class \'" + clazz.getSimpleName() + "\'.");
            else
                System.out.println("DocProcessor has found no errors in class \'" + clazz.getSimpleName() + "\'.");
        }
        else
            System.out.println("Doc annotation is not present in " + clazz.getSimpleName());
    }

    private static boolean isReturnTypePresent(Method method, Doc doc) {
        return method.getReturnType().equals(Void.TYPE) || !doc.returnVal().isEmpty();
    }

    private static int getMissingParams(Method method, Doc doc) {
        int missingParams;
        int annotatedParams = doc.params().length;
        int actualParams = method.getParameterCount();
        missingParams = actualParams - annotatedParams;
        return missingParams;
    }

    private static boolean isDescPresent(Method method, Doc doc) {
        return !doc.desc().isEmpty();
    }
}
