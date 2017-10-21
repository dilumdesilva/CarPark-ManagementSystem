/**
 * OOP CW01 by Dilum De Silva (IIT NO - 2016142)
 */
public class Van extends Vehicle {

    private Double cargoVolume;

    public Double getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(Double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    //implementing the abstract methods in the vehicle class
    public String getVehicleType() { return vehicleType;}

    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType;}

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
