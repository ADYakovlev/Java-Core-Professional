import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *@author Yakovlev Alexandr
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    enum Priority(LOWEST, VERY_LOW, LOWER, MEDIUM, HIGHER, HIGH, VREY_HIGH, HIGHEST, MAX_PRIORITY )
    Priority prioritry() default Priority.MEDIUM;
}
