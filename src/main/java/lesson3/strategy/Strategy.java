package lesson3.strategy;

interface PrintStrategy {
    void print(String someString);
}

class PrintToConsoleStrategy implements PrintStrategy {
    public void print(String someString) {
        System.out.printf("Строка «%s» выведена в консоль.\n", someString);
    }
}

class PrintToFileStrategy implements PrintStrategy {
    public void print(String someString) {
        System.out.printf("Строка «%s» сохранена в файл.\n", someString);
    }
}

class Printer {
    String string;

    public Printer(String string) {
        this.string = string;
    }

    public void printByStrategy(PrintStrategy printStrategy) {
        System.out.printf("Печать в соответствии со стратегией %s:\n",
                printStrategy.getClass().getSimpleName());
        printStrategy.print(this.string);
    }
}

public class Strategy {
    public static void main(String[] args) {
        Printer printer = new Printer("тест");
        printer.printByStrategy(new PrintToConsoleStrategy());
        printer.printByStrategy(new PrintToFileStrategy());
    }
}