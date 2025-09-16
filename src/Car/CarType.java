package Car;

public class CarType {
    public int carTypeId;
    public String typeName;
    public String description;

    public CarType(int carTypeId, String typeName, String description){
        this.carTypeId = carTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public int getCarTypeId() {
        return carTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString(){
        return typeName + " (" + description + ")";
    }
}
