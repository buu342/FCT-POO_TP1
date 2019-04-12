/**
 * @author Lourenco Soares 54530
 * Implementation of the base array iterator
 */

package DronesRUs;

public class BaseIteratorClass implements BaseIterator {

    private int counter;        // Number of elements in the array
    private Base[] bases;       // Copy of the array to iterate
    private int currentindex;   // Current position of the iterator in the array
    
    public BaseIteratorClass(int counter, Base[] bases) {
        this.counter = counter;
        this.bases = bases;
        this.currentindex = 0;
    }

    @Override
    public boolean hasNext() {
        return (this.currentindex < this.counter);
    }

    @Override
    public Base next() {
        return this.bases[this.currentindex++];
    }
    
    @Override
    public void rewind() {
        this.currentindex = 0;
    }

}
