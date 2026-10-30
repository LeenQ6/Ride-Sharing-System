package project212;

	public class Driver extends Person implements IDriver{
		private String vehiclePlate ; 
		private VehicleType vehicleType ;

		public Driver(int id , String name , String phoneNumber , String vehiclePlate , VehicleType vehicleType ){
		super(id , name , phoneNumber);
		setVehiclePlate(vehiclePlate); 
		this.vehicleType = vehicleType;}

		public String getVehiclePlate(){
		return vehiclePlate;}

		public void setVehiclePlate(String vehiclePlate){//
		if (vehiclePlate == null || vehiclePlate.length() != 7)
		throw new IllegalArgumentException("The Vehicle Plate Must Consist of 3 Uppercase Letters Followed by 4 Digits");

		for ( int i = 0 ; i < 3 ; i++)
		if ( !Character.isUpperCase(vehiclePlate.charAt(i)))
		throw new IllegalArgumentException("The Vehicle Plate Must Start With 3 Uppercase Letters");


		for ( int i = 3 ; i < 7 ; i++)
		if ( !Character.isDigit(vehiclePlate.charAt(i)))
		throw new IllegalArgumentException("The Vehicle Plate Must End With 4 Digits");

		this.vehiclePlate = vehiclePlate;} 

		public VehicleType getVehicleType(){
		return vehicleType;}

		public void setVehicleType(VehicleType vehicleType){
		this.vehicleType = vehicleType ;} 


		public int compareTo(IDriver other){
		return this.id - other.getId();}

		public String toString(){
		return super.toString()+" ,vehicle Plate: " +vehiclePlate+" ,vehicle Type: " +vehicleType;}
		}

