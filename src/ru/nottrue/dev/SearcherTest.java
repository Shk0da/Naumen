package ru.nottrue.dev;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Тестовый класс
 * Позволяет проверить, что базовые требования выполняются
 *
 * Для запуска теста необходим JUnit 4.x
 *
 */
public class SearcherTest
{
    @Test
    public void testBaseLogic()
    {
        String[] classNames = new String[] {
                "ClassA1",
                "ClassA2",
                "ClassA3",
                "ClassA4",
                "ClassA5",
                "ClassA6",
                "ClassA7",
                "ClassA8",
                "ClassA9",
                "ClassB" ,
                "ClassC" ,
                "ClassD1",
                "ClassD2"
        };

        long[] modificationDates = {
                1322191917000l,
                1322191918000l,
                1322191913000l,
                1322191913000l,
                1322191913000l,
                1322191913000l,
                1322191913000l,
                1322191913000l,
                1322191913000l,
                1322191914000l,
                1322191915000l,
                1322191916000l,
                1322191916000l
        };


        ISearcher searcher = new Searcher();
        searcher.refresh(classNames, modificationDates);
        String[] actualResult = searcher.guess("Class");

        String[] expectedResult = new String[] {
                "ClassA2",
                "ClassA1",
                "ClassD1",
                "ClassD2",
                "ClassC",
                "ClassB",
                "ClassA3",
                "ClassA4",
                "ClassA5",
                "ClassA6",
                "ClassA7",
                "ClassA8"
        };

        Assert.assertArrayEquals(expectedResult, actualResult);
    }
}
