package bus;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
 abstract class Vehicle {
 public Scanner x=new Scanner(System.in);
 public Scanner r;
 private BufferedWriter w;
 HashMap<String,ArrayList<String>> tripsTrainOne=new HashMap<>();
 HashMap<String,ArrayList<String>> tripsTrainTwo=new HashMap<>();
 HashMap<String,ArrayList<String>> tripsTrainNon=new HashMap<>();
 HashMap<String,ArrayList<String>> tripsBusOne=new HashMap<>();
 HashMap<String,ArrayList<String>> tripsBusTwo=new HashMap<>();
 HashMap<String,ArrayList<String>> tripsBusNon=new HashMap<>();
 HashMap<String,ArrayList<String>> tripsLimousine=new HashMap<>();
 private HashMap<String,ArrayList<String>> seatTrip=new HashMap<>();
 ArrayList<String> trips=new ArrayList<>();
 ArrayList<String> trip=new ArrayList<>();
 List<String> seats=new ArrayList<>();
 private String ID;
 private int Seats;
 private HashMap<String,ArrayList<String>> tripHash=new HashMap<>();
 void setTripHash(HashMap<String,ArrayList<String>> tripHash){
     this.tripHash=tripHash;
 }
 HashMap<String,ArrayList<String>> getTripHash(){
     return tripHash;
 }
 HashMap<String,ArrayList<String>> Trips(String FileName,int i,int j,HashMap<String,ArrayList<String>>tripHash){
  try{
   r=new Scanner(new File(FileName));
   while (r.hasNext()){
                setID(r.next());
    tripHash.put(getID(), new ArrayList<>());
    for(int l=0;l<j+i;l++){
     trips.add(l,r.next());
     tripHash.get(getID()).add(trips.get(l));
     }
   }
   r.close();
   }
  catch(Exception e){
   System.out.println("No array");
  }
  return tripHash;
 }
 boolean searchExistence(HashMap<String,ArrayList<String>> triphash,String id){
  if(tripHash.containsKey(ID)){
      setTripHash(triphash);
      setID(id);
      return true;
 }
  else return false;
 }
 int availability(){
     
  return tripHash.size();
 }
ArrayList<String> returnTrip(){
   ArrayList<String> tri=new ArrayList<String>();
  
      tri.addAll(tripHash.get(ID));

  return tri;
}
 void bookTicket(HashMap<String,ArrayList<String>>tripHash,int i,int j,String ID){
  
 	try{
  ArrayList<String> tri=new ArrayList<>();
  tri.addAll(tripHash.get(ID));
  tripHash.get(ID).removeAll(tripHash.get(ID));
  tripHash.get(ID).addAll(seats(i,j,tri));
  System.out.println(tripHash);
 	}
 	catch(Exception e){
 	 System.out.println("Doesn't exist");
 	}
 }
 HashMap<String,ArrayList<String>> cancelTicket(HashMap<String,ArrayList<String>>tripHash,int i,int j,String ID,List<String> list){
  
  try{
   ArrayList<String> tri=new ArrayList<>();
   tri.addAll(tripHash.get(ID));
   tripHash.get(ID).removeAll(tripHash.get(ID));
   tripHash.get(ID).addAll(returnSeat(i,j,tri,list));
   System.out.println(tripHash);
  	}
  	catch(Exception e){
  	 System.out.println("Doesn't exist");
  	}
  return tripHash;
 }
 ArrayList<String> seats(int i,int j,ArrayList<String> tri){
  seats.clear();
  int index;
  int l;
  String[]seat=new String[j];
  for(l=0;l<j;l++)
  {
   seat[l]=tri.get(i+l);
  }
  System.out.println("Add Seat:\nChoose from 1 till 14");
  index=x.nextInt();
  try{
   while(seat[index-1].equals("0")&&index>0&&index-1<j){
   seat[index-1]=Integer.toString(index);
   seats.add(Integer.toString(index));
  System.out.println("Add Seat:\nChoose from 1 till 14");
  index=x.nextInt();
  }
	System.out.println(seats);
  if(seat[index-1]!="0"){
   System.out.println("Seat taken");
  }
 	}
  catch(Exception e){
   System.out.println("Out of range");
  }
  tri.subList(i, tri.size()).clear();
  for(String m:seat){
   tri.add(i,m);
   i++;
  }
  return tri;
 }
 void updateSeats(HashMap<String,ArrayList<String>>tripHash,String Filename){
  try{
  List<String>list= new ArrayList<>(tripHash.keySet());
    	w = new BufferedWriter(new FileWriter(Filename));
    	for (int i=0;i<list.size();i++) {
        	w.write(list.get(i));
        	w.write(" ");
        	for(String j:tripHash.get(list.get(i))){
        	 w.write(j);
        	 w.write(" ");
        	}
        	w.newLine();
      	}
  w.close();
  }
  catch(Exception e){
   System.out.println("LA2 arrrrrray");
 }  
}
 List<String> returnSeat(int i,int j,ArrayList<String> tri,List<String> list){
  String[]compareSeat=new String[j];
  for(int l=0;l<j;l++)
  {
   compareSeat[l]=tri.get(i+l);
  }
  for(int k=0;k<list.size();k++){
   for(int l=0;l<j;l++)
   {
     if(compareSeat[l].equals(list.get(k))){
      compareSeat[l]="0";
     }
    }
  }
  
  tri.subList(i, tri.size()).clear();
  for(String m:compareSeat){
   tri.add(i,m);
   i++;
  }
  return tri;
 }
 boolean Search(HashMap<String,ArrayList<String>> tripHash,String ID){
  if(tripHash.containsKey(ID))
   return true;
  else
   return false; 
 }
 void setSeatTrip(HashMap<String,ArrayList<String>> seatTrip){
     this.seatTrip=seatTrip;
 }
 HashMap<String,ArrayList<String>> getSeatTrip(){
     return seatTrip;
 }
 
    abstract  HashMap <String,ArrayList<String>> chooseTicket(int stop);
  
   // abstract int availability(HashMap<String,ArrayList<String>>tripHash,String ID);
		
    abstract void showTicket(HashMap<String,ArrayList<String>>tripHash,int i);

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the SeatsT
     */
    public int getSeats() {
        return Seats;
    }

    /**
     * @param SeatsT the SeatsT to set
     */
    public void setSeatsT(int SeatsT) {
        this.Seats= Seats;
    }
  
}
class Limousine extends Vehicle{
 HashMap <String,ArrayList<String>> chooseTicket(int stop){
  return tripsLimousine;
   
 }
 int availabilityy(HashMap<String,ArrayList<String>>tripHash,int i,String ID){
  return tripHash.size();
 }
 ArrayList<String> returnTrip(int i){
 
  return null;
}
 void showTicket(HashMap<String,ArrayList<String>>tripHash,int i){
  List<String>list= new ArrayList<>(tripHash.keySet());
    	for(int j=0;j<list.size();j++){
    System.out.println("Trip"+ " " + (j+1));
    System.out.println("ID" + " " + list.get(j));
    System.out.println("Source:" + " " + tripHash.get(list.get(j)).get(0));
    System.out.println("Destination" + " " + tripHash.get(list.get(j)).get(1));
    System.out.println("Price" + " " + tripHash.get(list.get(j)).get(2));
 }
}
}
 class Bus extends Vehicle{
     private int seatB;
	public Scanner r;
 public Scanner x=new Scanner(System.in);
 HashMap <String,ArrayList<String>> chooseTicket(int stop){
  if(stop==0){
      setSeatB(0);
       Trips("BusTripsNoStop.txt",14,5,tripsBusNon);
   return tripsBusNon;
  }
  else if(stop==1) {
      setSeatB(1);
       Trips("BusTripsOneStop.txt",14,6,tripsBusOne);
   return tripsBusOne;
  }
  else {
      setSeatB(2);
       Trips("BusTripsTwoStop.txt",14,7,tripsBusTwo);
   return tripsBusTwo;
  }
   
 }
   int availabilityy(HashMap<String,ArrayList<String>>tripHash,int i,String ID){
	int count=0;
	for(int j=0;j<14;j++){
   	if(tripHash.get(ID).get(j+i).equals("0"))
   	{
   	 count++;
   	}
	}
	return count;
  }
   ArrayList<String> returnTrip(int i){
   ArrayList<String> tri=new ArrayList<String>();
   if(i==5){
        setTripHash(tripsBusNon);
        tri.addAll(getTripHash().get(getID()));
   }
   else if(i==6){
      setTripHash(tripsBusOne);
      tri.addAll(getTripHash().get(getID()));
      
}
   else{
        setTripHash(tripsBusTwo);
        tri.addAll(getTripHash().get(getID()));
   }
  return tri;
}
   void showTicket(HashMap<String,ArrayList<String>>tripHash,int i){
   List<String>list= new ArrayList<String>(tripHash.keySet());
        	for(int j=0;j<list.size();j++){
        	 if(i==5){
	     System.out.println("Trip"+ " " + (j+1));
	     System.out.println("   " +"ID" + " " + list.get(j));
	     System.out.println("   " + "Source:" + " " + tripHash.get(list.get(j)).get(0));
	     System.out.println("   " + "Destination" + " " + tripHash.get(list.get(j)).get(1));
	     System.out.println("   " + "Date" + " " + tripHash.get(list.get(j)).get(2));
	     System.out.println("   " + "Time" + " " + tripHash.get(list.get(j)).get(3));
	     System.out.println("   " + "Price" + " " + tripHash.get(list.get(j)).get(4));
	    }
	   else if(i==6){
	     System.out.println("Trip"+ " " + (j+1));
	     System.out.println("   " +"ID" + " " + list.get(j));
	     System.out.println("   " +"Source:" + " " + tripHash.get(list.get(j)).get(0));
	     System.out.println("   " +"Stop 1:" + " " + tripHash.get(list.get(j)).get(1));
	     System.out.println("   " +"Destination" + " " + tripHash.get(list.get(j)).get(2));
	     System.out.println("   " +"Date" + " " + tripHash.get(list.get(j)).get(3));
	     System.out.println("   " +"Time" + " " + tripHash.get(list.get(j)).get(4));
	     System.out.println("   " +"Price" + " " + tripHash.get(list.get(j)).get(5));
	    }
	   else if(i==7){
	     System.out.println("Trip"+ " " + (j+1));
	     System.out.println("   " +"ID" + " " + list.get(j));
	     System.out.println("   " +"Source:" + " " + tripHash.get(list.get(j)).get(0));
	     System.out.println("   " +"Stop 1:" + " " + tripHash.get(list.get(j)).get(1));
	     System.out.println("   " +"Stop 2:" + " " + tripHash.get(list.get(j)).get(2));
	     System.out.println("   " +"Destination" + " " + tripHash.get(list.get(j)).get(3));
	     System.out.println("   " +"Date" + " " + tripHash.get(list.get(j)).get(4));
	     System.out.println("   " +"Time" + " " + tripHash.get(list.get(j)).get(5));
	     System.out.println("   " +"Price" + " " + tripHash.get(list.get(j)).get(6));
	    }
        	}
  }
   boolean searchStop(int count,String ID,List<String> list){
    boolean state=false;
    if(count==5){
     if(Search(Trips("busTripsNoStop.txt",14,5,tripsBusNon),ID))
    	 updateSeats(cancelTicket(tripsBusNon,5,14,ID,list),"busTripsNoStop.txt");
      state=true;
    }
    if(count==6){
     if(Search(Trips("busTripsOneStop.txt",14,6,tripsBusOne),ID))
     	updateSeats(cancelTicket(tripsBusOne,6,14,ID,list),"busTripsOneStop.txt");
      state=true;
    }
    if(count==7){
     if(Search(Trips("busTripsTwoStop.txt",14,7,tripsBusTwo),ID))
     	updateSeats(cancelTicket(tripsBusTwo,7,14,ID,list),"busTripsTwoStop.txt");
      state=true;
    }
    return state;
  }
   HashMap <String,ArrayList<String>> BusNoStop(){
       return tripsBusNon;
   }
   HashMap <String,ArrayList<String>> BusOneStop(){
       return tripsBusOne;
   }
   HashMap <String,ArrayList<String>> BusTwoStop(){
       return tripsBusTwo;
   }

    /**
     * @return the seatB
     */
    public int getSeatB() {
        return seatB;
    }

    /**
     * @param seatB the seatB to set
     */
    public void setSeatB(int seatB) {
        this.seatB = seatB;
    }
}
 class Train extends Vehicle{
     private int seatT;
 HashMap <String,ArrayList<String>> chooseTicket(int stop){
  if(stop==0){
      setSeatT(0);
      Trips("trainTripsNoStop.txt",36,5,tripsTrainNon);
   return tripsTrainNon;
  }
  else if(stop==1) {
      setSeatT(1);
       Trips("trainTripsOneStop.txt",36,6,tripsTrainOne);
   return tripsTrainOne;
  }
  else {
      setSeatT(2);
     Trips("trainTripsTwoStop.txt",36,7,tripsTrainTwo);
   return tripsTrainTwo;
  }
 }
 boolean seats(int i,int j,ArrayList<String> tri,int index){
  int l;
  
  HashMap<String,ArrayList<String>> hash=new HashMap<String,ArrayList<String>>();
  String[]seat=new String[j];
  boolean valid=false;
  hash=getTripHash();
  String id=getID();
  for(l=0;l<j;l++)
  {
   seat[l]=tri.get(i);
  
  }
  if(seat[index-1].equals("0")&&index>0&&index-1<j){
   seat[index-1]=Integer.toString(index);
    if(i==5){
        
        tri.subList(i, tri.size()).clear();
       for(String m:seat){
             tri.add(i,m);
              i++;
  }
      
  hash.get(id).removeAll(hash.get(id));
  hash.get(id).addAll(tri);
        setSeatTrip(hash);//showTrips no Stop;
   valid=true;
    }
   else if(i==6){
       tri.subList(i, tri.size()).clear();
       for(String m:seat){
             tri.add(i,m);
              i++;
  }
  hash.get(id).removeAll(hash.get(id));
  hash.get(id).addAll(tri);
        setSeatTrip(hash);//showTrips One Stop;
   valid= true;
   }
   else if(i==7){
       tri.subList(i, tri.size()).clear();
       for(String m:seat){
             tri.add(i,m);
              i++;
  }
  hash.get(id).removeAll(hash.get(id));
  hash.get(id).addAll(tri);
        setSeatTrip(hash);//showTrips Two Stop;
    valid=true;
           }
  }
  else {
      valid=false;
  }
  return valid;
 }
public  HashMap <String,ArrayList<String>> TrainNoStop(){
       return tripsTrainNon;
   }
 public  HashMap <String,ArrayList<String>> TrainOneStop(){
       return tripsTrainOne;
   }
 public  HashMap <String,ArrayList<String>> TrainTwoStop(){
       return tripsTrainTwo;
   }
  int availabilityy(int i){
   int count=0;
   for(int j=0;j<36;j++){
  	if(getTripHash().get(getID()).get(j+i)=="0");
  	 count++;
   }
   return count;
  }
  
  void showTicket(HashMap<String,ArrayList<String>>tripHash,int i){
   List<String>list= new ArrayList<>(tripHash.keySet());
     	for(int j=0;j<list.size();j++){
   if(i==5){
     System.out.println("Trip"+ " " + (j+1));
     System.out.println("   " +"ID" + " " + list.get(j));
     System.out.println("   " + "Source:" + " " + tripHash.get(list.get(j)).get(0));
     System.out.println("   " + "Destination" + " " + tripHash.get(list.get(j)).get(1));
     System.out.println("   " + "Date" + " " + tripHash.get(list.get(j)).get(2));
     System.out.println("   " + "Time" + " " + tripHash.get(list.get(j)).get(3));
     System.out.println("   " + "Price" + " " + tripHash.get(list.get(j)).get(4));
    }
   else if(i==6){
     System.out.println("Trip"+ " " + (j+1));
     System.out.println("   " +"ID" + " " + list.get(j));
     System.out.println("   " +"Source:" + " " + tripHash.get(list.get(j)).get(0));
     System.out.println("   " +"Stop 1:" + " " + tripHash.get(list.get(j)).get(1));
     System.out.println("   " +"Destination" + " " + tripHash.get(list.get(j)).get(2));
     System.out.println("   " +"Date" + " " + tripHash.get(list.get(j)).get(3));
     System.out.println("   " +"Time" + " " + tripHash.get(list.get(j)).get(4));
     System.out.println("   " +"Price" + " " + tripHash.get(list.get(j)).get(5));
    }
   else if(i==7){
     System.out.println("Trip"+ " " + (j+1));
     System.out.println("   " +"ID" + " " + list.get(j));
     System.out.println("   " +"Source:" + " " + tripHash.get(list.get(j)).get(0));
     System.out.println("   " +"Stop 1:" + " " + tripHash.get(list.get(j)).get(1));
     System.out.println("   " +"Stop 2:" + " " + tripHash.get(list.get(j)).get(2));
     System.out.println("   " +"Destination" + " " + tripHash.get(list.get(j)).get(3));
     System.out.println("   " +"Date" + " " + tripHash.get(list.get(j)).get(4));
     System.out.println("   " +"Time" + " " + tripHash.get(list.get(j)).get(5));
     System.out.println("   " +"Price" + " " + tripHash.get(list.get(j)).get(6));
    }
  }
  }
  boolean searchStop(int count,String ID,List<String> list){
  	boolean state=false;
    if(count==5){
     if(Search(Trips("trainTripsNoStop.txt",36,5,tripsTrainNon),ID))
      updateSeats(cancelTicket(tripsBusNon,5,36,ID,list),"trainTripsNoStop.txt");
      state=true;
    }
    if(count==6){
     if(Search(Trips("trainTripsOneStop.txt",36,6,tripsTrainOne),ID))
      updateSeats(cancelTicket(tripsTrainOne,6,36,ID,list),"trainTripsOneStop.txt");
      state=true;
    }
    if(count==7){
     if(Search(Trips("trainTripsTwoStop.txt",36,7,tripsTrainTwo),ID))
      updateSeats(cancelTicket(tripsTrainTwo,7,36,ID,list),"trainTripsTwoStop.txt");
      state=true;
    }
    return state;
  }

    /**
     * @return the seatT
     */
    public int getSeatT() {
        return seatT;
    }

    /**
     * @param seatT the seatT to set
     */
    public void setSeatT(int seatT) {
        this.seatT = seatT;
    }
 }

