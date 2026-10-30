package project212;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RideSharingSystem implements IRideSharingSystem {
  private IRiderList riderList;
  private IDriverList driverList;
  private IRideList rideList;

public RideSharingSystem() {
  this.riderList = new RiderList();
  this.driverList = new DriverList();
  this.rideList = new RideList();
}
 @override
public boolean addRider(IRider r) {
	 IRider r1 = null;

	 try { 
		r1 = new Rider(r.getId(), r.getName(), r.getPhoneNumber(), r.getEmail(), r.getHomeCity());
	 }
	 catch (IllegalArgumentException e)
	 {
	     System.out.println("Rejected rider: " + e.getMessage());
	     return false;
	 }
    return riderList.add(r1);
  }
 
@override
public boolean loadRidersFromCSV(String filePath) {
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
        boolean added = addRider(r);
        if (!added) {
			return false;
		}
      }
       
      read.close();
      return true;
 
      }
    catch (Exception e) {
        e.printStackTrace();
        return false;
    }

  
}

@override
public boolean addDriver (IDriver d) {
	IDriver d1 = null;
	try {
		 d1 = new Driver (d.getId(), d.getName(), d.getPhoneNumber(), d.getVehiclePlate(), d.getVehicleType());
	}
	catch (IllegalArgumentException e)
	{
		return false;
	}
	return driverList.add(d1);
}

@override
public boolean loadDriversFromCSV(String filePath) {
	try {
		Scanner read = new Scanner(new File(filePath));
	      while (read.hasNextLine()) {
	        String [] d = read.nextLine().trim().split(",");
	        IDriver driver = new Driver(
	          Integer.parseInt(d[0]), //driverId
	          d[1], //name
	          d[2], //phone
	          d[3], //vehicle plate
	          VehicleType.valueOf(d[4].trim().toUpperCase())
	          );
	        addDriver(driver);
	      }
	      read.close();
	      return true;
	    } catch (Exception e) {
	      return false;
	    }

	}

@override
public IRider searchRiderById(int riderId) {          //Searches for a rider by ID.
	return riderList.findById(riderId);
}

@override 
public IRider searchRiderByEmail(String email) {      //Searches for a rider by email.
	return riderList.findByEmail(email);
}

@override
public LinkedList<IRider> searchRidersByName(String fullName){          //Searches for all riders whose full name exactly matches the given name.
	return riderList.findByName(fullName);
}

@override
public LinkedList<IRider> searchRidersByHomeCity(String homeCity){    //Searches for all riders from the specified home city.
	return riderList.findByHomeCity(homeCity);
}

@override
public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation){
	return rideList.findByPickupLocation(pickupLocation);
}

@override
public LinkedList<IRider> getAllRiders(){   //Returns all riders stored in the system.
	return riderList.getAll();
}

@override
public IDriver searchDriverById(int driverId) {    //Searches for a driver by ID.
	return driverList.findById(driverId);
}

@override
public IDriver searchDriverByVehiclePlate(String vehiclePlate) {   //Searches for a driver by vehicle plate.
	return driverList.findByVehiclePlate(vehiclePlate);
}

@override
public LinkedList<IDriver> searchDriversByVehicleType(VehicleType vehicleType){  //Searches for all drivers with the specified vehicle type.
	return driverList.findByVehicleType(vehicleType);
}

@override
public LinkedList<IDriver> getAllDrivers(){  //Returns all drivers stored in the system.
	return driverList.getAll();
}

@override
public LinkedList<IRide> getAllRidesAlphabetically(){  //Returns all rides (private and shared) alphabetically ordered by pickup location.
	return rideList.getAllAlphabetically();
}


private boolean isOverlap (IDateTime p1, IDateTime d1, IDateTime p2, IDateTime d2) {
	return p1.compareTo(d2) < 0 && p2.compareTo(d1) < 0;
}

