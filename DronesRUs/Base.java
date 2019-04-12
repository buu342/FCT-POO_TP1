/**
 * @author Lourenco Soares 54530
 * Interface of a singular base
 */

package DronesRUs;

public interface Base {

    /**
     * Retrieves the name of this drone base.
     * @return A string with the name of the base.
     */
    String getName();

    /**
     * Retrieves the latitude of this drone base.
     * @return An int with the latitude of the base.
     */
    int getLatitude();
    
    /**
     * Retrieves the longitude of this drone base.
     * @return An int with the longitude of the base.
     */
    int getLongitude();
    
    /**
     * Gets the number of drones in the hangar.
     * @return An int with the number of drones in the hangar.
     */
    int hangarCount();
    
    /**
     * Gets the number of drones in the service bay.
     * @return An int with the number of drones in the service bay.
     */
    int bayCount();
    
    /**
     * Gets the number of drones currently flying.
     * @return An int with the number of drones currently flying.
     */
    int flyingCount();
    
    /**
     * Gets the number of orders.
     * @return An int with the number of orders.
     */
    int orderCount();
    
    /**
     * Checks whether the order with name <code>name</code> exists.
     * @return <code>true</code> if the order exists or <code>false</code> if it does not.
     */
    boolean hasOrder(String name);
    
    /**
     * Adds the drone <code>drone</code> to the hangar.
     */
    void addDroneToHangar(Drone drone);
    
    /**
     * Adds the drone <code>drone</code> to the sservice bay.
     */
    void addDroneToBay(Drone drone);
    
    /**
     * Adds the drone <code>drone</code> to the flying array.
     */
    void addDroneToFlying(Drone drone);
    
    /**
     * Adds the order <code>order</code> to the list of orders.
     */
    void addOrder(String name, int dimension, int latitude, int logitude);
    
    /**
     * Removes the drone <code>drone</code> from the hangar.
     */
    void removeDroneHangar(Drone drone);
    
    /**
     * Removes the drone <code>drone</code> from the service bay.
     */
    void removeDroneBay(Drone drone);
    
    /**
     * Removes the drone <code>drone</code> from the flying array.
     */
    void removeDroneFlying(Drone drone);
    
    /**
     * Removes the order <code>order</code> from the list of orders.
     */
    void removeOrder(Order order);
    
    /**
     * Retrieves a drone by the given name
     * @return a drone object that corresponds with name <code>name</code>, or <code>null</code> if it doesn't exist.
     */
    Drone getDroneByName(String name);
    
    /**
     * Creates an iterator to search through the drones in the hangar
     * @return an iterator for the hangar array.
     */
    DroneIterator hangarDrones();
    
    /**
     * Creates an iterator to search through the drones in the service bay
     * @return an iterator for the bay array.
     */
    DroneIterator bayDrones();
    
    /**
     * Creates an iterator to search through the drones that are flying
     * @return an iterator for the flying array.
     */
    DroneIterator flyingDrones();
    
    /**
     * Creates an iterator to search through the orders
     * @return an iterator for the orders array.
     */
    OrderIterator orders();

}
