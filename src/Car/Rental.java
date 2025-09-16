package Car;

import java.sql.Timestamp;

public class Rental {
    public int rentalId;
    public int customerId;
    public int carId;
    public Timestamp fromDateTime;
    public Timestamp toDateTime;
    public int maxKm;
    public int startOdometer;

    public Rental(int rentalId, int customerId, int carId, Timestamp fromDateTime, Timestamp toDateTime,
                  int maxKm, int startOdometer) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.carId = carId;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.maxKm = maxKm;
        this.startOdometer = startOdometer;
    }

    public int getRentalId() {
        return rentalId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCarId() {
        return carId;
    }

    public Timestamp getFromDateTime() {
        return fromDateTime;
    }

    public Timestamp getToDateTime() {
        return toDateTime;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public int getStartOdometer() {
        return startOdometer;
    }

    @Override
    public String toString() {
        return String.format("ID:%d CustomerID:%d CarID:%d From:%s To:%s MaxKM:%d StartOdometer:%d",
                rentalId, customerId, carId, fromDateTime, toDateTime, maxKm, startOdometer);
    }
}