import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Клас, що демонструє використання анотацій та рефлексії.
 */
public class ReflectionDemo {

    @SourceNote("Цей метод використовує рефлексію для аналізу анотацій.")
    public static void printAnnotationInfo() {
        System.out.println("=== Демонстрація анотацій та Reflection ===");

        inspectClassAnnotations(CalculationManager.class);
        inspectClassAnnotations(CalculationData.class);
        inspectFields(CalculationData.class);
        inspectObserverAnnotations(ConsoleSortingObserver.class);

        System.out.println("Примітка: анотації з RetentionPolicy.CLASS та SOURCE "
                + "не доступні через Reflection у runtime.");
        System.out.println("=============================================");
    }

    private static void inspectClassAnnotations(Class<?> type) {
        System.out.println("Клас: " + type.getName());
        Annotation[] annotations = type.getAnnotations();
        if (annotations.length == 0) {
            System.out.println("  (немає анотацій із політикою RUNTIME)");
            return;
        }
        for (Annotation a : annotations) {
            System.out.println("  @" + a.annotationType().getSimpleName() + " -> " + a.toString());
        }
    }

    private static void inspectFields(Class<?> type) {
        System.out.println("Поля класу " + type.getName() + " з анотацією @Monitored:");
        for (Field f : type.getDeclaredFields()) {
            if (f.isAnnotationPresent(Monitored.class)) {
                Monitored m = f.getAnnotation(Monitored.class);
                System.out.println("  field: " + f.getName() + " -> " + m.value());
            }
        }
    }

    private static void inspectObserverAnnotations(Class<?> type) {
        System.out.println("Перевірка анотації з RetentionPolicy.CLASS для " + type.getName());
        InternalObserverTag tag = type.getAnnotation(InternalObserverTag.class);
        if (tag == null) {
            System.out.println("  Анотація InternalObserverTag недоступна через Reflection (Retention.CLASS).");
        } else {
            System.out.println("  InternalObserverTag: " + tag.purpose());
        }
    }
}

