public class PrivateRide extends Ride implements IPrivateRide{
   private IRider rider;

   public PrivateRide(String pickupLocation, IDateTime pickupTime,IDateTime dropoffTime,String dropoffLocation, IDriver driver, IRider rider){
      super(pickupLocation, pickupTime, dropoffTime, dropoffLocation, driver);
      this.rider=rider;
   
   }

   public IRider getRider(){
      return rider;
   }

   public void setRider(IRider rider){
      this.rider=rider;
   }

   public boolean hasRider(int riderId){
      return rider !=null && rider.getId()==riderId;
   }

   public String toString(){
      return "PrivateRide[" +super.toString()+", Rider: "+(rider !=null? rider.getName() :"None") +")";
   }

















}//end class