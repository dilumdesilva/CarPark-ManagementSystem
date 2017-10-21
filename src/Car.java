
public class Car extends Vehicle {
    private int numDoors;
    private String Color;

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    //implementing the abstract methods in the vehicle class
    public String getVehicleType() {return vehicleType;}

    public void setVehicleType(String vehicleType) {this.vehicleType = vehicleType;}

    public String getIdPlate() {return idPlate;}

    public void setIdPlate(String idPlate) {
        this.idPlate = idPlate;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

}
