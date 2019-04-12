/**
 * @author Lourenco Soares 54530
 * Project 1 for POO
 */

import java.util.Scanner;
import DronesRUs.*;

public class Main {
    
    // Program constants
    public static final String HELP             = "HELP";
    public static final String ADDBASE          = "BASE";
    public static final String LISTBASES        = "LISTBASES";
    public static final String ADDDRONE         = "DRONE";
    public static final String LISTDRONES       = "LISTDRONES";
    public static final String ADDORDER         = "ADDORDER";
    public static final String LISTORDERS       = "ORDERS";
    public static final String ALLORDERS        = "ALLORDERS";
    public static final String ADDSWARM         = "SWARM";
    public static final String SWARMCOMPONENTS  = "SWARMCOMPONENTS";
    public static final String DISBAND          = "DISBAND";
    public static final String FLYTOBASE        = "FLYTOBASE";
    public static final String INTRANSIT        = "INTRANSIT";
    public static final String SERVICE          = "SERVICE";
    public static final String DELIVER          = "DELIVER";
    public static final String DELIVERED        = "DELIVERED";
    public static final String TICTAC           = "TICTAC";
    public static final String EXIT             = "EXIT";
    
    // Main
    public static void main(String[] args) {
        
        // Initialize the program
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        DronesRUs dRUs = new DronesRUsClass();
        String command = getCommand(in);
        
        // Get commands until the user writes EXIT
        while (!command.equals(EXIT)) {
            // Decide what to do depending on the command
            switch (command) {
                case HELP:
                    printHelp();
                    break;
                case ADDBASE:
                    registerBase(dRUs, in);
                    break;
                case LISTBASES:
                    listBases(dRUs, in);
                    break;
                case ADDDRONE:
                    registerDrone(dRUs, in);
                    break;
                case LISTDRONES:
                    listDrones(dRUs, in);
                    break;
                case ADDORDER:
                    registerOrder(dRUs, in);
                    break;                   
                case LISTORDERS:
                    listOrders(dRUs, in);
                    break;
                case ALLORDERS:
                    allOrders(dRUs, in);
                    break;
                case ADDSWARM:
                    registerSwarm(dRUs, in);
                    break;
                case SWARMCOMPONENTS:
                    swarmComponents(dRUs, in);
                    break;
                case DISBAND:
                    disband(dRUs, in);
                    break;
                case FLYTOBASE:
                    flyToBase(dRUs, in);
                    break;
                case INTRANSIT:
                    inTransit(dRUs, in);
                    break;
                case SERVICE:
                    service(dRUs, in);
                    break;
                case DELIVER:
                    deliver(dRUs, in);
                    break;
                case DELIVERED:
                    deliveries(dRUs, in);
                    break;
                case TICTAC:
                    advanceTicks(dRUs, in);
                    break;
                default:
                    System.out.print("Unknown command. Type help to see available commands.\n");
                    break;
            }

            // Get another command
            System.out.print("> ");
            command = getCommand(in);
        }
        
        // Say goodbye and terminate the program
        System.out.print("Bye!\n");
        in.close();
    }

    // Standardize the input by forcing everything to uppercase
    private static String getCommand(Scanner in) {
        String input;
        
        input = in.nextLine().toUpperCase();
        return input;
    }

    private static void printHelp() {
        System.out.print( 
            "base - registers a new distribution base\n" + 
            "listBases - lists existing distribution bases\n" + 
            "drone - registers a new drone in a starting base\n" + 
            "service - service all grounded drones requiring service in a base\n" + 
            "swarm - creates a new swarm from free drones in a base\n" + 
            "swarmComponents - lists the drones within a swarm\n" + 
            "disband - disbands the whole swarm\n" + 
            "listDrones - lists all existing drones (and swarms)\n" + 
            "flyToBase - fly a drone (or swarm) to a base\n" + 
            "addOrder - add a new order to a base\n" + 
            "orders - lists all pending orders from a base\n" + 
            "allOrders - lists all pending orders from all bases\n" + 
            "deliver - deliver a package to a customer\n" + 
            "delivered - lists all delivered orders\n" + 
            "inTransit - lists all drones (and swarms) currently flying\n" + 
            "ticTac - advances the simulation clock n timestamps\n" + 
            "help - shows the available commands\n" + 
            "exit - terminates the execution of the program\n"
        );
    }
    
