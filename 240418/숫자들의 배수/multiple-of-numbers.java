import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int v = Integer.parseInt(br.readLine());
        int count = 0;
        int cnt = 1;
        int ans = v;



        while (true) {
            bw.write(String.valueOf(ans) + " ");
            cnt++;
            

            if (ans % 5 == 0) {
                count++;
                if (count == 2) {
                    break;
                }
            }

            ans = v * cnt;
        }

        bw.flush();
        bw.close();
    }
}