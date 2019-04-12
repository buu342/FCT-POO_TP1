/**
 * @author Lourenco Soares 54530
 * Implementation of Drones R US Inc.
 */

package DronesRUs;

public class DronesRUsClass implements DronesRUs {
    
    public static final int DEFAULT_SIZE = 10;
    
    private int curtime;            // Current time of the simulation
    private int counterbases;       // Number of bases registered to this service
    private int counterdrones;      // Number of drones registered to this service
    private int counterswarms;      // Number of swarms registered to this service
    private int counterflying;      // Number of swarms currently flying
    private int counterdeliveries;  // Number of deliveries registered to this service
    private Base[] bases;           // An array with all the bases registered to this service
    private Drone[] drones;         // An array with all the drones registered to this service
    private Drone[] flying;         // An array with all the drones currently flying
    private String[] deliveries;    // An array with all the deliveries registered to this service
    private Swarm[] swarms;         // An array with all the swarms registered to this service
    
    public DronesRUsClass() {
        this.curtime = 0;
        this.counterbases = 0;
        this.counterdrones = 0;
        this.counterflying = 0;
        this.counterswarms = 0;
        this.counterdeliveries = 0;
        this.drones = new Drone[DEFAULT_SIZE];
        this.bases = new Base[DEFAULT_SIZE];
        this.flying = new Drone[DEFAULT_SIZE];
        this.deliveries = new String[DEFAULT_SIZE];
        this.swarms = new Swarm[DEFAULT_SIZE];
    }
    
    @Override
    public boolean hasBases() {
        return (this.counterbases == 0);
    }
    
    @Override
    public boolean hasDrones() {
        return (this.counterdrones == 0);
    }
    
    @Override
    public boolean hasBase(String name) {
        int i = 0;
        
        // Search through the bases array and return if the base with name 'name' exists
        while (i < this.counterbases) {
            if (this.bases[i].getName().equals(name))
                return true;
            i++;
        }
        
        return false;
    }
    
    @Override
    public boolean hasDrone(String name) {
        int i = 0;
        
        // Search through the drones array and return if the drone with name 'name' exists
        while (i < this.counterdrones) {
            if (this.drones[i].getName().equals(name))
                return true;
            i++;
        }
        
        return false;
    }
    
    @Override
    public boolean hasSwarm(String name) {
        int i = 0;
        
        // Search through the drones array and return if the drone with name 'name' exists
        while (i < this.counterswarms) {
            if (this.swarms[i].getName().equals(name))
                return true;
            i++;
        }
        
        return false;
    }
    
    @Override
    public boolean hasFlights() {
        return (this.counterflying != 0);
    }
    
    @Override
    public int totalOrders() {
        int total = 0;
        for (int i=0; i<this.counterbases; i++)
            total += this.bases[i].orderCount();
        return total;
    }
    
    public int totalDeliveries() {
        return this.counterdeliveries;
    }
    
    @Override
    public boolean positionOccupied(int latitude, int longitude) {
        int i = 0;
        
        // Search through the bases array and return true/false if there is a base occupying position [latitude, longitude]
        while (i < this.counterbases) {
            if (this.bases[i].getLatitude() == latitude && this.bases[i].getLongitude() == longitude)
                return true;
            i++;
        }
        
        return false;
    }
    
    @Override
    public void registerBase(int latitude, int longitude, String name) {
        // If the array cannot fit any more bases, resize it
        if (this.counterbases == this.bases.length)
            resizeBases();
        
        // Set the first empty index in the bases array and set its value to a new base.
        this.bases[this.counterbases] = new BaseClass(latitude, longitude, name);
        this.counterbases++;
    }
    
    @Override
    public Drone registerDrone(Base base, String name, String type, int capacity, int range) {
        // If the array cannot fit any more drones, resize it
        if (this.counterdrones == this.drones.length)
            resizeDrones();
        
        // Set the first empty index in the drones array and set its value to a new drone.
        this.drones[this.counterdrones] = new DroneClass(name, type, capacity, range);
        
        // Add the drone to the base's hangar and increment the drones counter
        base.addDroneToHangar(this.drones[this.counterdrones]);
        this.counterdrones++;
        return this.drones[this.counterdrones-1];
    }
    