    private static void registerBase(DronesRUs dRUs, Scanner in) {
        int latitude = in.nextInt();
        int longitude = in.nextInt();
        String name = in.nextLine();
        name = name.substring(1);
        
        if (dRUs.hasBase(name)) {
            System.out.print("Base already exists!\n");
            return;
        }

        if (dRUs.positionOccupied(latitude, longitude)) {
            System.out.print("There is already another base at ["+latitude+","+longitude+"]!\n");
            return;
        }
        
        dRUs.registerBase(latitude, longitude, name);
        System.out.print("Base "+name+" created at ["+latitude+","+longitude+"].\n");
    }
    
    private static void listBases(DronesRUs dRUs, Scanner in) {
        
        BaseIterator itbase = dRUs.bases();

        // Check that there are registered bases
        if (dRUs.hasBases()) {
            System.out.print("There are no bases!\n");
            return;
        }

        // Go through all the bases and print their information
        while (itbase.hasNext()) {
            Base base = itbase.next();
            int hangarcount = base.hangarCount();
            int baycount = base.bayCount();
            System.out.print(base.getName()+" ["+base.getLatitude()+","+base.getLongitude()+"]\n");
            
            // Print the drones in the hangar
            if (hangarcount != 0) {
                int dronecount = 0;
                System.out.print("Hangar: [");
                DroneIterator itdrone = base.hangarDrones();
                while (itdrone.hasNext()) {
                    dronecount++;
                    Drone drone = itdrone.next();
                    System.out.print(drone.getName()+" "+drone.getCapacity()+" "+drone.getRange());
                    if (dronecount < hangarcount)
                        System.out.print(", ");
                }
                System.out.print("]\n");
            }
            else
                System.out.print("The hangar is empty!\n");
                
            // Print the drones in the service bay
            if (baycount != 0) {
                int dronecount = 0;
                System.out.print("Service bay: [");
                DroneIterator itdrone = base.bayDrones();
                while (itdrone.hasNext()) {
                    dronecount++;
                    Drone drone = itdrone.next();
                    System.out.print(drone.getName()+" "+drone.getCapacity()+" "+drone.getRange());
                    if (dronecount < baycount)
                        System.out.print(", ");
                }
                System.out.print("]\n");
            }
            else
                System.out.print("The service bay is empty!\n");
        }
    }
    
    private static void registerDrone(DronesRUs dRUs, Scanner in) {
        String dronename = in.nextLine();
        String basename = in.nextLine();
        String type = in.next();
        int capacity = in.nextInt();
        int range = in.nextInt();
        in.nextLine();
            
        BaseIterator itbase = dRUs.bases();
        Base base = null;
        
        // Check if the range is larger than 10
        if (range < 10) {
            System.out.print("Invalid range for a new drone!\n");
            return;
        }   
        
        // Check if the capacity is larger than 0
        if (!(capacity > 0)) {
            System.out.print("Capacity has to be a positive integer!\n");
            return;
        }
        
        // Check if the base exists
        if (!dRUs.hasBase(basename)) {
            System.out.print("Base "+basename+" does not exist!\n");
            return;
        }
        
        // Check if the drone exists
        if (dRUs.hasDrone(dronename)) {
            System.out.print("Drone "+dronename+" already exists!\n");
            return;
        }
        
        // Check if the type is correct
        type = type.toUpperCase();
        if (!(type.equals("HERMIT") || type.equals("SOCIABLE"))) {
            System.out.print("Invalid drone type!\n");
            return;
        }

        // Otherwise, if there were no errors, start by finding the base
        base = itbase.next();
        while (!base.getName().equals(basename))
            base = itbase.next();
        
        // Register the drone to that base
        dRUs.registerDrone(base, dronename, type, capacity, range);
        System.out.print("Drone "+dronename+" created.\n");
    }
    
    private static void listDrones(DronesRUs dRUs, Scanner in) {
        
        DroneIterator itdrone = dRUs.drones();

        // Check that there are registered drones
        if (dRUs.hasDrones()) {
            System.out.print("There are no drones!\n");
            return;
        }

        // Go through all the drones and print their information
        while (itdrone.hasNext()) {
            Drone drone = itdrone.next();
            if (drone != null && !drone.getType().equals("SWARM"))
                System.out.print(drone.getName()+"\n");
        }
    }
    
