public class RiderList implements IRiderList{

private LinkedList<IRider> riders ; 

public RiderList(){
riders = new LinkedList<IRider>() ; }

public LinkedList<IRider> getAll(){
return riders ;}

public boolean add (IRider rider){
if (rider == null )
return false ; 

if(riders.empty()){
riders.insert(rider) ; 
return true ;}

riders.findfirst() ; 
while(true)
{
IRider currentRider = riders.retrieve() ; 
if ( rider.compareTo( currentRider ) == 0)
return false ; 

if ( rider.compareTo( currentRider ) < 0)
{
riders.insertBeforeCurrent(rider) ; 
return true ; 
}

if ( riders.last() )
break;

riders.findnext() ; 
}
riders.insert(rider) ; 
return true ; 
}

public int size(){
return riders.size() ; }

public IRider findById (int riderId){
if (riders.empty())
return null ;

riders.findfirst() ; 

while(true){
IRider currentRider = riders.retrieve() ;
if (currentRider.getId() == riderId)
return currentRider ; 

if(riders.last())
break ; 

riders.findnext() ; 
}
return null ;}

public IRider findByEmail (String email){
if (email == null || riders.empty())
return null ;

riders.findfirst() ; 

while(true){
IRider currentRider = riders.retrieve() ;
if (currentRider.getEmail().equalsIgnoreCase(email))
return currentRider ; 

if(riders.last())
break ; 

riders.findnext() ; 
}
return null ;}


public LinkedList<IRider> findByName(String fullName){

LinkedList<IRider> LLR = new LinkedList<IRider>();

if(fullName == null || riders.empty() )
return LLR ;

riders.findfirst() ; 

while(true){
IRider currentRider = riders.retrieve() ;
if (currentRider.getName().equals(fullName))
LLR.insert(currentRider) ;  

if(riders.last())
break ; 

riders.findnext() ; 
}
return LLR ;} 

public LinkedList<IRider> findByHomeCity(String homeCity){

LinkedList<IRider> LLR = new LinkedList<IRider>();

if(homeCity == null || riders.empty() )
return LLR ;

riders.findfirst() ; 

while(true){
IRider currentRider = riders.retrieve() ;
if (currentRider.getHomeCity().equalsIgnoreCase(homeCity))
LLR.insert(currentRider) ;  

if(riders.last())
break ; 

riders.findnext() ; 
}
return LLR ;} 

public boolean removeById ( int riderId ){
if ( riders.empty() )
return false ; 

riders.findfirst() ; 

while(true){
IRider currentRider = riders.retrieve() ;
if (currentRider.getId() == riderId ){
riders.remove() ; 
return true ; }

else if(riders.last())
break ; 

else
riders.findnext() ; 
}

return false ; 
}

public boolean removeByEmail (String email ){
if ( email == null || riders.empty() )
return false ; 

riders.findfirst() ; 

while(true){
IRider currentRider = riders.retrieve() ;
if (currentRider.getEmail().equalsIgnoreCase(email) ){
riders.remove() ; 
return true ; }

else if(riders.last())
break ; 

else
riders.findnext() ; 
}

return false ; 
}

public int removeByName(String fullName){
if ( fullName == null || riders.empty())
return 0 ; 
  
int count = 0 ; 
 
riders.findfirst() ; 
while (true){
IRider currentRider = riders.retrieve() ;
if(currentRider.getName().equals(fullName)){
riders.remove() ; 
count++ ;
if ( riders.empty())
break;
}
else if(riders.last())
break ; 
else
riders.findnext() ; 
}
return count ; 
}

public int removeByHomeCity(String homeCity){
if ( homeCity == null || riders.empty())
return 0 ; 

int count = 0 ; 
 
riders.findfirst() ; 
while(true){
IRider currentRider = riders.retrieve() ;
if (currentRider.getHomeCity().equalsIgnoreCase(homeCity) ){
riders.remove() ; 
count++ ; 
if(riders.empty())
break ; 
}

else if(riders.last())
break ; 
else
riders.findnext() ; 
}
return count ; 
}


}
