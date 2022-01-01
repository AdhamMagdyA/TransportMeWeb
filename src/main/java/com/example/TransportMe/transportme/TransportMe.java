// is to be removed

/*package transportme;
import java.util.ArrayList;
import java.util.Scanner;

import rides.*;
import users_pack.*;

public class TransportMe {

    public static User loggedIn = null;
    public static ArrayList<Driver> pendingRegistrations=new ArrayList<Driver>();
    public static ArrayList<User> suspendedUsers=new ArrayList<User>(); // if  the admin suspended any user , this user will add to the list and cannot be able to login
    public static ArrayList<User> registeredUsers=new ArrayList<User>() ;
    public static ArrayList<Driver> drivers =new ArrayList<Driver>();
    public static ArrayList<Client> clients=new ArrayList<Client>();
    public static ArrayList<Ride> rides=new ArrayList<Ride>() ;
    private static Admin systemAdmin = new Admin("admin","123456789","password","email");
    public static void storeUser(User u){
        registeredUsers.add(u);
        if(u instanceof Driver) {
            drivers.add((Driver) u);
            registeredUsers.add(u);
        }
        if(u instanceof Client) {
            clients.add((Client) u);
            registeredUsers.add(u);
        }

        System.out.println("User is successfully registered");
    }
    public static boolean searchDriver(String username){
        for(Driver driver : drivers){
            if(driver.getUsername().equals(username)) return true;
        }
        return false;
    }
    static void displayMenu() {
        System.out.println("1-REGISTER .");
        System.out.println("2-SIGN IN.");
        System.out.println("3-EXIT .");
    }
    public static void displayClientMenu(){
        System.out.println("1-Request Ride.");
        System.out.println("2-View Offers.");
        System.out.println("3-Add Rating.");
        System.out.println("4-Back.");
        choice = input.nextInt();
        while (choice!=4){
            if (choice==1){
                System.out.println("Enter source and destination area names.");
                String source;
                String destination;
                source = input.next();
                destination = input.next();
                ((Client) loggedIn).requestRide(source,destination,1);
            }
            if (choice==2){
                ((Client) loggedIn).viewOffers();
            }
            if (choice==3){
                System.out.println("Enter driver's name .");
                String name;
                int rating;
                name = input.next();
                if (searchDriver(name)){
                    for (Driver driver: TransportMe.drivers){
                        if (driver.getUsername().equals(name)){
                            System.out.println("Enter your rating from 1 to 5 .");
                            rating = input.nextInt();
                            if (rating >0 && rating <5){
                                ((Client) loggedIn).addRating(driver,rating);
                                System.out.println("Rating added successfully .");
                            }
                            else {
                                System.out.println("wrong number, please try again.");
                            }

                        }
                    }
                }
                else {
                    System.out.println("There is no such driver in the system");

                }
            }
            displayClientMenu();
        }
    }
    public static void displayDriverMenu(){
        System.out.println("1-List Rides in Favourite Area.");
        System.out.println("2-Display Rating List.");
        System.out.println("3-add favourite area.");
        System.out.println("4-Back.");
        choice = input.nextInt();
        while (choice!=4){
            if (choice==1) {
                System.out.println("Enter source area name.");
                String source;
                source = input.next();
                ((Driver) loggedIn).listRides(source);
                System.out.println("=================================");
                System.out.println("1-Suggest Price for ride");
                System.out.println("2- Back");
                choice = input.nextInt();
                if (choice==1){
                    double price;
                    int rideID;
                    rideID = input.nextInt();
                    price = input.nextDouble();
                    System.out.println("Enter price.");
                    ((Driver) loggedIn).suggestPrice(price,rideID);
                    displayDriverMenu();
                }
                if (choice==2){
                    displayDriverMenu();
                }

            }
            if (choice==2) {
                ((Driver) loggedIn).showRatingList();
                displayDriverMenu();
            }
            if (choice==3) {
                System.out.println("Enter favourite area name.");
                String area = input.next();
                ((Driver) loggedIn).setFavAreas(area);
                displayDriverMenu();

            }
        }

    }
    public static void displayAdminMenu(){
        System.out.println("1-List Pending Registration for Drivers.");
        System.out.println("2-Suspend User .");
        System.out.println("3-UnSuspend User.");
        System.out.println("4-Back.");
        choice = input.nextInt();
        while (choice!=4){
            if (choice==1){
                systemAdmin.listPendingRegistrations();
                System.out.println("1-Choose Driver To Approve or Reject.");
                System.out.println("2-Back.");
                choice=input.nextInt();
                if (choice==1){
                    System.out.println("Enter Driver's user name");
                    String name = input.next();
                    for (Driver driver: pendingRegistrations){
                        if (driver.getUsername().equals(name)){
                            System.out.println("1-Approve Driver.");
                            System.out.println("2-Reject Driver.");
                            choice=input.nextInt();
                            if (choice==1){
                                systemAdmin.acceptRegistration(name);
                                break;
                            }
                            // this code is not working for now
                            if (choice==2){
                                //systemAdmin.rejectRegistration(name);
                                break;
                            }
                        }
                    }
                }
                if (choice==2){
                    displayAdminMenu();
                }

            }
            if (choice==2){
                System.out.println("Enter User' name");
                String name = input.next();
                for (User user :registeredUsers){
                    if (user.getUsername().equals(name)){
                        systemAdmin.suspendUser(user.getUsername());
                        System.out.println("suspended successfully");
                    }
                    else {
                        System.out.println("user not found");

                    }
                    displayAdminMenu();
                }
            }
            if (choice==3){
                System.out.println("Enter User' name");
                String name = input.next();
                for (User user :suspendedUsers){
                    if (user.getUsername().equals(name)){
                        systemAdmin.unSuspendUser(user.getUsername());
                    }
                    else {
                        System.out.println("user not found");
                    }
                }
                displayAdminMenu();
            }
            if (choice==4){
                displayAdminMenu();
            }
        }
    }
    public static boolean searchSuspended(String userName){
        for (User user: suspendedUsers){
            if (user.getUsername().equals(userName)){
                return true;
            }
        }
        return false;
    }
    public static boolean login(String userName, String password){
        boolean status=false;
        if(systemAdmin.getUsername().equals(userName)&&systemAdmin.getPassword().equals(password)){
            displayAdminMenu();
            System.out.println("ADMIN LOGGED IN SUCCESSFULLY");
            status=true;
        }
        else if (searchSuspended(userName)){
            System.out.println("Sorry, You have been suspended by Admin");
        }
        else if (searchRegisteredUser(userName)){
            for(User u : registeredUsers){
                if(u.getUsername().equals(userName) && u.getPassword().equals(password) ){
                    loggedIn = u;
                    System.out.println("logged in successfully");
                    status=true;
                }
            }
        }
        else{
            System.out.println("user not found");
            status=false;
        }
        return status;
    }
    public static boolean searchRegisteredUser(String username){
        for(User u : registeredUsers){
            if(u.getUsername().equals(username))
                return true;
        }
        return false;
    }
    static void register(int ch){
        if (ch==1){
            System.out.println("ENTER YOUR INFORMATION AS FOLLOW: USERNAME, MOBILE NUMBER, PASSWORD, EMAIL");
            String userName = input.next();
            String mobileNumber = input.next();
            String password = input.next();
            String email = input.next();
            Client client = new Client(userName,mobileNumber,password,email);
            storeUser(client);
        }
        // this code is not working for now
        if (ch==2){
            System.out.println("ENTER YOUR INFORMATION AS FOLLOW: USERNAME, MOBILE NUMBER, PASSWORD, EMAIL, NATIONAL ID, DRIVING LICENCE");
            String userName = input.next();
            String mobileNumber = input.next();
            String password = input.next();
            String email = input.next();
            String nationalID = input.next();
            String drivingLicence = input.next();
            Driver driver = new Driver(userName,mobileNumber,password,email,nationalID,drivingLicence);
            //Admin.addPendingRegistrations(driver);
        }
    }
    static Scanner input = new Scanner(System.in);
    static int choice;
    static int ip;

    public static void main(String[] args) {
        System.out.println("=======WELCOME TO TRANSPORT ME , PLEASE ENTER CHOICE NUMBER=======");
        displayMenu();
        do {
            choice = input.nextInt();
            //REGISTRATION
            if (choice == 1) {
                System.out.println("1-CLIENT");
                System.out.println("2-DRIVER.");
                System.out.println("3-BACK.");
                ip = input.nextInt();
                while (ip != 3) {
                    register(ip);
                    break;
                }
            }
            // signIn
            if (choice == 2) {
                System.out.println("ENTER YOUR USER NAME AND PASSWORD");
                String userName = input.next();
                String password = input.next();
                boolean suspended = false;
                boolean status = false;
                for (User user : suspendedUsers){
                    if(user.getUsername().equals(userName)){
                        suspended = true;
                    }
                }
                if(!suspended) {status =login(userName,password);}else{
                    System.out.print("user is suspended");
                    status = false;
                }
                if (status){
                    if (loggedIn instanceof Client){
                        displayClientMenu();

                    }
                    if (loggedIn instanceof Driver){
                        displayDriverMenu();

                    }
                    if (loggedIn instanceof Admin){
                        displayAdminMenu();

                    }

                }

            }
            displayMenu();
        }
        while (choice != 3);
    }
    
}
*/