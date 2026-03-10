Лабораторна робота №7

## Мета роботи

- **Розробити ієрархію класів за шаблоном Observer** та показати обслуговування однієї колекції щонайменше **двома різними спостерігачами** (сортування/вивід/відображення).
- **Використати анотації (Annotations)** та продемонструвати різні політики утримання (**Retention policies**): `RUNTIME`, `CLASS`, `SOURCE`.
- **Продемонструвати Reflection**: прочитати анотації та метадані класів/полів у runtime.
- **Створити GUI-додаток** (Swing), що відображає результати обробки колекції у **графічному вигляді**.
- **Забезпечити діалоговий інтерфейс** і **перемальовування графіка** при зміні значень елементів колекції.

## Постановка задачі (реалізація в проєкті)

1. **Observable**: раніше створена колекція результатів керується класом `CalculationManager`.
2. **Observers** (мінімум 2):
   - `ConsoleSortingObserver` — реагує на зміни колекції, **сортує** елементи та **виводить** у консоль.
   - `GraphPanel` — `JPanel`, що малює **стовпчиковий графік** кількості повних тетрад для кожного елемента; перерисовується при змінах.
3. **Annotations**:
   - `@Monitored` з `RetentionPolicy.RUNTIME` — ставиться на `CalculationManager` та поля `CalculationData`; доступна через Reflection.
   - `@InternalObserverTag` з `RetentionPolicy.CLASS` — ставиться на `ConsoleSortingObserver`; у runtime через Reflection **не читається**.
   - `@SourceNote` з `RetentionPolicy.SOURCE` — існує лише на етапі компіляції.
4. **Reflection**:
   - `ReflectionDemo.printAnnotationInfo()` читає `@Monitored` для класів/полів і демонструє, що `CLASS/SOURCE`-анотації у runtime не доступні.
5. **GUI**:
   - `GuiMain` — головний клас графічного застосунку.
   - Інтерфейс: поле вводу числа + кнопки **Додати**, **Видалити останнє**, **Очистити**.
   - При додаванні елемента запускається обчислення через `TetradCalculator`, після чого елемент додається до колекції — обидва спостерігачі отримують подію і оновлюються.

## Орієнтовна структура (ключові класи)

- **Дані/колекція**
  - `CalculationData` — елемент колекції (decimal/binary/fullTetrads).
  - `CalculationManager` — менеджер колекції (**Observable**) з `addObserver/removeObserver`.
- **Observer**
  - `CalculationObserver` — інтерфейс спостерігача.
  - `ConsoleSortingObserver` — консольний спостерігач (сортування + вивід).
  - `GraphPanel` — графічний спостерігач (малювання).
- **Annotations / Reflection**
  - `Monitored` (RUNTIME), `InternalObserverTag` (CLASS), `SourceNote` (SOURCE).
  - `ReflectionDemo` — демонстрація читання анотацій через Reflection.
- **GUI**
  - `GuiMain` — запуск Swing-вікна та реєстрація спостерігачів.

## Особливості retention policies (коротко)

- `RetentionPolicy.RUNTIME` — анотація **зберігається** і **доступна через Reflection** під час виконання програми.
- `RetentionPolicy.CLASS` — анотація **є в байткоді**, але **не доступна** через Reflection у runtime.
- `RetentionPolicy.SOURCE` — анотація **лише в вихідному коді**; компілятор її **не переносить** у `.class`.

## Запуск проєкту (Windows / PowerShell)

1. Перейти в директорію проєкту:

```
cd "c:\Users\MaksymPC\projects\Practice_OOP\lab6"
```

2. Скомпілювати всі `.java`:

```
javac *.java
```

3. Запуск (рекомендовано GUI):

```
java GuiMain
```

4. Запуск консольного меню (як у попередніх лабораторних):

```
java Main
```