    private static void registerOrder(DronesRUs dRUs, Scanner in) {
        String basename = in.nextLine();
        String ordername = in.nextLine();
        int dimension = in.nextInt();
        int latitude = in.nextInt();
        int longitude = in.nextInt();
        in.nextLine();
        
        BaseIterator itbase = dRUs.bases();
        Base base = null;
        
        // Check that there are registered bases
        if (dRUs.hasBases()) {
            System.out.print("There are no bases!\n");
            return;
        }
        
        // Check if the base exists
        if (!dRUs.hasBase(basename)) {
            System.out.print("Base "+basename+" does not exist!\n");
            return;
        }
        
        // Find the base
        base = itbase.next();
        while (!base.getName().equals(basename))
            base = itbase.next();
        
        // Check if the order has already been registered
        if (base.hasOrder(ordername)) {
            System.out.print("Order "+basename+" already registered!\n");
            return;
        }
        
        // Check if the order has the correct dimensions
        if (!(dimension > 0)) {
            System.out.print("Order dimension must be a positive integer!\\n");
            return;
        }
        
        // Otherwise, add the order
        base.addOrder(ordername, dimension, latitude, longitude);
        System.out.print("Order queued for delivery.\n");
    }
    
    private static void listOrders(DronesRUs dRUs, Scanner in) {
        String basename = in.nextLine();
        BaseIterator itbase = dRUs.bases();
        Base base = null;
        OrderIterator itorder = null;
        
        // Check that there are registered bases
        if (dRUs.hasBases()) {
            System.out.print("There are no bases!\n");
            return;
        }
        
        // Check if the base exists
        if (!dRUs.hasBase(basename)) {
            System.out.print("Base "+basename+" does not exist!\n");
            return;
        }
        
        // Find the base
        base = itbase.next();
        while (!base.getName().equals(basename))
            base = itbase.next();
        
        // Check if the order has already been registered
        if (base.orderCount() == 0) {
            System.out.print("There are no pending orders!\n");
            return;
        }
        
        // Print the orders
        itorder = base.orders();
        while (itorder.hasNext()) {
            Order order = itorder.next();
            if (order != null)
                System.out.print(order.getName()+"; "+order.getDimension()+"; ["+order.getLatitude()+","+order.getLongitude()+"]\n");
        }
    }
    
    private static void allOrders(DronesRUs dRUs, Scanner in) {
        BaseIterator itbase = dRUs.bases();
        
        // Check that there are registered bases
        if (dRUs.hasBases()) {
            System.out.print("There are no bases!\n");
            return;
        }
        
        // Check if there are registered orders
        if (dRUs.totalOrders() == 0) {
            System.out.print("There are no pending orders!\n");
            return;
        }
        
        // Find the base
        while (itbase.hasNext())
        {
            Base base = itbase.next();
            
            // Print the orders in this base
            if (base.orderCount() != 0) {
                System.out.print("Orders in "+base.getName()+":\n");
                OrderIterator itorder = base.orders();
                
                while (itorder.hasNext()) {
                    Order order = itorder.next();
                    if (order != null)
                        System.out.print(order.getName()+"; "+order.getDimension()+"; ["+order.getLatitude()+","+order.getLongitude()+"]\n");
                }   
            }
            else
                System.out.print("There are no pending orders in "+base.getName()+".\n");
        }
    }
    
    private static void registerSwarm(DronesRUs dRUs, Scanner in) {
        String basename = in.nextLine();
        String swarmname = in.nextLine();
        int numdrones = in.nextInt(); in.nextLine();
        String[] dronenames = new String[numdrones];
        Drone[] drones = new Drone[numdrones];
        
        int maxrange = -1;
        int capacity = 0;
        for (int i=0; i<numdrones; i++)
            dronenames[i] = in.nextLine();
        
        BaseIterator itbase = dRUs.bases();
        Base base = null;
     
        // Check if the base exists
        if (!dRUs.hasBase(basename)) {
            System.out.print("Base "+basename+" does not exist!\n");
            return;
        }
        
        // Check if we have enough drones to make a swarm
        if (numdrones < 2) {
            System.out.print("Swarm must have at least two drones!\n");
            return;
        }
        
        // Find the base
        base = itbase.next();
        while (!base.getName().equals(basename))
            base = itbase.next();
        
        // Check for repeats
        for (int j=0; j<numdrones; j++)
            for (int i=0; i<numdrones; i++)
                if (i != j && dronenames[i].equals(dronenames[j])) {
                    System.out.print("Cannot add drone "+dronenames[i]+" twice!\n");
                    return;
                }
        
        // Check that the drone is of the correct type and add them to our temporary list of drones
        for (int i=0; i<numdrones; i++) {
            drones[i] = base.getDroneByName(dronenames[i]);
            if (drones[i] != null && drones[i].getType().equals("HERMIT")) {
                System.out.print("Cannot add hermit drone "+dronenames[i]+"!\n");
                return;
            }
        }
        
        // Check that the drone is in the hangar
        for (int i=0; i<numdrones; i++)
            if (drones[i] == null) {
                System.out.print("Drone "+dronenames[i]+" is not available in this base!\n");
                return;
            }
        
        // Check that the swarm hasn't already been registered
        if (dRUs.hasDrone(swarmname)) {
            System.out.print("Swarm "+swarmname+" already exists!\n");
            return;
        }
    
        // Calculate the maximum capacity and range
        for (int i=0; i<numdrones; i++) {
            capacity += drones[i].getCapacity();
            if (maxrange == -1)
                maxrange = drones[i].getRange();
            else if (maxrange > drones[i].getRange())
                maxrange = drones[i].getRange();
        }
        
        // Register the swarm to that base
        dRUs.registerSwarm(base, swarmname, drones, maxrange, capacity);
        System.out.print(swarmname+" created.\n");
    }
    
