import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RideSharingSystem implements IRideSharingSystem {
  private IRiderList riderList;
  private IDriverList driverList;
  private IRideList rideList;

public RideSharingSystem() {
  this.riderList = new RiderList;
  this.driverList = new DriverList;
  this.rideList = new RideList;
}
@override
  public boolean addRider(IRider rider) {
    return riderList.add(rider);
  }

  
  
}
