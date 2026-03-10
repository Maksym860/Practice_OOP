import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Анотація з політикою утримання SOURCE.
 * Існує лише на етапі компіляції та повністю зникає з байткоду.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface SourceNote {
    String value();
}

