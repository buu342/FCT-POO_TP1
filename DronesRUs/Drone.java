/**
 * @author Lourenco Soares 54530
 * Interface of a singular drone
 */

package DronesRUs;

public interface Drone {

    /**
     * Retrieves the name of this drone.
     * @return A string with the name of the drone.
     */
    String getName();

    /**
     * Retrieves the type of this drone.
     * @return A string with the type of the drone.
     */
    String getType();

    /**
     * Retrieves the capacity of this drone.
     * @return An int with the capacity of the drone.
     */
    int getCapacity();
    
    /**
     * Retrieves the maximum range of this drone.
     * @return An int with the maximum range of the drone.
     */
    int getMaxRange();
    
    /**
     * Retrieves the range of this drone.
     * @return An int with the range of the drone.
     */
    int getRange();
    
    /**
     * Retrieves the base which the drone came from of this drone.
     * @return An pointer to the base the drone came from.
     */
    Base getOrigin();
    
    /**
     * Retrieves the base which the drone is going to.
     * @return An pointer to the base which the drone is going to.
     */
    Base getDestination();
    
    /**
     * Sets the range of this drone to the range <code>range</code>.
     */
    void setRange(int range);
    
    /**
     * Retrieves the distance of this drone to the destination.
     * @return An int with the distance the drones needs to fly.
     */    
    int getDistance();
    
    /**
     * Retrieves the distance this drone traveled to the destination.
     * @return An int with the distance travaled by the drone.
     */    
    int getDistanceTraveled();
    
    /**
     * Retrieves the number of ticks left to repair this drone.
     * @return An int with the number of ticks left to repair this drone.
     */
    int getServiceTicks();
    
    /**
     * Retrieves the order assigned to this drone
     * @return A pointer to the order object
     */
    Order getOrder();
    
    /**
     * Sets the distance of this drone to its destination to <code>distance</code>.
     */
    void setDistance(int distance);
    
    /**
     * Sets the distance traveled for this drone to reach its destination to <code>distance</code>.
     */
    void setDistanceTraveled(int distance);
    
    /**
     * Sets the original base the drone came from to <code>base</code>.
     */
    void setOrigin(Base base);
    
    /**
     * Sets the base the drone is going to to <code>base</code>.
     */
    void setDestination(Base base);
    
    /**
     * Sets the number of ticks to repair this drone to <code>ticks</code>
     */
    void setServiceTicks(int ticks);
    
    /**
     * Sets the order for this to deliver to <code>order</code>
     */
    void setOrder(Order order);
}
