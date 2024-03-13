import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split(" ");
        int[] arr = new int[n];
        
        for (int i=0; i<n; i++) {
            int k = Integer.parseInt(s[i]);
            arr[i] = k * k;
        }

        for (int i=0; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}