    @Override
    public void registerSwarm(Base base, String swarmname, Drone[] dronestoadd, int maxrange, int capacity) {
        int numdrones = dronestoadd.length;
        
        // If the array cannot fit any more swarms, resize it
        if (this.counterswarms == this.swarms.length)
            resizeSwarms();
        
        // Set disband all swarms
        for (int i=0; i<numdrones; i++) {
            // If the drone is a swarm, dismantle it and add all the old drones to the array
            if (dronestoadd[i].getType().equals("SWARM")) {
                Swarm swarmobject = getSwarmFromName(dronestoadd[i].getName());
                int swarmsize = swarmobject.getSize();
                Drone[] oldswarm = disbandSwarm(swarmobject);
                Drone[] temp = new Drone[swarmsize+dronestoadd.length-1];
                
                // Append the drones that were in the swarm to the end of the temp array
                for (int j=0; j<swarmsize; j++)
                    temp[j] = oldswarm[j];
                int lag=0;
                for (int j=swarmsize; j<swarmsize+dronestoadd.length; j++) {
                    if (j-swarmsize == i) {
                        lag++;
                        continue;
                    }
                    temp[j-lag] = dronestoadd[j-swarmsize];
                }
                dronestoadd = temp;
            }
        }
        
        // Register the swarm as one big drone
        Drone fakedrone = registerDrone(base, swarmname, "SWARM", capacity, maxrange);
        
        // Place a list of drones being utilized by the swarm inside the swarms array
        this.swarms[this.counterswarms] = new SwarmClass(fakedrone);
        
        // Add all the drones to the swarm
        for (int j=0; j<dronestoadd.length; j++) {
            this.swarms[this.counterswarms].addDrone(dronestoadd[j]);
            base.removeDroneHangar(dronestoadd[j]);
        }
        
        this.counterswarms++;
    }
    
    public Drone[] disbandSwarm(Swarm swarm) {
        Drone fakedrone = swarm.getFakeDrone();        
        Drone[] ret = swarm.disband();
        
        // Remove the fakedrone from drones array
        for (int i=0; i<this.counterdrones; i++)
            if (this.drones[i].getName() == fakedrone.getName())
                this.drones[i] = null;
        
        // Remove the fakedrone from hangar
        int index = 0;
        for (int i=0; i<this.counterbases; i++)
            if (this.bases[i].getDroneByName(fakedrone.getName()) != null)
                index = i;
        this.bases[index].removeDroneHangar(fakedrone);
        
        // Add all the drones back to the hangar
        for (int i=0; i<ret.length; i++)
            this.bases[index].addDroneToHangar(ret[i]);
        
        // Remove the swarm from the swarms array
        for (int i=0; i<this.counterswarms; i++)
            if (this.swarms[i].getName().equals(fakedrone.getName()))
                this.swarms[i] = null;
        
        this.counterdrones--;
        this.counterswarms--;
        compactDrones();
        compactSwarms();
        return ret;
    }
    
    @Override
    public void registerFlight(Drone drone, Base baseorig, Base basedest, int distance) {
        // If the array cannot fit any more bases, resize it
        if (this.counterflying == this.flying.length)
            resizeFlying();
        
        // Set some variables on the drone and place the drones in the correct bases
        drone.setDistanceTraveled(0);
        drone.setDistance(distance);
        drone.setOrigin(baseorig);
        drone.setDestination(basedest);
        baseorig.removeDroneHangar(drone);
        basedest.addDroneToFlying(drone);
        
        // Set the first empty index in the bases array and set its value to a new base.
        this.flying[this.counterflying] = drone;
        this.counterflying++;
    }
    
    @Override
    public BaseIterator bases() {
        return new BaseIteratorClass(this.counterbases, this.bases);
    }
    
