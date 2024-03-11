import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**이름을 정렬 후 String 값을 Key로 설정하여 hashMap에 put 합니다
 * 이때 
 * 1. 만약 기존에 Key를 가지고 있다면 기존 가지고 있는 값에 +1(해당 소 조합이 방문) 를 한다
 * 2. Key 가 없다면 hashmap에 없으면 value를 1로 하여 새로 넣는다
 */

public class Main {


    static HashMap<String, Integer> hashMap;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        hashMap = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            String arr[] = s.split(" ");
            Arrays.sort(arr);

            String k = arr[0] + arr[1] + arr[2];

            if (hashMap.containsKey(k)) {
                hashMap.put(k, hashMap.get(k) + 1);
            } else {
                hashMap.put(k, 1);
            }
        }

        // value 중 최대값
        Integer maxValue = Collections.max(hashMap.values());

        System.out.println(maxValue);

    }
}