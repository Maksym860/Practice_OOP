public class main {

    public static void lab1_af72fd9f(String[] args) {

        System.out.println("Передані аргументи:"); // виводимо заголовок

        for (int i = 0; i < args.length; i++) { // проходимо по всіх аргументах і виводимо їх
            System.out.println(args[i]);
        }

        System.out.println("Кількість аргументів: " + args.length); // виводимо кількість аргументів
    }
}
