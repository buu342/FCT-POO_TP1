/**
 * @author Lourenco Soares 54530
 * Implementation of a singular swarm
 */

package DronesRUs;

public class SwarmClass implements Swarm {

    public static final int DEFAULT_SIZE = 10;
    
    private Drone fakedrone;    // The fake drone that this swarm object points to
    private String name;        // The name of this swarm
    private int capacity;       // The carrying capacity of this swarm
    private int range;          // The Range this swarm has left until next service
    private int maxrange;       // The maximum range this swarm can get
    private int counterdrones;  // The number of drones that form this swarm
    private Drone[] drones;     // An array with all the drones that form this swarm
    
    public SwarmClass(Drone fakedrone) {
        this.fakedrone = fakedrone;
        this.name = this.fakedrone.getName();
        this.capacity = this.fakedrone.getCapacity();
        this.range = this.fakedrone.getRange();
        this.maxrange = this.range;
        this.counterdrones = 0;
        this.drones = new Drone[DEFAULT_SIZE];
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public int getCapacity() {
        return this.capacity;
    }
    
    @Override
    public int getMaxRange() {
        return this.maxrange;
    }
    
    @Override
    public int getRange() {
        return this.range;
    }
    
    @Override
    public int getSize() {
        return this.counterdrones;
    }
    
    @Override
    public Drone getFakeDrone() {
        return this.fakedrone;
    }
    
    public void addDrone(Drone drone) {
        // If the array cannot fit any more drones, resize it
        if (this.counterdrones == this.drones.length)
            resizeDrones();
        
        // Add the drone to the swarm
        this.drones[this.counterdrones] = drone;
        this.counterdrones++;
    }
    
    @Override
    public Drone[] disband() {
        int len = this.counterdrones;
        Drone[] olddrones = new Drone[len];
        for (int i=0; i<len; i++) {
            olddrones[i] = this.drones[i];
            this.drones[i] = null; // Set to null for the Garbage Collector
        }
        this.fakedrone = null;
        return olddrones;
    }
    
    @Override
    public DroneIterator drones() {
        return new DroneIteratorClass(this.counterdrones, this.drones);
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
}
