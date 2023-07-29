package ftp.Model;

import java.util.List;

public class StepsVerifier {

    public static boolean verify(List<Coord> l1, List<Coord> l2) {
        if (l1.size() != l2.size()) {
            return false;
        } else {
            for (int i = 0; i < l1.size(); i++) {
                if (!l1.get(i).sameCoord(l2.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