    @Override
    public DroneIterator drones() {
        return new DroneIteratorClass(this.counterdrones, this.drones);
    }
    
    @Override
    public SwarmIterator swarms() {
        return new SwarmIteratorClass(this.counterswarms, this.swarms);
    }
    
    @Override
    public StringIterator deliveries() {
        return new StringIteratorClass(this.counterdeliveries, this.deliveries);
    }
    
    @Override
    public DroneIterator flights() {
        return new DroneIteratorClass(this.counterflying, this.flying);
    }
    
    @Override
    public Drone getDroneFromName(String name) {
        for (int i=0; i<this.counterdrones; i++)
            if (this.drones[i] != null && this.drones[i].getName().equals(name))
                return this.drones[i];
        return null;
    }
    
    @Override
    public Swarm getSwarmFromName(String name) {
        for (int i=0; i<this.counterswarms; i++)
            if (this.swarms[i] != null && this.swarms[i].getName().equals(name))
                return this.swarms[i];
        return null;
    }
    
    @Override
    public void advanceTicks(int numticks) {
        this.curtime += numticks;
        
        // Update the drones
        for (int i=0; i<this.counterbases; i++) {
            int removecounter = 0;
            Drone[] remove = new Drone[this.counterdrones];
            
            // Make the drones move if they're flying
            DroneIterator itdrone = this.bases[i].flyingDrones();
            while (itdrone.hasNext()) {
                Drone[] drones = new Drone[1];
                drones[0] = itdrone.next();
                int displacement = 10*numticks;
                int distance = drones[0].getDistance()-drones[0].getDistanceTraveled();
                
                // If it reached its destination, add it back to the hangar
                if (distance <= displacement) {
                    int difference = displacement - distance;
                    displacement = distance;
                    this.bases[i].addDroneToHangar(drones[0]);
                    remove[removecounter++] = drones[0];
                    
                    // If it was delivering something, mark the delivery as complete
                    if (drones[0].getOrder() != null) {
                        drones[0].getOrigin().removeOrder(drones[0].getOrder());
                        registerDelivery(drones[0].getOrder(), drones[0].getOrigin(), this.curtime-difference/10);
                        drones[0].setOrder(null);
                    }

                    // Reset the drone's journey
                    drones[0].setDistance(0);
                    drones[0].setOrigin(null);
                    drones[0].setDestination(null);
                }
                
                // If the drone is a swarm, then create a new array with all the drones in the swarm
                if (drones[0].getType() == "SWARM") {
                    Swarm swarm = getSwarmFromName(drones[0].getName());
                    Drone[] temp = new Drone[swarm.getSize()+1];
                    DroneIterator itdronesswarm = swarm.drones();
                    int j=1;
                    
                    // Add all the drones to the temporary array
                    temp[0] = drones[0];
                    while (itdronesswarm.hasNext()) {
                        temp[j] = itdronesswarm.next();
                        j++;
                    }
                    drones = temp;
                }
                
                // Set all the drones' distance and range values accordingly
                for (int j=0; j<drones.length; j++) {
                    drones[j].setDistanceTraveled(drones[j].getDistanceTraveled()+displacement);
                    drones[j].setRange(drones[j].getRange()-displacement);
                }
            }
            
            // Safely remove drones from the flying array (to prevent the iterator from pointing to null)
            for (int j=0; j<removecounter; j++) {
                this.bases[i].removeDroneFlying(remove[j]);
                removeFlight(remove[j]);
            }
            
            // Fix the drones in the service bay
            removecounter = 0;
            itdrone = this.bases[i].bayDrones();
            while (itdrone.hasNext()) {
                Drone drone = itdrone.next();
                int ticksleft = drone.getServiceTicks();
                int time = numticks;
                
                // If the drone is done fixing, move it back to the hangar
                if (ticksleft <= numticks) {
                    time = ticksleft;
                    remove[removecounter++] = drone;
                    drone.setRange(drone.getMaxRange());
                    this.bases[i].addDroneToHangar(drone);
                }
                
                drone.setServiceTicks(ticksleft-time);
            }
            
            // Safely remove drones from the bay array (to prevent the iterator from pointing to null)
            for (int j=0; j<removecounter; j++)
                this.bases[i].removeDroneBay(remove[j]);
        }
    }
    
