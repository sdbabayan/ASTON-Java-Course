package lesson3.builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class City {
    private String name;
    private LocalDate foundationDate;
    private int populationQty;

    private City() {
    }

    public String getName() {
        return name;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public int getPopulationQty() {
        return populationQty;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formatted = foundationDate.format(formatter);
        return "Город " + name +
                " - дата основания: " + formatted +
                ", численность населения: " + populationQty + " человек.";
    }

    public static class Builder {
        private final City city;

        public Builder() {
            city = new City();
        }

        public Builder setName(String name) {
            city.name = name;
            return this;
        }

        public Builder setFoundationDate(LocalDate foundationDate) {
            city.foundationDate = foundationDate;
            return this;
        }

        public Builder setPopulationQty(int populationQty) {
            city.populationQty = populationQty;
            return this;
        }

        public City build() {
            return city;
        }
    }
}

class Program {
    public static void main(String[] args) {
        City city = new City.Builder()
                .setName("Санкт-Петербург")
                .setFoundationDate(LocalDate.of(1703, 5, 27))
                .setPopulationQty(5_000_000)
                .build();
        System.out.println(city);
    }
}
