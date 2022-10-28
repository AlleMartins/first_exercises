package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int MIN_ELEMS = 1000;
    private static final int MAX_ELEMS = 2000; 
    private static final int ELEMS = 100_000;
    private static final int ELEMS_PICCOLO = 1000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> popolazioni = new ArrayList<>();
        for (int i = MIN_ELEMS; i < MAX_ELEMS; i++) {
            popolazioni.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> popolazioni_2 = new LinkedList<>(popolazioni);
         
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int tmp = popolazioni.get(0);
        final int tmp_final = popolazioni.get(popolazioni.size() - 1 );
        popolazioni.set(0, tmp_final);
        popolazioni.set(popolazioni.size() - 1, tmp);        

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (final int elem : popolazioni) {
            System.out.println(elem);
        }

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();

        for (int i = 1; i <= ELEMS; i++) {
            popolazioni.add(0, i);
        }

        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);

        //---------------------------------------------------------------------------------//

        long time_2 = System.nanoTime();

        for (int i = 1; i <= ELEMS; i++) {
            popolazioni_2.add(0, i);
        }

        time_2 = System.nanoTime() - time_2;
        final var millis_2 = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println("Primo Tempo: " + millis);
        System.out.println("Secondo Tempo: " + millis_2);

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */

        long time_medium= System.nanoTime();

        for (int i = 1; i <= ELEMS_PICCOLO; i++) {
            popolazioni.get(popolazioni.size()/2);
        }

        time_medium = System.nanoTime() - time_medium;
        final var millis_medium = TimeUnit.NANOSECONDS.toMillis(time_medium);

        //---------------------------------------------------------------------------------//

        long time_2_medium = System.nanoTime();

        for (int i = 1; i <= ELEMS_PICCOLO; i++) {
            popolazioni_2.get(popolazioni.size()/2);
        }

        time_2_medium = System.nanoTime() - time_2_medium;
        final var millis_2_medium = TimeUnit.NANOSECONDS.toMillis(time_2_medium);

        System.out.println("Primo Tempo medio: " + millis_medium);
        System.out.println("Secondo Tempo medio: " + millis_2_medium);

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> mappa = new HashMap<>();
        mappa.put("Africa", 1_110_635L);
        mappa.put("Americas", 972_005_000L);
        mappa.put("Antarctica", 0L);
        mappa.put("Asia", 4_298_723_000L);
        mappa.put("Europe", 742_452_000L);
        mappa.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
        int populationFinal = 0;
        for (final Long elem : mappa.values()) {
            populationFinal += elem;
        }
        System.out.println("\nLa popolazione finale è: " + populationFinal);
    }
}