private boolean hasConflict(LinkedList<IRide> rideHistory, IDateTime pickupTime, IDateTime dropoffTime) {
	if(rideHistory.empty()) {
		return false;
	}
	
	rideHistory.findfirst();
	while(true) {
		IRide ride = rideHistory.retrieve();
		if (isOverlap(pickupTime, dropoffTime, ride.getPickupTime(), ride.getDropoffTime())) {
			return true;
		}
		
		if(!rideHistory.last()) {
			rideHistory.findnext();
		}
		else {
			return false;
		}
	}
}

@override
public boolean schedulePrivateRide(String pickupLocation, IDateTime pickupTime, IDateTime
		dropoffTime, String dropoffLocation, int riderId, int driverId) {
	IDriver driver = searchDriverById(driverId);
	
	IRider rider = searchRiderById(riderId);
	if(rider == null || driver == null) {
		return false;
	}
	if (hasConflict(driver.getRideHistory(), pickupTime, dropoffTime)) {
		return false;
	}
	if (hasConflict(rider.getRideHistory(), pickupTime, dropoffTime)) {
		return false;
	}
	
	PrivateRide ride = new PrivateRide (pickupLocation, pickupTime, dropoffTime, dropoffLocation, driver, rider);
	rideList.addRide(ride);
	rider.getRideHistory().insert(ride);
	driver.getRideHistory().insert(ride);
	return true;
}

@override
public boolean scheduleSharedRide(String pickupLocation, IDateTime pickupTime, IDateTime
		dropoffTime, String dropoffLocation, int[] riderIds, int driverId) {
	IDriver driver = driverList.findById(driverId);
	if (driver == null || riderIds == null || riderIds.length == 0) {
		return false;
	}
	IRider[] riders = new IRider[riderIds.length];
	for (int i = 0; i < riderIds.length; i++) {
		
		riders[i] = searchRiderById(riderIds[i]);
		
		if (riders[i] == null) {
			return false;
		}
	}
	
	if (hasConflict(driver.getRideHistory(), pickupTime, dropoffTime)) {
		return false;
	}
	
	for (int i = 0; i < riders.length; i++) {
		if (hasConflict(riders[i].getRideHistory(), pickupTime, dropoffTime)) {
			return false;
		}
	}
	
	SharedRide ride = new SharedRide (pickupLocation, pickupTime, dropoffTime, dropoffLocation, driver);
	driver.getRideHistory().insert(ride);
	for (int i = 0; i < riders.length; i++) {
	ride.addParticipant(riders[i]);
	riders[i].getRideHistory().insert(ride);
	}
	rideList.addRide(ride);
	return true;
}


@override
public boolean loadRidesFromCSV (String filePath) {
  try {
       Scanner read = new Scanner (new File (filePath));
        while (read.hasNextLine()){ 
        	String[] d = read.nextLine().trim().split(",");
        	
        	String rideType = d[0];
        	String pickupLocation = d[1];
        	
        	DateTime pickupTime = DateTime.parse(d[2]);
        	DateTime dropoffTime = DateTime.parse(d[3]);
        	
        	String dropofflocation = d[4];
        	
        	int driverId = Integer.parseInt(d[5]);
        	
        	if (rideType.equalsIgnoreCase("PRIVATE")) {
        		
        		int riderId = Integer.parseInt(d[6]);
        		schedulePrivateRide (
        				pickupLocation,
        				pickupTime,
        				dropoffTime,
        				dropofflocation,
        				riderId,
        				driverId
        				);
        		} else if (rideType.equalsIgnoreCase("SHARED")) {
	String[] riderStrings = d[6].split (";");
	int[] riderIds = new int[riderStrings.length];
	
	for  (int i = 0; i < riderStrings.length; i++) {
		riderIds[i] = Integer.parseInt(riderStrings[i].trim());
	}
	scheduleSharedRide(
			pickupLocation,
			pickupTime,
			dropoffTime,
			dropofflocation,
			riderIds,
			driverId
	);
	
}
        }
        
        read.close();
        return true;
  } catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
  }

@override
public LinkedList<IRide> searchRidesByRiderName(String riderName){
	return rideList.findByRiderName(riderName);
}

