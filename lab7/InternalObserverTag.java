import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Анотація з політикою утримання CLASS.
 * Буде присутня у байткоді, але недоступна через Reflection у runtime.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface InternalObserverTag {
    String purpose();
}

