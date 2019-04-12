/**
 * @author Lourenco Soares 54530
 * Implementation of the swarm array iterator
 */

package DronesRUs;

public class SwarmIteratorClass implements SwarmIterator {

    private int counter;        // Number of elements in the array
    private Swarm[] swarms;     // Copy of the array to iterate
    private int currentindex;   // Current position of the iterator in the array
    
    public SwarmIteratorClass(int counter, Swarm[] swarms) {
        this.counter = counter;
        this.swarms = swarms;
        this.currentindex = 0;
    }

    @Override
    public boolean hasNext() {
        return (this.currentindex < this.counter);
    }

    @Override
    public Swarm next() {
        return this.swarms[this.currentindex++];
    }
    
    @Override
    public void rewind() {
        this.currentindex = 0;
    }

}
