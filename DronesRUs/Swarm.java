/**
 * @author Lourenco Soares 54530
 * Interface of a singular swarm
 */

package DronesRUs;

public interface Swarm {

    /**
     * Retrieves the name of this swarm.
     * @return A string with the name of the swarm.
     */
    String getName();

    /**
     * Retrieves the capacity of this swarm.
     * @return An int with the capacity of the swarm.
     */
    int getCapacity();
    
    /**
     * Retrieves the maximum range of this swarm.
     * @return An int with the maximum range of the swarm.
     */
    int getMaxRange();
    
    /**
     * Retrieves the range of this swarm.
     * @return An int with the range of the swarm.
     */
    int getRange();
    
    /**
     * Retrieves the number of drones in this swarm.
     * @return An int with the number of drones.
     */
    int getSize();
    
    /**
     * Returns the fake drone.
     * @return A pointer to the fake drone leader.
     */
    Drone getFakeDrone();
    
    /**
     * Adds the drone <code>drone</code> to the list of drones.
     */
    void addDrone(Drone drone);
    
    /**
     * Disbands the swarm
     * @return An array with all the drones that were in this swarm.
     */
    Drone[] disband();
    
    /**
     * Creates an iterator to search through the drones
     * @return an iterator for the drones array.
     */
    DroneIterator drones();
}