    private static void swarmComponents(DronesRUs dRUs, Scanner in) {
        String swarmname = in.nextLine();
        Swarm swarm;
        SwarmIterator itswarm = dRUs.swarms();
        DroneIterator itsdrone;
        
        // Find the drone if it exists
        if (!dRUs.hasSwarm(swarmname)) {
            System.out.print(swarmname+" is not a swarm!\n");
            return;
        }
        
        // Find the swarm
        swarm = itswarm.next();
        while (!swarm.getName().equals(swarmname))
            swarm = itswarm.next();
        
        // Iterate through each drone in the swarm and print their name
        itsdrone = swarm.drones();
        while (itsdrone.hasNext())     
            System.out.print(itsdrone.next().getName()+"\n");
        
        // Print the final information
        System.out.print(swarm.getCapacity()+" "+swarm.getRange()+"\n");
    }

    private static void disband(DronesRUs dRUs, Scanner in) {
        String basename = in.nextLine();
        String swarmname = in.nextLine();
        Base base;
        BaseIterator itbase = dRUs.bases();
        DroneIterator itdrone;
        
        // Check if the base exists
        if (!dRUs.hasBase(basename)) {
            System.out.print("Base "+basename+" does not exist!\n");
            return;
        }
        
        // Find the base
        base = itbase.next();
        while (!base.getName().equals(basename))
            base = itbase.next();
        
        // Check that the swarm exists in the base
        itdrone = base.hangarDrones();
        while (itdrone.hasNext()) {
            Drone drone = itdrone.next();
            if (drone.getName().equals(swarmname) && dRUs.hasSwarm(swarmname)) {
                Swarm swarm = dRUs.getSwarmFromName(swarmname);
                dRUs.disbandSwarm(swarm);
                System.out.print(swarmname+" disbanded.\n");
                return;
            }
        }
                
        System.out.print(swarmname+" is not at "+basename+"!\n");
    }
    
    private static void flyToBase(DronesRUs dRUs, Scanner in) {
        String basenameorig = in.nextLine();
        String dronename = in.nextLine();
        String basenamedest = in.nextLine();
        int distance;
        Base baseorig;
        Base basedest;
        BaseIterator itbase = dRUs.bases();
        Drone drone;

        // Check if the origin base exists
        if (!dRUs.hasBase(basenameorig)) {
            System.out.print("Source base "+basenameorig+" does not exist!\n");
            return;
        }
        
        // Check if the destination base exists
        if (!dRUs.hasBase(basenamedest)) {
            System.out.print("Target base "+basenamedest+" does not exist!\n");
            return;
        }
        
        // Find the origin base
        baseorig = itbase.next();
        while (!baseorig.getName().equals(basenameorig))
            baseorig = itbase.next();
        
        // Find the destination base
        itbase.rewind();
        basedest = itbase.next();
        while (!basedest.getName().equals(basenamedest))
            basedest = itbase.next();
        
        // Check if the drone exists in the origin base
        drone = baseorig.getDroneByName(dronename);
        if (drone == null) {
            System.out.print(dronename+" is not at "+basenameorig+"!\n");
            return;
        }
        
        // Calculate the distance and check that the drone can fly to it
        distance = (int)Math.ceil(calcDistance(baseorig.getLatitude(), baseorig.getLongitude(), basedest.getLatitude(), basedest.getLongitude()));
        if (drone.getRange() < distance)
        {
            System.out.print("Drone "+dronename+" cannot reach "+basenamedest+"!\n");
            return;
        }
        
        // Remove the drone from the origin base and set it to go to the destination base
        dRUs.registerFlight(drone, baseorig, basedest, distance);
        System.out.print(dronename+" flying from "+basenameorig+" to "+basenamedest+".\n");
    }
    
