package ftp.Model;

import java.util.List;

/**
 * Utility class to verify user input from game
 */
public class StepsVerifier {

    /**
     * Determines if the list of coordinates is the same as the list of each grid cell's position
     *
     * @param l1 - the list of coordinates
     * @param l2 - the list of grid cells, each of which has coordinates
     * @return - true, if grid cells match the list of coordinates, false otherwise
     */
    public static boolean verify(List<Coord> l1, List<GridCell> l2) {
        List<Coord> ans = l2.stream().map(e -> e.getPos()).toList();
        return ans.equals(l1);
    }

}
