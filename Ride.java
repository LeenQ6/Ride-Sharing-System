public abstract class Ride implements IRide{

   private static int idCounter=1;
   protected final int rideId;
   protected String pickupLocation;
   protected final IDateTime pickupTime;
   protected final IDateTime dropoffTime;
   protected String dropoffLocation;
   protected IDriver driver;

   public Ride(String pickupLocation ,IDateTime pickupTime ,IDateTime dropoffTime ,String dropoffLocation , IDriver driver){
   
      this.rideId=idCounter++;
      this.pickupLocation=pickupLocation;
      this.pickupTime=pickupTime;
      this.dropoffTime=dropoffTime;
      this.dropoffLocation=dropoffLocation;
      this.driver=driver;
   
   }//end constructor

   
   public int getRideId(){
      return rideId;
   }

   
   public String getPickupLocation(){
      return pickupLocation;
   }

   
   public void setPickupLocation(String pickupLocation){
      this.pickupLocation=pickupLocation;
   }

   
   public IDateTime getPickupTime(){
      return pickupTime;
   }

   
   public IDateTime getDropoffTime(){
      return dropoffTime;
   }

   
   public String getDropoffLocation(){
      return dropoffLocation;
   }

   
   public void setDropoffLocation(String dropoffLocation){
      this.dropoffLocation=dropoffLocation;
   }

   
   public IDriver getDriver(){
      return driver;
   }

   
   public void setDriver(IDriver driver){
      this.driver=driver;
   }

   
   public int compareTo(IRide other){
      return this.pickupLocation.compareToIgnoreCase(other.getPickupLocation());
   }

   
   public String toString(){
      return "RideID: "+rideId+", From: "+pickupLocation+" To: "+dropoffLocation+", Pickup: "+pickupTime.format()+", Dropoff: "+dropoffTime.format()+", Driver: "+(driver !=null? driver.getName() : "None");
   
   
   }



}//end class