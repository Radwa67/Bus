package bus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.util.List;
  class User {
ArrayList<String> bookedTrip=new ArrayList<>();
  HashMap<String,ArrayList<String>> ListOfTrips=new HashMap<>();
  HashMap<String,String> listOfUsers = new HashMap<>();
  HashMap<String, String> ListOfDrivers = new HashMap<>();
  HashMap<String, String> ListOfManagers=new HashMap<>();
  List<String> seats=new ArrayList<>();
  private Scanner x;
  private BufferedWriter w;
  Scanner s=new Scanner(System.in);
  String temp;
  String username;

  public void openFileR(HashMap<String, String> userHash,String Filename){
   try{
    x=new Scanner(new File(Filename));
    while (x.hasNext()){
     userHash.put(x.next(),x.next());
     }
    x.close();
    }
   catch(Exception e){
    System.out.println("LA2");
   }
  }
  public void openFileW(String username,String password,String Filename){
   try{
   w=new BufferedWriter(new FileWriter(Filename,true));
   w.newLine();
   w.write(username);
   w.write(" ");
   w.write(password);
   w.close();
 }
   catch(Exception e){
    System.out.println("LA2 La2");
   }
  }
 boolean loginpass(String username,String password){
 	 openFileR(listOfUsers,"Users.txt");
       if(listOfUsers.containsKey(username)&&listOfUsers.containsValue(password))
       {
       return true;
       } 
       else 
        {
       
       return false;
       }
 	 }
	public void openFileSave(HashMap<String, String> userHash,String Filename){
      	try{
      	w = new BufferedWriter(new FileWriter(Filename));
     	for(String key:userHash.keySet()){
      	w.write(key);
        w.write("  ");
      	w.write(userHash.get(key));
        w.newLine();
      	}
      	w.close();
      	}
      	catch(Exception e){
      	System.out.println(" boob");
      	}
    	}
       boolean loginDriver(String username, String password){
	openFileR(ListOfDrivers,"Drivers.txt");
       if(ListOfDrivers.containsKey(username)&&ListOfDrivers.containsValue(password))
       {
       return true;
       } 
       else 
        {
       
       return false;
       }
}
       boolean loginManager(String username, String password){
	openFileR(ListOfManagers,"Drivers.txt");
       if(ListOfManagers.containsKey(username)&&ListOfManagers.containsValue(password))
       {
       return true;
       } 
       else 
        {
       
       return false;
       }
}
	}
 class Passenger extends User{
  private Scanner x;
  private BufferedWriter w;
  public void chooseVehicle(String username,String type){
  	bookedTrip.clear();
   if(type.equals("1")){
    Limousine l=new Limousine() {};
   // bookedTrip=l.chooseTicket();
   }
   else if(type.equals("2")){
    //Bus b=new Bus();
 //   bookedTrip=b.chooseTicket(); 
   }
   else if(type.equals("3")){
  //  Train t=new Train();
 //   bookedTrip=t.chooseTicket();  
    }
   else{
    System.out.println("Invalid Entry");
   }
   addProfileTrips(username);
  	}
  void addProfileTrips(String username){
   HashMap<String,ArrayList<String>> profileTrips= new HashMap<String,ArrayList<String>>();
     profileTrips.put(bookedTrip.get(0), new ArrayList<String>());
   if(bookedTrip.size()==42&&bookedTrip!=null)
   for(int i=1;i<6;i++){
     profileTrips.get(bookedTrip.get(0)).add(bookedTrip.get(i));
   }
   else if(bookedTrip.size()==43&&bookedTrip!=null)
    for(int i=1;i<7;i++){
     profileTrips.get(bookedTrip.get(0)).add(bookedTrip.get(i));
   }
      	
   else if(bookedTrip.size()==44&&bookedTrip!=null)
    for(int i=1;i<8;i++){
     profileTrips.get(bookedTrip.get(0)).add(bookedTrip.get(i));
   }
   else if(bookedTrip.size()==66&&bookedTrip!=null)
    for(int i=1;i<6;i++){
     profileTrips.get(bookedTrip.get(0)).add(bookedTrip.get(i));
   }
   else if(bookedTrip.size()==67&&bookedTrip!=null)
    for(int i=1;i<7;i++){
     profileTrips.get(bookedTrip.get(0)).add(bookedTrip.get(i));
   }
   else if(bookedTrip.size()==68&&bookedTrip!=null)
    for(int i=1;i<8;i++){
     profileTrips.get(bookedTrip.get(0)).add(bookedTrip.get(i));
   }
   else if(bookedTrip.size()==4&&bookedTrip!=null)
    for(int i=1;i<4;i++){
     profileTrips.get(bookedTrip.get(0)).add(bookedTrip.get(i));
   }
	profileTrips.get(bookedTrip.get(0)).add(":");
	System.out.println(seats);
      	for(String m:seats){
      	 profileTrips.get(bookedTrip.get(0)).add(m);
      	}
   bookedTrip.clear();
   seats.clear();
   try{
   bookedTrip=new ArrayList<String>(profileTrips.keySet());
   w=new BufferedWriter(new FileWriter(username,true));
   for (int i=0;i<bookedTrip.size()+seats.size();i++) {
         	w.write(bookedTrip.get(i));
         	w.write(" ");
         	for(String j:profileTrips.get(bookedTrip.get(i))){
         	 w.write(j);
         	 w.write(" ");
         	}
         	w.newLine();
   }
   w.close();
   }
   catch(Exception e){
	System.out.println("Error");
   }
  }
    Scanner s=new Scanner(System.in);
 
 	boolean deletePassenger(String username,String password){
     	openFileR(listOfUsers,"Users.txt");
     	if(listOfUsers.containsKey(username)&&listOfUsers.containsValue(password)){
     	 listOfUsers.remove(username);
     	openFileSave(listOfUsers,"Users.txt");
        return true;
     	}
     	else{
            return false;
     	}
   	}
 	boolean signup(String username,String password){
 	    openFileR(listOfUsers,"Users.txt");
            if(listOfUsers.containsKey(username)){
                  return false;
                                                 }
            else{
                 openFileW(username,password,"Users.txt");
                       return true;
                }
                                                 	}
 	
  
  public HashMap<String,ArrayList<String>> listTripsOfProfile(String username){
   ListOfTrips.clear();
   String parts;
   try {
    x=new Scanner(new File(username));
    
    while(x.hasNext()){
     parts=x.nextLine();
     String[]split=parts.split("\\s");
     ListOfTrips.put(split[0],new ArrayList<String>());
     for(int i=1;i<split.length;i++){
      ListOfTrips.get(split[0]).add(split[i]); 
     }
    }
    return ListOfTrips;
   } catch (FileNotFoundException e) {
    e.printStackTrace();
   return null;
  }
 }
 void cancelTicket(String username,String ID){
   Bus b=new Bus() {};
   Train t=new Train();
   List<String> cancelledSeat=new ArrayList<String>();
 	  HashMap<String,ArrayList<String>> list;
   list=listTripsOfProfile(username);
   int count=0;
   int i=0;
   System.out.println(list.get(ID).size());
   while(i<list.get(ID).size()&&!list.get(ID).get(i).contains(",")){
   	count++;
            	i++;
   }
    i++;
   while(i<list.get(ID).size()){
    cancelledSeat.add(list.get(ID).get(i));
    i++;
   }
   if(count==5){
     if(b.searchStop(5, ID,cancelledSeat)||t.searchStop(5, ID,cancelledSeat)){
     list.remove(ID);
     updateTripsProfile(list,username);
    }
   }
   else if(count==6){
     if(b.searchStop(6, ID,cancelledSeat)||t.searchStop(6, ID,cancelledSeat)){
     list.remove(ID);
     updateTripsProfile(list,username);
    }
   }
   else if(count==7){
     if(b.searchStop(7, ID,cancelledSeat)||t.searchStop(7, ID,cancelledSeat)){
      list.remove(ID);
      updateTripsProfile(list,username);
     }
    }
   else if(count==3){
    list.remove(ID);
    updateTripsProfile(list,username);
   }
   
   else{
    System.out.println("Not found");
   }
   list.clear();
  }
   
  public void updateTripsProfile(HashMap<String,ArrayList<String>> tripList,String Filename){
   try{
    List<String>list= new ArrayList<String>(tripList.keySet());
      	w = new BufferedWriter(new FileWriter(Filename));
      	for (int i=0;i<list.size();i++){
          	w.write(list.get(i));
          	w.write(" ");
          	for(String j:tripList.get(list.get(i))){
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
 public void Action(){
   String Action;
   System.out.println("Enter Action");
   Action=s.nextLine();
   if(Action.equals("Book Trip")){
  //  chooseVehicle(username,type);
   }
   else if(Action.equals("Delete Tickets")){
    
   } 
   else if(Action.equals("Delete Account")){
    
   }
   else if(Action.equals("Cancel Tickets")){
    String ID=s.next();
    cancelTicket(username,ID);
   }
   else{
    System.out.println("Invalid Entry");
   }
  }
 }

 class Driver extends User{
     
 }
  class Manager extends User{
   private BufferedWriter w;
  public void AddTrips(){
  	System.out.println(" Enter Trip code:");
      	String code=s.nextLine();
      	System.out.println("Enter source:");
      	String source=s.nextLine();
          	System.out.println("Enter destination:");
      	String destination=s.nextLine();
         	System.out.println("Enter Date:");
      	String date=s.nextLine();
         	System.out.println("Enter time of trip:");
      	String time=s.nextLine();
         	System.out.println("Enter price of trip:");
      	String price=s.nextLine();
  	
//  	openFileWTrips(code, source, destination, date, time,price);
  	
  	}
  boolean DeleteTrips(String code){
      	
    	
  	openFileR(ListOfDrivers,"Drivers.txt");
  	if(ListOfTrips.containsKey(code))
    	  {
   	ListOfTrips.remove(code);
        return true;
    	  }
        else
            return false;
   }
  public boolean AddDriver(String username, String password){
   	
   	openFileR(ListOfDrivers,"Drivers.txt");
   	if(ListOfDrivers.containsKey(username)){
    	return false;
   	}
   	else{
    	openFileW(username,password,"Drivers.txt");
    	ListOfDrivers.put(username, password);
    	return true;
   	}
  	}
  public boolean RemoveDriver(String username,String password){
   	openFileR(ListOfDrivers,"Drivers.txt");
   	if(ListOfDrivers.containsKey(username)&&ListOfDrivers.containsValue(password))
   	{
  	ListOfDrivers.remove(username);
 	openFileSave(ListOfDrivers,"Drivers.txt");
  	return true;
   	}
   	else{
   	return false;
   	
   	}
   	
 }
 
  public void openFileWTrips(String TripCode,String source, String destination,String date,String time,String price,String filename,int j){
 		try{
 		w=new BufferedWriter(new FileWriter(filename,true));
 		w.newLine();
 		w.write(TripCode);
 		w.write(" ");
 		w.write(source);
 		w.write(" ");
 		w.write(destination);
 		w.write(" ");
 		w.write(date);
 		w.write(" ");
 		w.write(time);
 		w.write(" ");
 		w.write(price);
                w.write(" ");
 		for(int i=0;i<j;i++){
  		w.write("0");
                w.write(" ");
  		}
 		w.close();
 		}
 		catch(Exception e){
  	 	System.out.println("LA2 La2");
  		}
  }
   
 		public void openFileWTrips(String TripCode,String source,String Stop1, String destination,String date,String time,String price,String filename,int j){
  		try{
  		w=new BufferedWriter(new FileWriter(filename,true));
  		w.newLine();
  		w.write(TripCode);
  		w.write(" ");
  		w.write(source);
  		w.write(" ");
  		w.write(Stop1);
  		w.write(" ");
  		w.write(destination);
  		w.write(" ");
  		w.write(date);
  		w.write(" ");
  		w.write(time);
  		w.write(" ");
  		w.write(price);
                w.write(" ");
  		for(int i=0;i<j;i++){
   		w.write("0");
                w.write(" ");
   		}
  		w.close();
  		}
  		catch(Exception e){
   	 	System.out.println("LA2 La2");
   		}
  	  }
 		public void openFileWTripsl(String TripCode,String source,String destination,String price,String filename){
  		try{
  		w=new BufferedWriter(new FileWriter(filename,true));
  		w.newLine();
  		w.write(TripCode);
  		w.write(" ");
  		w.write(source);
  		w.write(" ");
  		w.write(destination);
  		w.write(" ");
  		w.write(price);
                w.write(" ");
  		w.close();
  		}
  		catch(Exception e){
   	 	System.out.println("LA2 La2");
   		}
  	  }
  		public void openFileWTrips(String TripCode,String source,String Stop1,String Stop2, String destination,String date,String time,String price,String filename,int j){
   		try{
   		w=new BufferedWriter(new FileWriter(filename,true));
   		w.newLine();
   		w.write(TripCode);
   		w.write(" ");
   		w.write(source);
   		w.write(" ");
   		w.write(Stop1);
   		w.write(" ");
   		w.write(Stop2);
   		w.write(" ");
   		w.write(destination);
   		w.write(" ");
   		w.write(date);
   		w.write(" ");
   		w.write(time);
   		w.write(" ");
   		w.write(price);
                w.write(" ");
   		for(int i=0;i<j;i++){
   		w.write("0");
                w.write(" ");
   		}
   		w.close();
   	  }
 		catch(Exception e){
 	 	System.out.println("LA2 La2");
 		}
  } 

  }
