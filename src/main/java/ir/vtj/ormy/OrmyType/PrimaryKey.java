package ir.vtj.ormy.OrmyType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by JavaDroid on 5/16/2017 in MEEDC-GIS department.
 * hi@JavaDroid.ir
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PrimaryKey {
    public boolean isPrimaryKey() default true;
}