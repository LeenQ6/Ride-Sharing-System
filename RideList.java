package project212;

public class RideList implements IRideList{
	   private LinkedList<IRide> all_rides;

	   public RideList() {
	      all_rides=new LinkedList<>();
	   }

	   public LinkedList<IRide> getAllAlphabetically(){
	      return all_rides;
	   }

	   public int size(){
	      return all_rides.size();
	   }

	   public boolean addRide(IRide ride){
	      if(ride==null){
	         return false;
	      }//end if
	   
	      if(all_rides.empty()){
	         all_rides.insert(ride);
	         return true;
	      }//end if
	      all_rides.findfirst();
	      while(true){
	         IRide current=all_rides.retrieve();
	         if(ride.compareTo(current)<0){
	            all_rides.insertBeforeCurrent(ride);
	            return true;
	         }//end if
	         if(all_rides.last()){
	            break;
	         }
	         all_rides.findnext();
	      
	      
	      
	      }//end while
	      all_rides.insert(ride);
	      return true;
	   }//end method

	   public boolean removeRideById(int rideId){
	      if(all_rides.empty()){
	         return false;
	      }//end if
	   
	      int size=all_rides.size();
	      all_rides.findfirst();
	      for(int i=1; i<=size; i++){
	         IRide current=all_rides.retrieve();
	         if(current.getRideId()==rideId){
	            all_rides.remove();
	            return true;
	         }//end if
	         if(!all_rides.last()){
	            all_rides.findnext();
	         }//end if
	      
	      }//end for loop
	      return false;
	   }//end method

	   public LinkedList<IRide> findByPickupLocation(String pickupLocation){
	      LinkedList<IRide> result=new LinkedList<>();
	   
	      if(pickupLocation==null || all_rides.empty()){
	         return result;
	      }//end if
	   
	      all_rides.findfirst();
	      while(true){
	         IRide current =all_rides.retrieve();
	         if(current.getPickupLocation().equalsIgnoreCase(pickupLocation)){
	            result.insert(current);
	         }
	      
	         if(all_rides.last()){
	            break;
	         }
	         else
	            all_rides.findnext();
	      }//end while
	   
	      return result;
	   
	   }//end method

	   private boolean isRiderInPrivateRide(IPrivateRide ride, String riderFullName){
	      return ride.getRider()!=null && ride.getRider().getName().equals(riderFullName);
	   }//end method 

	   private boolean isRiderInSharedRide(ISharedRide ride, String riderFullName){
	      LinkedList<IRider> participants=ride.getParticipants();
	      if(participants==null || participants.empty()){
	         return false;
	      }//end if
	      participants.findfirst();
	   
	      while(true){
	         IRider rider=participants.retrieve();
	         if(rider !=null && riderFullName.equals(rider.getName())){
	            return true;
	         }//end if
	      
	         if(participants.last()){
	            break;
	         }
	         participants.findnext();
	      
	      }//end while
	      return false;
	   }//end method


	   public LinkedList<IRide> findByRiderName(String riderFullName){
	      LinkedList<IRide> result=new LinkedList<>();
	   
	      if(riderFullName==null || all_rides.empty()){
	         return result;
	      }
	   
	      all_rides.findfirst();
	   
	      while(true){
	         IRide current=all_rides.retrieve();
	         boolean found=false;
	      
	         if(current instanceof IPrivateRide){
	            found= isRiderInPrivateRide((IPrivateRide)current, riderFullName);
	         }//end if
	         
	         else if(current instanceof ISharedRide){
	            found=isRiderInSharedRide((ISharedRide)current ,riderFullName);
	         }//end if
	      
	         if(found){
	            result.insert(current);
	         }//end if
	      
	         if(all_rides.last()){
	            break;
	         }//end if
	         all_rides.findnext();
	      }//end while
	      return result;
	   }//end method

	   public static void Display_all_rides(RideList rideList){
	      System.out.println("\\n====== ALL RIDES ======");
	   
	      LinkedList<IRide> rides=rideList.getAllAlphabetically();
	   
	      if(rides.empty()){
	         System.out.println("No rides found.");
	         return;
	      }//end if
	      rides.findfirst();
	   
	      while(true){
	         System.out.println(rides.retrieve());
	      
	         if(rides.last()){
	            break;
	         }
	      
	         rides.findnext();
	      
	      }//end while
	   }//end method


	//Maybe i will delete this class
	   public static void Display_search_result(LinkedList<IRide> result){
	   
	      if(result.empty()){
	         System.out.println("No rides found");
	      }//end if
	      result.findfirst();
	   
	      while(true){
	         System.out.println(result.retrieve());
	      
	         if(result.last()){
	            break;
	         }
	         result.findnext();
	      
	      
	      
	      }//end while
	   }//end method

















	}//end class
