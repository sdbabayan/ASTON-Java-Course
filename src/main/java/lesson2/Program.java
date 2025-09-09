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
        } catch (MyIOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Пишем в файл строку на латинице...");
        try {
            Printer.print(path, latinLine, StandardOpenOption.APPEND);
        } catch (MyIOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Пишем в файл строку на кириллице...");
        try {
            Printer.print(path, cyrillicLine, StandardOpenOption.APPEND);
        } catch (MyIOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Читаем файл...");
        try {
            Scanner.scan(path);
        } catch (MyIOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Printer {
    public static void print(String path, String line, StandardOpenOption option) throws MyIOException {
        if (line.matches("[\\s\\S]*[А-Яа-яЁё][\\s\\S]*")) {
            throw new MyIOException("СТРОКА «" + line.strip() + "» НЕ ДОЛЖНА СОДЕРЖАТЬ КИРИЛЛИЧЕСКИЕ СИМВОЛЫ.");
        }
        try {
            Files.writeString(Paths.get(path), line, option);
        } catch (IOException e) {
            throw new MyIOException("ОШИБКА ЗАПИСИ", e);
        }
    }
}

class Scanner {
    public static void scan(String path) throws MyIOException {
        String text;
        try {
            text = Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new MyIOException("ОШИБКА ЧТЕНИЯ!", e);
        }
        if (text.isEmpty()) {
            throw new MyIOException("ПУСТОЙ ФАЙЛ!");
        }
        System.out.println(text);
    }
}

class MyIOException extends IOException {
    public MyIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyIOException(String message) {
        super(message);
    }
}