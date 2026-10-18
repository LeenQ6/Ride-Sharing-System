public class SharedRide extends Ride implements ISharedRide{

   private LinkedList<IRider> participants;

   public SharedRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,String dropoffLocation, IDriver driver){
   
      super(pickupLocation, pickupTime, dropoffTime, dropoffLocation, driver);
      participants=new LinkedList<IRider>();
   }
   
   public LinkedList<IRider> getParticipants(){
      return participants;
   }
   

   public boolean addParticipant(IRider rider){
      if(rider==null)
         return false;
      if(hasRider(rider.getId()))
         return false;
      participants.insert(rider);
      return true;
   }

   public boolean removeParticipantById(int riderId){
      if(participants.empty())
         return false;
   
      participants.findFirst();
   
      while(true){
         if(participants.retrieve().getId==riderId){
            participants.remove();
            return true;
         }//end if
         if(!participants.last())
            participants.findNext();
         else
            return false;
      }//end while
   
   }//end method


   public boolean isEmpty(){
      return participants.empty();
   }

   public boolean hasRider(int riderId){
   
      if(participants.empty())
         return false;
   
      participants.findFirst();
   
      while(true){
      
         if(participants.retrieve().getId()==riderId)
            return true;
      
         if(!participants.last())
            participants.findNext();
         else
            return false;
      }//end while
   }//end method

   public String toString(){
      Display_all_participants();
      return "SharedRide[" + super.toString() + ", Participants Count: " + countParticipants() + "]";
   }

   private int countParticipants(){
      return participants.size();
   }

   public void Display_all_participants(){
      if(participants.empty()){
         System.out.println("No rides found.");
         return;
      }//end if
      participants.findFirst();
      while(true){
         System.out.println(participants.retrieve());
         if(participants.last()){
            break;
         }//end if loop
         participants.findNext();
      }//end while
   
   
   }//end method










}//end class