/**
 * @author Lourenco Soares 54530
 * Implementation of the drone array iterator
 */

package DronesRUs;

public class DroneIteratorClass implements DroneIterator {

    private int counter;
    private Drone[] drones;
    private int currentindex;
    
    public DroneIteratorClass(int counter, Drone[] drones) {
        this.counter = counter;
        this.drones = drones;
        this.currentindex = 0;
    }

    @Override
    public boolean hasNext() {
        return (this.currentindex < this.counter);
    }

    @Override
    public Drone next() {
        return this.drones[this.currentindex++];
    }
    
    @Override
    public void rewind() {
        this.currentindex = 0;
    }

}