    private void removeFlight(Drone drone) {
        for (int i=0; i<this.counterflying; i++)
            if (this.flying[i].equals(drone))
            {
                this.flying[i] = null;
                break;
            }
        this.counterflying--;
        compactFlying();
    }
    
    private void registerDelivery(Order order, Base base, int time) {
        // If the array cannot fit any more deliveries, resize it
        if (this.counterdeliveries == this.deliveries.length)
            resizeDeliveries();
        
        // Set the first empty index in the deliveries array and set its value to a new delivery.
        this.deliveries[this.counterdeliveries] = time +" "+order.getName()+" "+base.getName()+".";
        this.counterdeliveries++;
    }
    
    // Resize the bases array
    private void resizeBases() {
        // Create a temporary array with double the length
        int len = this.bases.length;
        Base tmp[] = new Base[2*len];
        
        // Fill the temporary array with the contents of the old users array
        for (int i=0; i<len; i++)
            tmp[i] = this.bases[i];
        
        // Set the new users array to the temporary array
        this.bases = tmp;
    }
    
    // Resize the drones array
    private void resizeDrones() {
        // Create a temporary array with double the length
        int len = this.drones.length;
        Drone tmp[] = new Drone[2*len];
        
        // Fill the temporary array with the contents of the old users array
        for (int i=0; i<len; i++)
            tmp[i] = this.drones[i];
        
        // Set the new users array to the temporary array
        this.drones = tmp;
    }
    
    // Resize the deliveries array
    private void resizeDeliveries() {
        // Create a temporary array with double the length
        int len = this.deliveries.length;
        String tmp[] = new String[2*len];
        
        // Fill the temporary array with the contents of the old users array
        for (int i=0; i<len; i++)
            tmp[i] = this.deliveries[i];
        
        // Set the new users array to the temporary array
        this.deliveries = tmp;
    }
    
    // Resize the swarms array
    private void resizeSwarms() {
        // Create a temporary array with double the length
        int len = this.swarms.length;
        Swarm tmp[] = new Swarm[2*len];
        
        // Fill the temporary array with the contents of the old users array
        for (int i=0; i<len; i++)
            tmp[i] = this.swarms[i];
        
        // Set the new users array to the temporary array
        this.swarms = tmp;
    }
    
    // Resize the flying array
    private void resizeFlying() {
        // Create a temporary array with double the length
        int len = this.flying.length;
        Drone tmp[] = new Drone[2*len];
        
        // Fill the temporary array with the contents of the old users array
        for (int i=0; i<len; i++)
            tmp[i] = this.flying[i];
        
        // Set the new users array to the temporary array
        this.flying = tmp;
    }
    
    // Compact the drones array
    private void compactDrones() {
        for (int i=0; i<this.drones.length; i++)
            if (this.drones[i] == null) {
                for (int j=i+1; j<this.drones.length; j++){
                    this.drones[j-1] = this.drones[j];
                }
                this.drones[this.drones.length-1] = null;
                break;
            }
        
    }
    
    // Compact the swarms array
    private void compactSwarms() {
        for (int i=0; i<this.swarms.length; i++)
            if (this.swarms[i] == null) {
                for (int j=i+1; j<this.swarms.length; j++){
                    this.swarms[j-1] = this.swarms[j];
                }
                this.swarms[this.swarms.length-1] = null;
                break;
            }
        
    }
    
    // Compact the flying array
    private void compactFlying() {
        for (int i=0; i<this.flying.length; i++)
            if (this.flying[i] == null) {
                for (int j=i+1; j<this.flying.length; j++){
                    this.flying[j-1] = this.flying[j];
                }
                this.flying[this.flying.length-1] = null;
                break;
            }
        
    }

}