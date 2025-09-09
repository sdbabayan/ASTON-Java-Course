package lesson2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Program {
    public static void main(String[] args) {
        String path = "src\\main\\java\\lesson2\\example.txt";
        String latinLine = "Line of Latin letters\n";
        String cyrillicLine = "Строка на кириллице\n";

        System.out.println("Читаем файл...");
        try {
            Scanner.scan(path);
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Пишем в файл строку на латинице...");
        try {
            Printer.print(path, latinLine, StandardOpenOption.APPEND);
        } catch (CyrillicLettersException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Пишем в файл строку на кириллице...");
        try {
            Printer.print(path, cyrillicLine, StandardOpenOption.APPEND);
        } catch (CyrillicLettersException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Читаем файл...");
        try {
            Scanner.scan(path);
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Printer {
    public static void print(String path, String line, StandardOpenOption option) throws CyrillicLettersException {
        if (line.matches("[\\s\\S]*[А-Яа-яЁё][\\s\\S]*")) {
            throw new CyrillicLettersException("ОШИБКА ЗАПИСИ: Для записи в файл cтрока «" + line.strip() +
                    "» не должна содержать кириллические символы.");
        }
        try {
            Files.writeString(Paths.get(path), line, option);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Scanner {
    public static void scan(String path) throws EmptyFileException {
        try {
            String text = Files.readString(Paths.get(path));
            if (text.isEmpty()) {
                throw new EmptyFileException("ОШИБКА ЧТЕНИЯ: Пустой файл!");
            }
            System.out.println(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class CyrillicLettersException extends Exception {
    public CyrillicLettersException(String message) {
        super(message);
    }
}

class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}
