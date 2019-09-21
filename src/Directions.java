/**
 * This class helps to figure out from what direction a roar sounds.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Directions {

    private int source;
    private int destination;
    private String direction;

    public Directions(int source, int destination, String direction) {
        this.source = source;
        this.destination = destination;
        this.direction = direction;
    }

    /**
     * Getters.
     *
     * @return
     */
    public int getLocation1() {
        return source;
    }

    public int getLocation2() {
        return destination;
    }

    public String getDirection() {
        return direction;
    }
}
