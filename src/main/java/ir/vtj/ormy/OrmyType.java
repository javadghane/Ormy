package ir.vtj.ormy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by JavaDroid on 4/26/2017 in MEEDC-GIS department.
 * hi@JavaDroid.ir
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrmyType {
    public boolean isPrimaryKey() default false;

    public enum FieldType {PRIME, TEST}

    FieldType filed_type() default FieldType.PRIME;
}
