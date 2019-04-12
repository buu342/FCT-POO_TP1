/**
 * @author Lourenco Soares 54530
 * Implementation of a singular drone
 */

package DronesRUs;

public class DroneClass implements Drone {

    private String name;            // Name of this drone
    private String type;            // Type of this drone ("HERMIT", "SOCIABLE" or "SWARM")
    private int capacity;           // Maximum carrying capacity of this drone
    private int range;              // The range this drone has left until next service
    private int maxrange;           // The maximum range this drone can get
    private int distance;           // Distance to the destination
    private int distancetraveled;   // Distance the drone traveled.
    private int serviceticks;       // Number of ticks left for the drone to be ready again
    private Base origbase;          // The base the drone came from
    private Base destbase;          // The base the drone's going to
    private Order order;            // The order the drone's holding
    
    public DroneClass(String name, String type, int capacity, int range) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.range = range;
        this.maxrange = range;
        this.distance = 0;
        this.distancetraveled = 0;
        this.serviceticks = 0;
        this.origbase = null;
        this.destbase = null;
        this.order = null;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getType() {
        return this.type;
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
    public int getDistance() {
        return this.distance;
    }
    
    @Override
    public int getDistanceTraveled() {
        return this.distancetraveled;
    }
    
    @Override
    public Base getOrigin() {
        return this.origbase;
    }
    
    @Override
    public Base getDestination() {
        return this.destbase;
    }
    
    @Override
    public int getServiceTicks() {
        return this.serviceticks;
    }
    
    @Override
    public Order getOrder() {
        return this.order;
    }

    @Override
    public void setRange(int range) {
        this.range = range;
    }
    
    @Override
    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    @Override
    public void setDistanceTraveled(int distance) {
        this.distancetraveled = distance;
    }
    
    @Override
    public void setOrigin(Base base) {
        this.origbase = base;
    }
    
    @Override
    public void setDestination(Base base) {
        this.destbase = base;
    }
    
    @Override
    public void setServiceTicks(int ticks) {
        this.serviceticks = ticks;
    }
    
    @Override
    public void setOrder(Order order) {
        this.order = order;
    }
}
