/**
 * @author Lourenco Soares 54530
 * Interface of an swarm array iterator
 */

package DronesRUs;

public interface SwarmIterator {
    
    /**
     * Checks whether there is another value in the array.
     * @return <code>true</code> if a next value exists or <code>false</code> if it does not.
     */
    boolean hasNext();
    
    /**
     * Advances the iterator.
     * @return The current value in the iterator.
     */
    Swarm next();
 
    /**
     * Resets the iterator
     */
    void rewind();
    
}
