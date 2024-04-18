import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();

        for (int i=0; i<n; i++) {
            int v = Integer.parseInt(st.nextToken());
            if ((v % 2) == 0) {
                list.add(v);
            }
        }

        int[] arr = new int[list.size()];

        for (int i=0; i<list.size(); i++) {
            arr[i] = list.get(i);
        }

        for (int v : arr) {
            System.out.println(v);
        }


    }
}