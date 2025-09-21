package lesson3.adapter;

interface Speedometer {
    void getSpeed();

    void getMileage();
}

class SpeedometerInKilometers implements Speedometer {
    double speed;
    int mileage;

    SpeedometerInKilometers(double speed, int mileage) {
        this.speed = speed;
        this.mileage = mileage;
    }

    public void getSpeed() {
        System.out.printf("Скорость: %.1f км/ч\n", speed);
    }

    @Override
    public void getMileage() {
        System.out.printf("Пробег: %d км\n", mileage);
    }
}

class SpeedometerInMiles {
    String speedInMilesPerHour;
    String mileageInMiles;

    SpeedometerInMiles(String speedInMilesPerHour, String mileageInMiles) {
        this.speedInMilesPerHour = speedInMilesPerHour;
        this.mileageInMiles = mileageInMiles;
    }

    public void showSpeed() {
        System.out.printf("Скорость: %s миль/ч\n", speedInMilesPerHour);
    }

    public void showMileage() {
        System.out.printf("Пробег: %s миль\n", mileageInMiles);
    }
}

class SpeedometerAdapterFromKmToMiles implements Speedometer {
    SpeedometerInKilometers speedometerInKilometers;
    SpeedometerInMiles speedometerInMiles;

    SpeedometerAdapterFromKmToMiles(SpeedometerInKilometers speedometerInKilometers) {
        this.speedometerInKilometers = speedometerInKilometers;
        this.speedometerInMiles = convert(speedometerInKilometers);
    }

    private SpeedometerInMiles convert (SpeedometerInKilometers speedometerInKilometers) {
        return new SpeedometerInMiles(
                String.valueOf(speedometerInKilometers.speed / 1.6),
                String.valueOf(speedometerInKilometers.mileage / 1.6)
        );
    }

    @Override
    public void getSpeed() {
        speedometerInMiles.showSpeed();
    }

    @Override
    public void getMileage() {
        speedometerInMiles.showMileage();
    }
}

public class AdapterExample {
    public static void main(String[] args) {
        SpeedometerInKilometers speedometerInKilometers = new SpeedometerInKilometers(100.0, 50_000);
        SpeedometerAdapterFromKmToMiles speedometerAdapterFromKmToMiles =
                new SpeedometerAdapterFromKmToMiles(speedometerInKilometers);
        speedometerInKilometers.getSpeed();
        speedometerInKilometers.getMileage();
        speedometerAdapterFromKmToMiles.getSpeed();
        speedometerAdapterFromKmToMiles.getMileage();
    }
}
