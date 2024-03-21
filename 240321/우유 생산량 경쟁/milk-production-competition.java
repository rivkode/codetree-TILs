import java.util.*;
import java.io.*;

public class Main {
    static int M, E, B, changeCount;
    static List<List<String>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = 0;
        E = 0;
        B = 0;
        changeCount = 0;
        list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<String> input = new ArrayList<>();
            input.add(st.nextToken());
            input.add(st.nextToken());
            input.add(st.nextToken());
            list.add(input);
        }

// 루프 시작
        Set<String> previousList = new HashSet<>();

        for (int i = 1; i < 101; i++) {
            // i번째 State 시작

            List<Integer> nums = new ArrayList<>();
            
            // 1부터 list.size() 까지 돌면서 i 번째 인덱스를 찾아서 i Stage에 해당 연산을 수행하기 위한 nums들의 모음
            for (int j = 0; j < list.size(); j++) {
                if (Integer.parseInt(list.get(j).get(0)) == i) {
                    nums.add(j);
                }
            }

            for (int k = 0; k < nums.size(); k++) {
                String cowStr = list.get(nums.get(k)).get(1);
                String cow = String.valueOf(cowStr.charAt(0));
                Integer integer = Integer.parseInt(list.get(nums.get(k)).get(2));
                calculate(cow, integer);
            }

            Set<String> currentList = compare();
            previousList = boardCheck(previousList, currentList);
        }

        System.out.println(changeCount);


    }

    public static void calculate(String cow, Integer integer) {
        if (cow.equals("M")) {
            M += integer;
        } else if (cow.equals("E")) {
            E += integer;
        } else if (cow.equals("B")) {
            B += integer;
        }
    }

    public static Set<String> compare() {
        List<Integer> result = new ArrayList<>();
        Set<String> strResult = new HashSet<>();

        if (M > E) {
            if (M > B) {
                result.add(M);
                strResult.add("M");
            } else {
                result.add(B);
                strResult.add("B");
            }
        } else {
            if (E > B) {
                result.add(E);
                strResult.add("E");
            } else {
                result.add(B);
                strResult.add("B");
            }
        }

        // 최대값
        int max = Collections.max(result);

        // 최대값과 같다면 모두 넣기
        // 만약 3개가 다 같다면 ?
        if (max == M) {
            strResult.add("M");
        }

        if (max == B) {
            strResult.add("B");
        }

        if (max == E) {
            strResult.add("E");
        }

        // 사이즈가 3일 경우 제외 : 모두 같을 경우 전광판에 오를 수 없음
        if (strResult.size() == 3) {
            return new HashSet<>();
        }

        return strResult;
    }

    public static Set<String> boardCheck(Set<String> previousList, Set<String> currentList) {
        if (!previousList.equals(currentList)) {
            changeCount += 1;
        }

        return currentList;

    }


}