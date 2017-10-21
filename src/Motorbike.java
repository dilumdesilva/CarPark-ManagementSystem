/**
 * OOP CW01 by Dilum De Silva (IIT NO - 2016142)
 */
public class Motorbike extends Vehicle {

    //this class includes all the methods to implement functions related to motorbike
    private String engineCapacity;

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    //abstract methods which are in the vehicle class
    public String getVehicleType() {return vehicleType;}

    public void setVehicleType(String vehicleType) {this.vehicleType = vehicleType;}

    public String getIdPlate() { return idPlate; }

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
