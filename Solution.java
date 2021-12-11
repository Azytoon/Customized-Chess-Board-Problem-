import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

public class Solution {

  // Complete the solve function below.
  static String solve(List<List<Integer>> board) {
    //1st solution order of N^3`
    /*int size = board.size();
        //check for first column
        for (int i = 0;i < size-1;i++){
            if(board.get(i).get(0) == board.get(i+1).get(0)) return "No";
        }
         //check for each row
        for (int i = 0;i < size;i++)
                for (int j = 0;j < size-1;j++)
                {
                    //System.out.println(board.get(i).get(j));
                    if(board.get(i).get(j) == board.get(i).get(j+1))  return "No";
                }
        return "Yes";*/

    //2nd solution order of N^2
    int first_cell = board.get(0).get(0);
    int size = board.size();

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if ((i + j) % 2 == 0) {
          if (board.get(i).get(j) != first_cell) {
            return "No";
          }
        } else {
          if (board.get(i).get(j) == first_cell) {
            return "No";
          }
        }
      }
    }
    return "Yes";
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(
      new InputStreamReader(System.in)
    );
    BufferedWriter bufferedWriter = new BufferedWriter(
      new FileWriter(System.getenv("OUTPUT_PATH"))
    );

    int t = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream
      .range(0, t)
      .forEach(
        tItr -> {
          try {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> board = new ArrayList<>();

            IntStream
              .range(0, n)
              .forEach(
                i -> {
                  try {
                    board.add(
                      Stream
                        .of(
                          bufferedReader
                            .readLine()
                            .replaceAll("\\s+$", "")
                            .split(" ")
                        )
                        .map(Integer::parseInt)
                        .collect(toList())
                    );
                  } catch (IOException ex) {
                    throw new RuntimeException(ex);
                  }
                }
              );

            String result = solve(board);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
      );

    bufferedReader.close();
    bufferedWriter.close();
  }
}
