/**
 * OOP CW01 by Dilum De Silva (IIT NO - 2016142)
 */

public class EmptyParkingSlot extends Vehicle{

    private String availability;

    public EmptyParkingSlot(String availability){
        this.availability = availability;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return availability;
    }

    @Override
    public String getVehicleType() {
        return null;
    }

    @Override
    public void setVehicleType(String vehicleType) {

    }

    //abstract methods which are in the vehicle class
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
