import java.io.*;
import java.util.*;

/**
 *
 */

public class Main {
    static List<Integer> ansList;
    static Set<Integer> setList;
    static List<Integer> inputList;
    static List<List<Integer>> numList;
    static Integer answer;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int n = Integer.parseInt(br.readLine());
        inputList = new ArrayList<>();
        setList = new HashSet<>();
        numList = new ArrayList<>();
        ansList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inputList.add(Integer.parseInt(br.readLine()));
        }

        setList.addAll(inputList);


        if (setList.size() != 1) {
            Iterator<Integer> iterator = setList.iterator();

            // set 리스트를 돌며 모든 숫자에 대해 제거해본다
            while (iterator.hasNext()) {
                Integer targetNumber = iterator.next();
                List<Integer> lst = new ArrayList<>();
                lst.addAll(inputList);
                lst.removeAll(Arrays.asList(Integer.valueOf(targetNumber)));
                numList.add(lst);
            }

            int cnt = numList.size();

            for (int i = 0; i < cnt; i++) {
                List<Integer> targetList = numList.get(i);
                ansList.add(duplicatedCount(targetList));
            }
            // 최대값 찾기
            answer = Collections.max(ansList);
        } else {
            answer = 0;
        }

        System.out.println(answer);
    }

    // 입력된 리스트에 대해 가장 큰 중복된 숫자 개수를 리턴한다
    public static int duplicatedCount(List<Integer> list) {
        int duplicatedCount = 1;
        int maxDuplicatedCount;

        List<Integer> countList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(list.get(0));

        // 다음 수가 왔을때 리스트에 따로 저장해서
        // duplicatedCount들의 최대값을 return 해야함
        for (int i = 1; i < list.size(); i++) {
            Integer stackPeek = stack.peek();
            int listValue = list.get(i);

            if (stackPeek.equals(listValue)) {
                stack.add(listValue);
                duplicatedCount = stack.size();
            } else {
                countList.add(duplicatedCount);
                // 중복 count 초기화
                duplicatedCount = 1;
                stack.clear();
                stack.push(listValue);
            }
        }

        maxDuplicatedCount = Collections.max(countList);

        return maxDuplicatedCount;
    }
}