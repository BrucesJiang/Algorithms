package fundamentals;

import util.api.Out;
import util.api.StdOut;
import util.api.StdRandom;

import java.io.*;
import java.net.URL;

/**
 * @auther Bruce Jiang
 */
public class RandomData {
    public static void main(String[] args){
        String fileName = "F:/Source/git_repo/Algorithms/exercise/src/main/resources/" + "MoveToFront.txt";
        Out out = new Out(fileName);
        for(int i = 0; i < 1000; i ++){
            int item = StdRandom.uniform(1, 100);
            out.println(item);
        }
    }
}
