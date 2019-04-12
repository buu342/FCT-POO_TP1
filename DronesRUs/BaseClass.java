/**
 * @author Lourenco Soares 54530
 * Implementation of a singular Base
 */

package DronesRUs;

public class BaseClass implements Base {
    
    public static final int DEFAULT_SIZE = 10;
    
    private int latitude;       // Latitude of the base
    private int longitude;      // Longitude of the base
    private String name;        // Name of the base
    private int counterhangar;  // Number of drones in the hangar
    private int counterbay;     // Number of drones in the service bay
    private int counterflying;  // Number of drones currently flying
    private int counterorder;   // Number of orders to fulfill
    private Drone[] hangar;     // Array of drones in the hangar
    private Drone[] bay;        // Array of drones in the service bay
    private Drone[] flying;     // Array of drones currently flying
    private Order[] orders;     // Array of orders to fulfill
    
    public BaseClass(int latitude, int longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.counterhangar = 0;
        this.counterbay = 0;
        this.counterflying = 0;
        this.counterorder = 0;
        this.hangar = new DroneClass[DEFAULT_SIZE];
        this.bay = new DroneClass[DEFAULT_SIZE];
        this.flying = new DroneClass[DEFAULT_SIZE];
        this.orders = new OrderClass[DEFAULT_SIZE];
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLatitude() {
        return this.latitude;
    }

    @Override
    public int getLongitude() {
        return this.longitude;
    }
    
    @Override
    public int hangarCount() {
        return this.counterhangar;
    }
    
    @Override
    public int bayCount() {
        return this.counterbay;
    }
    
    @Override
    public int flyingCount() {
        return this.counterflying;
    }
    
    @Override
    public int orderCount() {
        return this.counterorder;
    }    
    
    @Override
    public boolean hasOrder(String name) {
        int i = 0;
        int len = this.orders.length;
        
        // Search through the orders array and return if the order with name 'name' exists
        while (i < len) {
            if (this.orders[i] != null && this.orders[i].getName().equals(name))
                return true;
            i++;
        }
        
        return false;
    }
    
    @Override
    public void addDroneToHangar(Drone drone) {
        // If the hangar is full, resize it
        if (this.counterhangar == this.hangar.length)
            resizeHangar();
        
        this.hangar[this.counterhangar] = drone;
        this.counterhangar++;
    }
    
    @Override
    public void addOrder(String name, int dimension, int latitude, int longitude) {
        // If the orders array is full, resize it
        if (this.counterorder == this.orders.length)
            resizeOrders();

        this.orders[this.counterorder] = new OrderClass(name, dimension, latitude, longitude);
        this.counterorder++;
    }
    
    @Override
    public void addDroneToBay(Drone drone) {
        // If the bay is full, resize it
        if (this.counterbay == this.bay.length)
            resizeBay();
        
        this.bay[this.counterbay] = drone;
        this.counterbay++;
    }
    
    @Override
    public void addDroneToFlying(Drone drone) {
        // If the flying array is full, resize it
        if (this.counterflying == this.flying.length)
            resizeFlying();
        
        this.flying[this.counterflying] = drone;
        this.counterflying++;
    }

    @Override
    public void removeDroneHangar(Drone drone) {
        for (int i=0; i<this.counterhangar; i++)
            if (this.hangar[i] == drone) {
                this.hangar[i] = null;
                this.counterhangar--;
                compactHangar();
                break;
            }
    }
    
    @Override
    public void removeDroneBay(Drone drone) {
        for (int i=0; i<this.counterbay; i++)
            if (this.bay[i] == drone) {
                this.bay[i] = null;
                this.counterbay--;
                compactBay();
                break;
            }
    }
    
    @Override
    public void removeDroneFlying(Drone drone) {
        for (int i=0; i<this.counterflying; i++)
            if (this.flying[i] == drone) {
                this.flying[i] = null;
                this.counterflying--;
                compactFlying();
                break;
            }
    }
    
    @Override
    public void removeOrder(Order order) {
        for (int i=0; i<this.counterorder; i++)
            if (this.orders[i] == order) {
                this.orders[i] = null;
                this.counterorder--;
                compactOrders();
                break;
            }
    }
    
    @Override
    public Drone getDroneByName(String name) {
        for (int i=0; i<this.counterhangar; i++)
            if (this.hangar[i] != null && this.hangar[i].getName().equals(name))
                return this.hangar[i];
        return null;
    }
    
    @Override
    public DroneIterator hangarDrones() {
        return new DroneIteratorClass(this.counterhangar, this.hangar);
    }

    @Override
    public DroneIterator bayDrones() {
        return new DroneIteratorClass(this.counterbay, this.bay);
    }
    
    @Override
    public DroneIterator flyingDrones() {
        return new DroneIteratorClass(this.counterflying, this.flying);
    }
    
    @Override
    public OrderIterator orders() {
        return new OrderIteratorClass(this.counterorder, this.orders);
    }

    // Resize the hangar array
    private void resizeHangar() {
        // Create a temporary array with double the length
        int len = this.hangar.length;
        Drone tmp[] = new Drone[2*len];
        
        // Fill the temporary array with the contents of the old users array
        for (int i=0; i<len; i++)
            tmp[i] = this.hangar[i];
        
        // Set the new users array to the temporary array
        this.hangar = tmp;
    }
    
    // Resize the bay array
    private void resizeBay() {
        // Create a temporary array with double the length
        int len = this.bay.length;
        Drone tmp[] = new Drone[2*len];
        
        // Fill the temporary array with the contents of the old users array
        for (int i=0; i<len; i++)
            tmp[i] = this.bay[i];
        
        // Set the new users array to the temporary array
        this.bay = tmp;
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
    
    // Resize the orders array
    private void resizeOrders() {
        // Create a temporary array with double the length
        int len = this.orders.length;
        Order tmp[] = new Order[2*len];
        
        // Fill the temporary array with the contents of the old users array
        for (int i=0; i<len; i++)
            tmp[i] = this.orders[i];
        
        // Set the new users array to the temporary array
        this.orders = tmp;
    }
    
    // Compact the hangar array
    private void compactHangar() {
        for (int i=0; i<this.hangar.length; i++)
            if (this.hangar[i] == null) {
                for (int j=i+1; j<this.hangar.length; j++){
                    this.hangar[j-1] = this.hangar[j];
                }
                this.hangar[this.hangar.length-1] = null;
                break;
            }
    }
    
    // Compact the bay array
    private void compactBay() {
        for (int i=0; i<this.bay.length; i++)
            if (this.bay[i] == null) {
                for (int j=i+1; j<this.bay.length; j++){
                    this.bay[j-1] = this.bay[j];
                }
                this.bay[this.bay.length-1] = null;
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
    
    // Compact the orders array
    private void compactOrders() {
        for (int i=0; i<this.orders.length; i++)
            if (this.orders[i] == null) {
                for (int j=i+1; j<this.orders.length; j++){
                    this.orders[j-1] = this.orders[j];
                }
                this.orders[this.orders.length-1] = null;
                break;
            }
    }
}
