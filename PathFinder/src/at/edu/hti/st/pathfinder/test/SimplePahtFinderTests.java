/*
 * Copyright (c) by Christian Trummer
 */
package at.edu.hti.st.pathfinder.test;

import org.junit.Test;

import at.edu.hti.st.pathfinder.SimplePathFinder;

public class SimplePahtFinderTests {

    @Test
    public void test() {
        SimplePathFinder finder = new SimplePathFinder();
        finder.setWeigthMatrix(new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
        finder.wrritePathToConsole();
    }
}
