/**
 * @author Lourenco Soares 54530
 * Implementation of a singular order
 */

package DronesRUs;

public class OrderClass implements Order {

    private String name;    // Name of the order
    private int dimension;  // Size of the order
    private int latitude;   // Latitude coordinate of the order
    private int longitude;  // Longitude coordinate of the order
    
    public OrderClass(String name, int dimension, int latitude, int longitude) {
        this.name = name;
        this.dimension = dimension;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDimension() {
        return this.dimension;
    }

    @Override
    public int getLatitude() {
        return this.latitude;
    }

    @Override
    public int getLongitude() {
        return this.longitude;
    }
    
}
