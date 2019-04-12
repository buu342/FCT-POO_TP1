/**
 * @author Lourenco Soares 54530
 * Interface of an string array iterator
 */

package DronesRUs;

public interface StringIterator {
    
    /**
     * Checks whether there is another value in the array.
     * @return <code>true</code> if a next value exists or <code>false</code> if it does not.
     */
    boolean hasNext();
    
    /**
     * Advances the iterator.
     * @return The current value in the iterator.
     */
    String next();
 
    /**
     * Resets the iterator
     */
    void rewind();
    
}