@override
public LinkedList<IRider> getSharedRideParticipants(String pickupLocation){
	LinkedList<IRide> rides = rideList.findByPickupLocation(pickupLocation);
	if(!rides.empty()) {
		rides.findfirst();
		while (!rides.last()) {
			IRide r = rides.retrieve();
			if (r instanceof ISharedRide) {
				return ((ISharedRide) r).getParticipants();
			}
			rides.findnext();
		}
		IRide r = rides.retrieve();
		if (r instanceof ISharedRide) {
			return ((ISharedRide) r).getParticipants();
		}
	}
	return new LinkedList<IRider>();
}


private void removeRideFromHistory(LinkedList<IRide> history, int rideId) {
	if(history.empty()) {
		return;
	}
	history.findfirst();
	while(true) {
		if(history.retrieve().getRideId() == rideId) {
			history.remove();
			return;
		}
		if(!history.last()) {
			history.findnext();
		}
		else {
			break;
		}
	}
}


@override
public boolean removeRider(int riderId) {
	IRider rider = riderList.findById(riderId);
	if(rider == null) {
		return false;
	}
	LinkedList<IRide> allRides = rideList.getAllAlphabetically();
	if(allRides.empty()) {
		return false;
	}
	allRides.findfirst();
	while(true) {
		IRide ride = allRides.retrieve();
		boolean removed = false;
		boolean wasLast = allRides.last();
		if(ride.hasRider(riderId)) {
			if(ride instanceof IPrivateRide) {
				if(ride.getDriver() != null) {
					removeRideFromHistory(ride.getDriver().getRideHistory(), ride.getRideId());
					}
				rideList.removeRideById(ride.getRideId());
				removed = true;
		}
			
			else if(ride instanceof ISharedRide) {
				ISharedRide sharedRide = (ISharedRide) ride;
				sharedRide.removeParticipantById(riderId);
				if(sharedRide.isEmpty()) {
					if(ride.getDriver() != null) {
						removeRideFromHistory(ride.getDriver().getRideHistory(), ride.getRideId());
					}
					
					rideList.removeRideById(ride.getRideId());
					removed = true;
				}
			}
			
		}
		
		if(wasLast) {
			break;
		}
		
		if(!removed) {
			allRides.findnext();
		}
	}
	
	riderList.removeById(riderId);
	return true;
}

public boolean removeDriver(int driverId) {
	IDriver driver = driverList.findById(driverId);
	if(driver == null){
		return false;
	}
	
	LinkedList<IRide> allRides = rideList.getAllAlphabetically();
	
	if(!allRides.empty()) {
		allRides.findfirst();
		while(true) {
			IRide ride = allRides.retrieve();
			boolean wasLast = allRides.last();
			boolean removed = false;
			
			if(ride != null && ride.getDriver() != null && ride.getDriver().getId() == driverId) {
				int currentRideId = ride.getRideId();
				
				if(ride instanceof IPrivateRide) {
					IPrivateRide privateRide = (IPrivateRide) ride;
					if(privateRide.getRider() != null) {
						removeRideFromHistory(privateRide.getRider().getRideHistory(), currentRideId);
					}
				}
				
				else if(ride instanceof ISharedRide) {
					ISharedRide sharedRide = (ISharedRide) ride;
					LinkedList<IRider> participants = sharedRide.getParticipants();
					
					if(participants != null && !participants.empty()) {
						
						participants.findfirst();
						while(true) {
							IRider participant = participants.retrieve();
							if(participant != null) {
								removeRideFromHistory(participant.getRideHistory(), currentRideId);
							}
							
							if(participants.last()) {
								break;
							}
							participants.findnext();
						}
					}
				}
				
				rideList.removeRideById(currentRideId);
				removed = true;
			}
			
			if(wasLast) {
				break;
			}
			
			if(!removed) {
				allRides.findnext();
			}
		}
		
		
		
	}
	driverList.removeById(driverId);
	return true;
	
}

}


      
  
  
