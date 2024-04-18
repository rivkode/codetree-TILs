import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String v1 = "Total days in Year";
        String v2 = "365";
        String v3 = "Circumference rate";
        String v4 = "3.1415926535";

        bw.write(String.valueOf(v1));
        bw.newLine();
        bw.write(String.valueOf(v2));
        bw.newLine();
        bw.write(String.valueOf(v3));
        bw.newLine();
        bw.write(String.valueOf(v4));

        bw.flush();
        bw.close();

        

    }
}