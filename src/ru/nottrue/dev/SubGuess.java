package ru.nottrue.dev;


import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

public class SubGuess extends RecursiveTask<String[]> {

    ArrayList resultArray;
    String[] result;
    String start;
    int seqTheesHold, length, foundIndex, ri, startPoint, endPoint;
    boolean found;
    Pattern searchWord;

    SubGuess(ArrayList resultArray, String start, int length, int startPoint, int endPoint){
        this.resultArray = resultArray;
        this.start = start;
        this.length = length;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        seqTheesHold = 15000;
        foundIndex = 0;
        ri = 0;
        found = false;
        searchWord = Pattern.compile(start+".*");
        result = new String[length];
    }

    @Override
    protected String[] compute() {

        if ((endPoint - startPoint) < seqTheesHold)
            do {
                found = searchWord.matcher(resultArray.get(foundIndex).toString()).matches();
                if (found) {
                    result[ri] = resultArray.get(foundIndex).toString();
                    ri++;
                }
                foundIndex++;
            } while ((foundIndex < endPoint) && (ri < length));
        else {
            int middlePoint = (startPoint + endPoint) / 2;

            SubGuess subGuess1 = new SubGuess(resultArray, start, length, startPoint, middlePoint);
            SubGuess subGuess2 = new SubGuess(resultArray, start, length, middlePoint, endPoint);

            subGuess1.fork();
            subGuess2.fork();

            String[] temp1 = subGuess1.join();
            String[] temp2 = subGuess2.join();

            System.arraycopy(temp1, 0, result, 0, temp1.length);
            System.arraycopy(temp2, 0, result, 0, temp2.length);

        }

        if (ri < length) {
            String[] temp = result;
            result = new String[ri];
            System.arraycopy(temp, 0, result, 0, ri);
        }

        return result;
    }

}
