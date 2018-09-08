import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 *@author Yakovlev Alexandr
 */
public class Tester {
    public static void main(String[] args) {
        start(Tests.class);
    }

    private static void start(Class<?> clazz) throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        Constructor constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();

        startSingleMethod(clazz, BeforeSuite.class, instance);

        Method[] tests = getMethodByAnnotation(clazz, Test.class);
        Arrays.sort(tests, Comparator.comparingInt((Method i)-> i.getAnnotation(Test.class).prioritry().ordinal()));
        for (Method test : tests) {
            test.invoke(instance);
        }

        startSingleMethod(clazz, AfterSuite.class, instance);
    }

    private static Method[] getMethodByAnnotation(Class clazz, final Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = Arrays
                .stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList());

        boolean isSingle = annotatedMethods.size() < 2;
        boolean mustBeSingle = annotation.equals(BeforeSuite.class) || annotation.equals(AfterSuite.class);
        if (mustBeSingle && !isSingle) throw new RuntimeException(annotation.getSimpleName() + "is not unique");

        return annotatedMethods.toArray(new Method[annotatedMethods.size()]);
    }

    private static void startSingleMethod(Class<?> clazz, Class<? extends Annotation> annotation, Object instance) throws
            IllegalAccessException,
            InvocationTargetException {
        Method[] afterMethod = getMethodByAnnotation(clazz, annotation);
        if(afterMethod.length !=0)afterMethod[0].invoke(instance);
    }
}
