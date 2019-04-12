/**
 * @author Lourenco Soares 54530
 * Interface of a singular order
 */

package DronesRUs;

public interface Order {

    /**
     * Retrieves the name of this order.
     * @return A string with the name of the order.
     */
    String getName();
    
    /**
     * Retrieves the dimension of this order.
     * @return An int with the dimension of the order.
     */
    int getDimension();
    
    /**
     * Retrieves the latitude of this order.
     * @return An int with the latitude of the order.
     */
    int getLatitude();
    
    /**
     * Retrieves the longitude of this order.
     * @return An int with the longitude of the order.
     */
    int getLongitude();
    
}
