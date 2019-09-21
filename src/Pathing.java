import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;

import java.util.*;

/**
 * This class is the base for a graph of nodes to be created. This translates into maps for the game.
 *
 * Date Last Modified: 06 / 28 / 2019
 *
 * @author Shirley Krogel
 */

public class Pathing {

    private ArrayList<Zone> zones = new ArrayList<>();
    private Map<Zone, ArrayList<Zone>> map = new HashMap<Zone, ArrayList<Zone>>();
    private ArrayList<Directions> directions = new ArrayList<>();
    private ArrayList<Image> backgrounds = new ArrayList<>();

    public Pathing() {
    }

    /**
     * Getters.
     * @return
     */
    public ArrayList<Directions> getDirections() {
        return directions;
    }

    public ArrayList<Image> getBackgrounds() {
        return backgrounds;
    }

    public Zone getZone(int i) {
        return zones.get(i);
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public Map<Zone, ArrayList<Zone>> getMap() {
        return map;
    }

    /**
     * Adds a zone.
     * @param zone
     */
    protected void addZone(Zone zone) {
        zones.add(zone);
    }

    /**
     * Finds the directions from other zones to the given zone.
     * @param source
     */
    protected void findDirections(int source) {
        for (int i = 0; i < zones.size(); i++) {
            if (i + 1 != source) {
                if (zones.get(i).getX() == zones.get(source - 1).getX() && zones.get(i).getY() < zones.get(source - 1)
                        .getY()) {
                    directions.add(new Directions(source, i + 1, "North"));
                } else if (zones.get(i).getX() == zones.get(source - 1).getX() && zones.get(i).getY() > zones.get
                        (source - 1).getY()) {
                    directions.add(new Directions(source, i + 1, "South"));
                } else if (zones.get(i).getX() < zones.get(source - 1).getX() && zones.get(i).getY() == zones.get
                        (source - 1).getY()) {
                    directions.add(new Directions(source, i + 1, "West"));
                } else if (zones.get(i).getX() > zones.get(source - 1).getX() && zones.get(i).getY() == zones.get
                        (source - 1).getY()) {
                    directions.add(new Directions(source, i + 1, "East"));
                } else if (zones.get(i).getX() < zones.get(source - 1).getX() && zones.get(i).getY() < zones.get
                        (source - 1).getY()) {
                    directions.add(new Directions(source, i + 1, "NorthWest"));
                } else if (zones.get(i).getX() < zones.get(source - 1).getX() && zones.get(i).getY() > zones.get
                        (source - 1).getY()) {
                    directions.add(new Directions(source, i + 1, "SouthWest"));
                } else if (zones.get(i).getX() > zones.get(source - 1).getX() && zones.get(i).getY() < zones.get
                        (source - 1).getY()) {
                    directions.add(new Directions(source, i + 1, "NorthEast"));
                } else if (zones.get(i).getX() > zones.get(source - 1).getX() && zones.get(i).getY() > zones.get
                        (source - 1).getY()) {
                    directions.add(new Directions(source, i + 1, "SouthEast"));
                }
            }
        }
    }

    /**
     * Sets the background images to be used in-game.
     * @param images ArrayList containing the backgrounds for each zone in order of number.
     */
    protected void setBackgroundImages(ArrayList<Image> images) {
        backgrounds.addAll(images);
    }

    /**
     * Adds an edge between two nodes. Only creates a one direction connection to allow for one-way paths.
     * @param source
     * @param destination
     */
    public void addEdge(int source, int destination) {
        Zone sourceZone = zones.get(source - 1);
        Zone destinationZone = zones.get(destination - 1);
        // Adds a new path.
        if (!map.containsKey(sourceZone)) {
            /*
            Stores a list of neighbours for a node.
            */
            ArrayList<Zone> adjacent = new ArrayList<Zone>();
            adjacent.add(destinationZone);
            map.put(sourceZone, adjacent);
        } else {
            // Updates a path.
            ArrayList<Zone> oldList = map.get(sourceZone);
            // If the destination is not already in the path, then
            // add it to the path.
            if (!oldList.contains(destinationZone)) {
                oldList.add(destinationZone);
                map.put(sourceZone, oldList);
            }
        }
        storeAreas(sourceZone, destinationZone);
    }

    /**
     * Stores the nodes in this Graph.
     */
    private void storeAreas(Zone source, Zone destination) {
        if (!source.equals(destination)) {
            if (!zones.contains(destination)) {
                zones.add(destination);
            }
        }
        if (!zones.contains(source)) {
            zones.add(source);
        }
    }

    /**
     * Returns the neighboursList for this node.
     *
     * @param area the node where its neighbours will be searched for. Requires:
     *             node must be present in this Graph and not null.
     * @return the neighboursList for this node.
     */
    public ArrayList<Zone> getNeighbours(Zone area) {
        ArrayList<Zone> neighboursList;
        Set<Zone> keys = map.keySet();
        for (Zone key : keys) {
            if (key.equals(area)) {
                neighboursList = map.get(key);
                return new ArrayList<Zone>(neighboursList);
            }
        }
        return new ArrayList<Zone>();
    }

    /**
     * Checks if the node is in this Graph.
     *
     * @return true if the node is in this Graph.
     */
    public boolean memberOf(Zone node) {
        return zones.contains(node);
    }


}
