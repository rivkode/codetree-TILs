import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int pp = sc.nextInt();
        int p = sc.nextInt();
        int k = 0;

        int[] arr = new int[10];
        arr[0] = pp;
        arr[1] = p;

        for (int i=2; i<10; i++) {
            int tmp = p + pp;
            pp = p;
            p = tmp;
            k = tmp % 10;
            arr[i] = k;
        }

        for (int i=0; i<10; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}