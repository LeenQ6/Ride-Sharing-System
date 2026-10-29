public abstract class Person implements IPerson {

protected int id ; 
protected String name ; 
protected String phoneNumber ; 
protected LinkedList <IRide> rideHistory ; 


public Person(int id ,String name ,String phoneNumber){
this.id = id; 
this.name = name;
setPhoneNumber(phoneNumber);
rideHistory = new LinkedList <IRide>();}

public int getId(){
return id;}

public String getName(){
return name;}

public void setName(String name){
this.name = name;}

public String getPhoneNumber(){
return phoneNumber;}

public void setPhoneNumber(String phoneNumber){
if (phoneNumber==null||phoneNumber.length()!=10)
throw new IllegalArgumentException("The Phone Number Must Consist of 10 Digits") ; 
for(int i=0 ; i<phoneNumber.length() ;i++)
if(!Character.isDigit(phoneNumber.charAt(i)))
throw new IllegalArgumentException("The Phone Number Must Contain Digits Only") ; 

this.phoneNumber = phoneNumber ; 
}

public LinkedList<IRide> getRideHistory(){
return rideHistory;}

public String toString(){
return "id: "+id+", name: "+name+", phone number: " +phoneNumber;}

}
