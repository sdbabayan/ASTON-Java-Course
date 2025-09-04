package lesson1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    private String name;
    private List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        StringBuilder booksName = new StringBuilder();
        for (Book book : books) {
            booksName.append("«");
            booksName.append(book.getName());
            booksName.append("», ");
        }
        return name + " читает книги: " + booksName;
    }
}

class Book implements Comparable<Book> {
    private String name;
    private String author;
    private Integer qtyOfPages;
    private String releaseDate;

    public Book(String name, String author, int qtyOfPages, String releaseDate) {
        this.name = name;
        this.author = author;
        this.qtyOfPages = qtyOfPages;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getQtyOfPages() {
        return qtyOfPages;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int compareTo(Book o) {
        if (this.getQtyOfPages() < o.getQtyOfPages()) {
            return -1;
        } else if (this.getQtyOfPages().equals(o.getQtyOfPages())) {
            return 0;
        } else return 1;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + author.hashCode() + qtyOfPages.hashCode() + releaseDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }
        Book book = (Book) obj;
        return this.name.equals(book.name) && this.author.equals(book.author)
                && this.qtyOfPages.equals(book.qtyOfPages) && this.releaseDate.equals(book.releaseDate);
    }
}

class Program {
    public static void main(String[] args) {
        Student student1 = new Student("Никитин Филипп", new ArrayList<Book>(Arrays.asList(
                new Book("Морана и Тень. Видящий", "Лия Арден", 198, "01.05.2023"),
                new Book("Посох двуликого Януса", "Александра Маринина", 203, "05.08.2020"),
                new Book("Каждому своё 2", "Сергей Тармашев", 320, "08.09.2021"),
                new Book("Дела Тайной канцелярии", "Виктор Дашкевич", 105, "01.09.1980"),
                new Book("Каждому своё", "Сергей Тармашев", 220, "21.05.2020")
        )));
        Student student2 = new Student("Маслов Дмитрий", new ArrayList<Book>(Arrays.asList(
                new Book("Сделка", "Марина Суржевская", 103, "12.12.2005"),
                new Book("Ониксовый шторм", "Ребекка Яррос", 214, "13.08.2004"),
                new Book("Посох двуликого Януса", "Александра Маринина", 203, "05.08.2020"),
                new Book("Каждому своё 2", "Сергей Тармашев", 320, "08.09.2021"),
                new Book("Сумрак Чужой войны", "Макс Глебов", 612, "29.03.1989")
        )));
        Student student3 = new Student("Чернышев Давид", new ArrayList<Book>(Arrays.asList(
                new Book("Сумрак Чужой войны", "Макс Глебов", 612, "29.03.1989"),
                new Book("Счастье в добрые руки", "Ольга Назарова", 405, "14.05.1999"),
                new Book("Каждому своё 2", "Сергей Тармашев", 320, "08.09.2021"),
                new Book("Морана и Тень. Видящий", "Лия Арден", 198, "01.05.2023"),
                new Book("Трансерфинг себя", "Вадим Зеланд", 488, "21.09.2020")
        )));
        Student student4 = new Student("Ткачев Леонид", new ArrayList<Book>(Arrays.asList(
                new Book("Тяжелый свет Куртейна. Синий", "Макс Фрай", 225, "01.01.2020"),
                new Book("Ониксовый шторм", "Ребекка Яррос", 214, "13.08.2004"),
                new Book("Дела Тайной канцелярии", "Виктор Дашкевич", 105, "01.09.1980"),
                new Book("Тяжелый свет Куртейна. Желтый", "Макс Фрай", 400, "25.08.2004"),
                new Book("Легкий способ бросить курить", "Аллен Карр", 500, "22.07.2020")
        )));

        ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(student1, student2, student3, student4));
        String string = students.stream()
                .peek(student -> System.out.println(student.toString()))
                .map(student -> student.getBooks())
                .flatMap(books -> books.stream())
                .sorted()
                .distinct()
                .filter(book -> LocalDate.parse(book.getReleaseDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).getYear() > 2001)
                .limit(3)
                .map(book -> LocalDate.parse(book.getReleaseDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).getYear())
                .findAny()
                .map(o -> o.toString())
                .orElse("такая книга отсутствует");

        System.out.println(string);
    }
}
