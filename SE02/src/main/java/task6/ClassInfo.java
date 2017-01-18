package task6;

import java.lang.annotation.*;

/**
 * Created by Air on 19/01/2017.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ClassInfo {
    String info();
    String author() default "unknown";
}
