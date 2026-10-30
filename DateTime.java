package project212;


	public class DateTime implements IDateTime{
		private int year; 
		private int month; 
		private int day;
		private int hour;
		private int minute;

		public DateTime(int year, int month, int day, int hour, int minute){
		if(year<1)
		return;

		if(day<1 || day>31)
		return;

		if(hour<0 || hour>23)
		    return;
		

		if(minute<0 || minute>59)
		return;

		this.year=year;
		this.month=month;
		this.day=day;
		this.hour=hour;
		this.minute=minute;

		}//end constructor

		public static DateTime parse(String str){

		String[] parts=str.trim().split(" ");

		String[] dateParts=parts[0].split("/");

		String[] timeParts=parts[1].split(":");

		int month=Integer.parseInt(dateParts[0].trim());
		int day=Integer.parseInt(dateParts[1].trim()) ;
		int year=Integer.parseInt(dateParts[2].trim());
		int hour=Integer.parseInt(timeParts[0].trim());
		int minute=Integer.parseInt(timeParts[1].trim());

		return new DateTime(year, month, day, hour, minute);
		}

		@Override
		public int getYear(){
		return year;
		}

		@Override
		public int getMonth(){
		return month;
		}

		@Override
		public int getDay(){
		return day;
		}

		@Override
		public int getHour(){
		return hour;
		}

		@Override
		public int getMinute(){
		return minute;
		}

		@Override
		public int compareTo(IDateTime other){

		if(this.year != other.getYear())
		return this.year-other.getYear();

		if(this.month != other.getMonth())
		return this.month-other.getMonth();

		if(this.day != other.getDay())
		return this.day-other.getDay();

		if(this.hour != other.getHour())
		return this.hour-other.getHour();

		return this.minute-other.getMinute();
		}

		@Override
		public String format(){

			return String.format("%02d/%02d/%04d %02d:%02d", month, day, year, hour, minute);

		}

		@Override 
		public String toString(){
		return format();
		}





		}//end class

