import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> AList;
    static List<Integer> BList;
    static List<List<Integer>> choiceList;
    static List<Integer> indexList;



    public static void main(String[] args) throws IOException {
        // 초기화 진행
        AList = new ArrayList<>();
        BList = new ArrayList<>();
        choiceList = new ArrayList<>();
        indexList = new ArrayList<>();

        int k = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++) {
            AList.add(Integer.parseInt(br.readLine().trim()));
        }

        int m = Integer.parseInt(br.readLine().trim());

        for (int i=0; i<m; i++) {
            BList.add(Integer.parseInt(br.readLine().trim()));
        }

        // 정렬
        BList.sort(Comparator.naturalOrder());

        // 경우의 수
        k = n-m+1;

        // 2차원 배열 초기화
        for (int i=0; i<k; i++) {
            choiceList.add(new ArrayList<>());
        }

        // 경우의 수만큼 나누어 정렬진행한 리스트를 2차원 리스트인 choiceList에 추가
        for (int i=0; i<k; i++) {
            List<Integer> sub = AList.subList(i, i+m);
            List<Integer> sortedSubList = new ArrayList<>(sub);
            sortedSubList.sort(Comparator.naturalOrder());
            choiceList.get(i).addAll(sortedSubList);
        }

        for (int i=0; i<k; i++) {
            List<Integer> sub = choiceList.get(i);
            int interval = checkInterval(sub);

            boolean b = correct(sub, interval);

            if (b) {
                indexList.add(i + 1);
            }
        }

        int idx = indexList.size();

        System.out.println(idx);

        for (int i=0; i<idx; i++) {
            System.out.println(indexList.get(i));
        }
    }

    // 실제 값과 차이
    public static int checkInterval(List<Integer> sub) {
        int bListfirst = BList.get(0);
        int subFirst = sub.get(0);

        int interval = subFirst - bListfirst;

        return interval;
    }

    // interval 만큼 뺐을때 동일한 리스트인지 체크
    public static boolean correct(List<Integer> sub, int interval) {
        for (int i=0; i<sub.size(); i++) {
            if (!(sub.get(i) == (BList.get(i) + interval))) {
                return false;
            }
        }

        return true;
    }
}