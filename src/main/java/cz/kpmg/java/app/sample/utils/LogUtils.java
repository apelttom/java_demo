package cz.kpmg.java.app.sample.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;


public class LogUtils {

    public static final String reflectionToString(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        return object.getClass().getSimpleName() + ReflectionToStringBuilder.toString(object, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

}
