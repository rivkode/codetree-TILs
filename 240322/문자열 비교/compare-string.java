import java.util.*;
import java.io.*;
public class Main {

    static HashSet<String> input;
    static List<String> target;
    static Integer count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        input = new HashSet<>();
        target = new ArrayList<>();
        count = 0;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            input.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            target.add(br.readLine());
        }

        // target들을 돌며 input의 contains 함수를 통해 속해있는지 확인
        for (int i = 0; i < target.size(); i++) {
            String s = target.get(i);
            
            // 속해있다면 +1
            if (input.contains(s)) {
                count += 1;
            }
        }
        System.out.println(count);
    }
}