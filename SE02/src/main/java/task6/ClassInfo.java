package task6;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ClassInfo {
    String info();
    String author() default "unknown";
}
