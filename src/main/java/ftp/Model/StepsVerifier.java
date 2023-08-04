package ftp.Model;

import java.util.List;

public class StepsVerifier {

    public static boolean verify(List<Coord> l1, List<GridCell> l2) {
        List<Coord> ans = l2.stream().map(e -> e.getPos()).toList();
        return ans.equals(l1);
    }

}
