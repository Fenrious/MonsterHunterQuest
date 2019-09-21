import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the only map made so far. It has a lot of initialization, but it has been made fairly straightforward.
 * The map is arguably the most important part of the program.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class TigerIsland extends Pathing {

    /**
     * The heaviest amount of initialization is in the map. Allows for a lot of customization in map creation.
     */
    public TigerIsland() {
        addZone(new Zone(1, new Herb(), new Herb(), new AntidoteFlower(), 0, 4));
        addZone(new Zone(2, new Honey(), new Herb(), new AntidoteFlower(), 1, 3));
        addZone(new Zone(3, new Herb(), new Herb(), new Honey(), 0, 2));
        addZone(new Zone(4, new BlueMushroom(), new BlueMushroom(), new Honey(), 0, 1));
        addZone(new Zone(5, new Herb(), new BlueMushroom(), new AntidoteFlower(), 0, 0));
        addZone(new Zone(6, new Herb(), new AntidoteFlower(), new Herb(), 1, 1));
        addZone(new Zone(7, new AntidoteFlower(), new Herb(), new AntidoteFlower(), 2, 1));
        addZone(new Zone(8, new Honey(), new BlueMushroom(), new BlueMushroom(), 1, 0));
        addZone(new Zone(9, new BlueMushroom(), new Honey(), new AntidoteFlower(), 2, 2));
        findDirections(1);
        findDirections(2);
        findDirections(3);
        findDirections(4);
        findDirections(5);
        findDirections(6);
        findDirections(7);
        findDirections(8);
        findDirections(9);
        ArrayList<Image> images = new ArrayList<>(Arrays.asList(
                new Image("/Area1.png"), new Image("/Area2.png"), new Image("/Area3.png"),
                new Image("/Area4.png"), new Image("/Area5.png"), new Image("/Area6.png"),
                new Image("/Area7.png"), new Image("/Area8.png"), new Image("/Area9.png")));
        setBackgroundImages(images);
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 1);
        addEdge(2, 3);
        addEdge(2, 9);
        addEdge(3, 1);
        addEdge(3, 2);
        addEdge(3, 4);
        addEdge(3, 6);
        addEdge(4, 3);
        addEdge(4, 5);
        addEdge(5, 4);
        addEdge(5, 6);
        addEdge(5, 8);
        addEdge(6, 3);
        addEdge(6, 5);
        addEdge(6, 7);
        addEdge(7, 6);
        addEdge(7, 8);
        addEdge(7, 9);
        addEdge(8, 5);
        addEdge(8, 7);
        addEdge(9, 2);
        addEdge(9, 7);
    }

}
