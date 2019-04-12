/**
 * @author Lourenco Soares 54530
 * Implementation of the order array iterator
 */

package DronesRUs;

public class OrderIteratorClass implements OrderIterator {

    private int counter;        // Number of elements in the array
    private Order[] orders;     // Copy of the array to iterate
    private int currentindex;   // Current position of the iterator in the array
    
    public OrderIteratorClass(int counter, Order[] orders) {
        this.counter = counter;
        this.orders = orders;
        this.currentindex = 0;
    }

    @Override
    public boolean hasNext() {
        return (this.currentindex < this.counter);
    }

    @Override
    public Order next() {
        return this.orders[this.currentindex++];
    }
    
    @Override
    public void rewind() {
        this.currentindex = 0;
    }

}
