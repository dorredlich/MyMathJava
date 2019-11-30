package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

    /**
     * The 2 parameters that we compare:
     * @param a
     * @param b
     *
     *
     * @return 0 if they are equal , positive if a>b , negative if a<b
     */
    @Override

    public int compare(Monom a, Monom b) {
        return -(a.get_power() - b.get_power());    //I prefer to be smart then beautiful
    }
}

