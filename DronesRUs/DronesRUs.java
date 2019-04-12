/**
 * @author Lourenco Soares 54530
 * Interface of Drones R US Inc.
 */

package DronesRUs;

public interface DronesRUs {
    
    /**
     * Checks whether the service has registered bases.
     * @return <code>false</code> if there are registered bases or <code>true</code> if there are none.
     */
    boolean hasBases();

    /**
     * Checks whether the service has registered drones.
     * @return <code>false</code> if there are registered drones or <code>true</code> if there are none.
     */
    boolean hasDrones();
    
    /**
     * Checks whether the base with name <code>name</code> exists.
     * @return <code>true</code> if the base exists or <code>false</code> if it does not.
     */
    boolean hasBase(String name);
    
    /**
     * Checks whether the drone with name <code>name</code> exists.
     * @return <code>true</code> if the drone exists or <code>false</code> if it does not.
     */
    boolean hasDrone(String name);
    
    /**
     * Checks whether the swarm with name <code>name</code> exists.
     * @return <code>true</code> if the swarm exists or <code>false</code> if it does not.
     */
    boolean hasSwarm(String name);
    
    /**
     * Checks whether there are flights.
     * @return <code>true</code> if there are flights or <code>false</code> if there are not.
     */
    boolean hasFlights();
    
    /**
     * Retrieves the total number of active orders
     * @return an int with the number of active orders.
     */
    int totalOrders();
    
    /**
     * Retrieves the total number of deliveries
     * @return an int with the number of deliveries.
     */
    int totalDeliveries();
    
    /**
     * Checks whether there is a base occupying the position [<code>latitude</code>,<code>longitutde</code>].
     * @return <code>true</code> if the position is occupied or <code>false</code> if it is not.
     */
    boolean positionOccupied(int latitude, int longitude);
    
    /**
     * Registers a base with latitude <code>latitude</code>, longitude <code>longitude</code>, and name <code>name</code>. 
     */
    void registerBase(int latitude, int longitude, String name);

    /**
     * Registers a drone with name <code>name</code>, type <code>type</code>, and capacity <code>capacity</code>, and maximum range <code>maxrange</code> to base <code>base</code>.
     * @return The newly created drone
     */
    Drone registerDrone(Base base, String name, String type, int capacity, int maxrange);
    
    /**
     * Registers a swarm with name <code>swarmname</code> into the base <code>base</code>. 
     */
    void registerSwarm(Base base, String swarmname, Drone[] drones, int maxrange, int capacity);
    
    /**
     * Registers a flight with drone <code>drone</code>. 
     */
    void registerFlight(Drone drone, Base baseorig, Base basedest, int distance);
    
    /**
     * Returns a drone from the list of drones with name <code>name</code>
     * @return A pointer to the drone object
     */
    Drone getDroneFromName(String name);
    
    /**
     * Returns a swarm from the list of swarms with name <code>name</code>
     * @return A pointer to the swarm object
     */
    Swarm getSwarmFromName(String name);
    
    /**
     * Disbands the swarm <code>swarm</code>
     * @return An array with all the drones that were in this swarm.
     */
    Drone[] disbandSwarm(Swarm swarm);
    
    /**
     * Advances the time by <code>numticks</code> ticks. 
     */
    void advanceTicks(int numticks);
    
    /**
     * Creates an iterator to search through the list of registered bases
     * @return an iterator for the bases array.
     */
    BaseIterator bases();
    
    /**
     * Creates an iterator to search through the list of registered drones
     * @return an iterator for the drones array.
     */
    DroneIterator drones();
    
    /**
     * Creates an iterator to search through the list of registered swarms
     * @return an iterator for the swarms array.
     */
    SwarmIterator swarms();
    
    /**
     * Creates an iterator to search through the list of flights
     * @return an iterator for the flights
     */
    DroneIterator flights();
    
    /**
     * Creates an iterator to search through the list of deliveries
     * @return an iterator for the deliveries array.
     */
    StringIterator deliveries();

}
