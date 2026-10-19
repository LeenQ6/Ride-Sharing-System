public class DriverList implements IDriverList{

private LinkedList<IDriver> drivers ; 

public DriverList(){
drivers = new LinkedList<IDriver>() ; }

public LinkedList<IDriver> getAll(){
return drivers ;}

public boolean add (IDriver driver){
if (driver == null )
return false ; 

if(drivers.empty()){
drivers.insert(driver) ; 
return true ;}

drivers.findfirst() ; 
while(true)
{
IDriver currentDriver = drivers.retrieve() ; 
if ( driver.compareTo( currentDriver ) == 0)
return false ; 
if(driver.getVehiclePlate().equalsIgnoreCase(currentDriver.getVehiclePlate()))
return false ; 
if ( driver.compareTo( currentDriver ) < 0)
{
drivers.insertBeforeCurrent(driver) ; 
return true ; 
}

if ( drivers.last() )
break;

drivers.findnext() ; 
}
drivers.insert(driver) ; 
return true ; 
}

public int size(){
return drivers.size() ; }

public IDriver findById (int driverId){
if (drivers.empty())
return null ;

drivers.findfirst() ; 

while(true){
IDriver currentDriver = drivers.retrieve() ;
if (currentDriver.getId() == driverId)
return currentDriver ; 

if(drivers.last())
break ; 

drivers.findnext() ; 
}
return null ;}

public IDriver findByVehiclePlate (String vehiclePlate){
if (vehiclePlate == null || drivers.empty())
return null ;

drivers.findfirst() ; 

while(true){
IDriver currentDriver = drivers.retrieve() ;
if (currentDriver.getVehiclePlate().equalsIgnoreCase(vehiclePlate))
return currentDriver ; 

if(drivers.last())
break ; 

drivers.findnext() ; 
}
return null ;}


public LinkedList<IDriver> findByName(String fullName){

LinkedList<IDriver> LLD = new LinkedList<IDriver>();

if(fullName == null || drivers.empty() )
return LLD ;

drivers.findfirst() ; 

while(true){
IDriver currentDriver = drivers.retrieve() ;
if (currentDriver.getName().equals(fullName))
LLD.insert(currentDriver) ;  

if(drivers.last())
break ; 

drivers.findnext() ; 
}
return LLD ;} 

public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType){

LinkedList<IDriver> LLD = new LinkedList<IDriver>();

if(vehicleType == null || drivers.empty() )
return LLD ;

drivers.findfirst() ; 

while(true){
IDriver currentDriver = drivers.retrieve() ;
if (currentDriver.getVehicleType() == vehicleType)
LLD.insert(currentDriver) ;  

if(drivers.last())
break ; 

drivers.findnext() ; 
}
return LLD ;} 

public boolean removeById ( int driverId ){
if ( drivers.empty() )
return false ; 

drivers.findfirst() ; 

while(true){
IDriver currentDriver = drivers.retrieve() ;
if (currentDriver.getId() == driverId ){
drivers.remove() ; 
return true ; }

else if(drivers.last())
break ; 

else
drivers.findnext() ; 
}

return false ; 
}

public boolean removeByVehiclePlate (String vehiclePlate ){
if ( vehiclePlate == null || drivers.empty() )
return false ; 

drivers.findfirst() ; 

while(true){
IDriver currentDriver = drivers.retrieve() ;
if (currentDriver.getVehiclePlate().equalsIgnoreCase(vehiclePlate) ){
drivers.remove() ; 
return true ; }

else if(drivers.last())
break ; 

else
drivers.findnext() ; 
}

return false ; 
}

public int removeByName(String fullName){
if ( fullName == null || drivers.empty())
return 0 ; 

int count = 0 ; 
int length = drivers.size() ; 

drivers.findfirst() ; 
for(int i = 1 ; i <= length ; i ++ ){
IDriver currentDriver = drivers.retrieve() ;
if (currentDriver.getName().equals(fullName) ){
drivers.remove() ; 
count++ ; }

else
drivers.findnext() ; 
}
return count ; 
}

public int removeByVehicleType(VehicleType vehicleType){
if ( vehicleType == null || drivers.empty())
return 0 ; 

int count = 0 ; 
int length = drivers.size() ; 

drivers.findfirst() ; 
for(int i = 1 ; i <= length ; i ++ ){
IDriver currentDriver = drivers.retrieve() ;
if (currentDriver.getVehicleType() == vehicleType ){
drivers.remove() ; 
count++ ; }

else
drivers.findnext() ;
}
return count ; 
}




}
