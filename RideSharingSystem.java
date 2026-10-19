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
@override
  loadRidersFromCSV(String filePath) {
    try{ 
      Scanner read = new Scanner(new File(filePath));
      while (read.hasNextLine()) {
        String [] d = read.nextLine().trim().split(",");
        IRider r = new Rider(
          Integer.parseInt(d[0]), //riderId
          d[1], //name
          d[3], //phone
          d[2], //email
          d[4] //homeCity
          );
        addRider(r);
      }
      read.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
      
  
  
}