    private static void inTransit(DronesRUs dRUs, Scanner in) {
        DroneIterator itflights = dRUs.flights();
        
        // Check that there are flights
        if (!dRUs.hasFlights()) {
            System.out.print("No drones are flying!\n");
            return;
        }
        
        // Go through all the flights
        while (itflights.hasNext()) {
            Drone drone = itflights.next();
            Base orig = drone.getOrigin();
            Base dest = drone.getDestination();
            String goal;
            if (orig.equals(dest))
                goal = "delivery";
            else
                goal = "relocation";
            
            System.out.print(drone.getName()+" "+orig.getName()+" "+dest.getName()+" "+drone.getDistanceTraveled()+" "+drone.getDistance()+" "+goal+"!\n");
        }
            
    }
    
    private static void deliver(DronesRUs dRUs, Scanner in) {
        String basename = in.nextLine();
        String dronename = in.nextLine();
        String ordername = in.nextLine();
        int distance;
        Base base;
        BaseIterator itbase = dRUs.bases();
        Order order;
        Drone drone;
        OrderIterator itorders;
        
        // Check if the base exists
        if (!dRUs.hasBase(basename)) {
            System.out.print("Base "+basename+" does not exist!\n");
            return;
        }
        
        // Find the base
        base = itbase.next();
        while (!base.getName().equals(basename))
            base = itbase.next();
        
        // Check if the drone exists in the base's hangar
        drone = base.getDroneByName(dronename);
        if (drone == null) {
            System.out.print(dronename+" is not at "+basename+"!\n");
            return; 
        }
        
        // Check if the order exists
        if (!base.hasOrder(ordername)) {
            System.out.print(ordername+" is not pending!\n");
            return; 
        }
        
        // Find the order
        itorders = base.orders();
        order = itorders.next();
        while (!order.getName().equals(ordername))
            order = itorders.next();
        
        // Check that the drone can deliver the order and come back
        distance = (int)Math.ceil(calcDistance(base.getLatitude(), base.getLongitude(), order.getLatitude(), order.getLongitude())*2);
        if (drone.getRange() < distance) {
            System.out.print(ordername+" is too far for "+dronename+"!\n");
            return;
        }
         
        // Check that the order is light enough for the drone
        if (drone.getCapacity() < order.getDimension()) {
            System.out.print(ordername+" is too heavy for "+dronename+"!\n");
            return;
        }
        
        // Otherwise, let the drone deliver the order
        drone.setOrder(order);
        base.removeOrder(order);
        dRUs.registerFlight(drone, base, base, distance);
        System.out.print(dronename+" will deliver "+ordername+".\n");
    }
    
    private static void deliveries(DronesRUs dRUs, Scanner in) {
        StringIterator itstr = dRUs.deliveries();
        
        // Check if deliveries were made
        if (dRUs.totalDeliveries() == 0) {
            System.out.print("No orders delivered so far!\n");
            return;
        }
        
        // Print the deliveries
        while (itstr.hasNext())
            System.out.print(itstr.next()+"\n");
    }
    
    private static void advanceTicks(DronesRUs dRUs, Scanner in) {
        int numticks = in.nextInt();
        in.nextLine();
        dRUs.advanceTicks(numticks);
    }
    
    private static void service(DronesRUs dRUs, Scanner in) {
        Base base;
        BaseIterator itbase = dRUs.bases();
        Drone[] send;
        int countersend = 0;
        DroneIterator ithangar;
        String basename = in.nextLine();
        int minthreshold = in.nextInt();
        in.nextLine();
        
        // Check if the base exists
        if (!dRUs.hasBase(basename)) {
            System.out.print("Base "+basename+" does not exist!\n");
            return;
        }
        
        // Find the base
        base = itbase.next();
        while (!base.getName().equals(basename))
            base = itbase.next();
        
        // Send the drones to the service area
        ithangar = base.hangarDrones();
        send = new Drone[base.hangarCount()];
        while (ithangar.hasNext()) {
            Drone drone = ithangar.next();
            if (drone.getRange() <= minthreshold) {
                base.addDroneToBay(drone);
                drone.setServiceTicks(3);
                send[countersend] = drone;
                countersend++;
            }
        }
        
        // Check for errors
        if (countersend == 0) {
            System.out.print("No drones were sent to the service station!\n");
            return;
        }
        
        // Safely remove drones from the hangar (to avoid the iterator pointing to null)
        for (int i=0; i<countersend; i++) {
            System.out.print(send[i].getName()+" moved to service bay.\n");
            base.removeDroneHangar(send[i]);
        }
    }
    
    private static double calcDistance(int startx, int starty, int endx, int endy) {
        return Math.sqrt(Math.pow(startx-endx, 2)+Math.pow(starty-endy, 2));
    }
}