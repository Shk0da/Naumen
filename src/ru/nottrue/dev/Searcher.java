package ru.nottrue.dev;

import java.util.*;
import java.util.concurrent.*;

public class Searcher implements ISearcher {

    private ArrayList resultArray;

    @Override
    public String[] guess(String start) {
        int length = 12;
        return new ForkJoinPool().invoke(new SubGuess(resultArray, start, length, 0, resultArray.size()));
    }

    @Override
    public void refresh(String[] classNames, long[] modificationDates) {
        Map<String, Long> classes = new HashMap<>(100000, 1.00f);
        for(int i=0; i < classNames.length; i++)
            classes.put(classNames[i], modificationDates[i]);

        classes = new TreeMap<String, Long>(classes);

        List<Map.Entry<String, Long>> sortedClasses = new ArrayList<>(classes.entrySet());
        Collections.sort(sortedClasses, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> me1, Map.Entry<String, Long> me2) {
                Long l1 = me1.getValue();
                Long l2 = me2.getValue();
                return (l1 < l2) ? 1 : (l1.equals(l2)) ? 0 : -1;
            }
        });

        resultArray = new ArrayList();

        for (Map.Entry<String, Long> e : sortedClasses)
            resultArray.add((String)e.getKey());
    }
}