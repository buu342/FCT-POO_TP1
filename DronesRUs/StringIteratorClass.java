/**
 * @author Lourenco Soares 54530
 * Implementation of the string array iterator
 */

package DronesRUs;

public class StringIteratorClass implements StringIterator {

    private int counter;        // Number of elements in the array
    private String[] strings;   // Copy of the array to iterate
    private int currentindex;   // Current position of the iterator in the array
    
    public StringIteratorClass(int counter, String[] strings) {
        this.counter = counter;
        this.strings = strings;
        this.currentindex = 0;
    }

    @Override
    public boolean hasNext() {
        return (this.currentindex < this.counter);
    }

    @Override
    public String next() {
        return this.strings[this.currentindex++];
    }
    
    @Override
    public void rewind() {
        this.currentindex = 0;
    }

}
