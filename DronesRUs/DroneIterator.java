/**
 * @author Lourenco Soares 54530
 * Interface of a drone array iterator
 */

package DronesRUs;

public interface DroneIterator {
    
    /**
     * Checks whether there is another value in the array.
     * @return <code>true</code> if a next value exists or <code>false</code> if it does not.
     */
    boolean hasNext();
    
    /**
     * Advances the iterator.
     * @return The current value in the iterator.
     */
    Drone next();
 
    /**
     * Resets the iterator
     */
    void rewind();
    
}
