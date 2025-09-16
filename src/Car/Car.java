package Car;

import java.sql.Date;

public class Car {
    public int carId;
    public String registrationNumber;
    public String brand;
    public String model;
    public String fuelType;
    public Date firstRegistration;
    public int odometerKm;
    public int carTypeId;

    public Car(int carId, String registrationNumber, String brand, String model, String fuelType, Date firstRegistration, int odometerKm, int carTypeId){
        this.carId = carId;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.firstRegistration = firstRegistration;
        this.odometerKm = odometerKm;
        this.carTypeId = carTypeId;
    }

    public int getCarId() {
        return carId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Date getFirstRegistration() {
        return firstRegistration;
    }

    public int getOdometerKm() {
        return odometerKm;
    }

    public int getCarTypeId() {
        return carTypeId;
    }

    @Override
    public String toString(){
        return String.format("ID:%d RegNum:%s %s %s Fuel:%s RegDate:%s Odo:%d TypeID:%d",
                carId, registrationNumber, brand, model, fuelType, firstRegistration, odometerKm, carTypeId);
    }
}
