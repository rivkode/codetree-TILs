import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> list;
    static Integer count;

    static boolean isCreateNegative; // 감소 부분의 리스트를 생성해도 되는가 ?

    static boolean isCreatePositive; // 증가 부분의 리스트를 생성해도 되는가 ?

    /**
     * 핵심 로직
     * - 양방향으로 폭탄위치를 찾기 위해 타겟 숫자 기준 증가, 감소 리스트를 생성한다
     * - 이때 boolean 값으로 생성해도 되는지 여부를 체크한다
     * - 각 증가 감소 리스트를 순회하며 폭탄위치와 리스트의 위치를 비교하여
     * - 만약 두 위치가 일치할 경우 폭탄이 터지는 것으로 간주하여 result++를 수행한다
     * - 이때 containList에 추가한다
     * - 만약 containList가 비었으면 다음번의 감소, 증가리스트를 생성하지 않는다
     * - 가장 폭탄이 많이 터진 횟수를 알기 위해 Math.max()를 사용하여 높은 값으로 초기화 한다
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        // 정적 요소 초기화
        list = new ArrayList<>();
        count = 0;

        // 입출력 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            list.add(num);
        }
        // 입력받은 내용 정렬
        Collections.sort(list);


        // 입력 리스트의 모든 원소를 1번씩 순회
        for (int i = 0; i < N; i++) {
            int targetNumber = list.get(i);
            int result = 0;


            int negativeNumber = targetNumber;
            int positiveNumber = targetNumber;
            isCreateNegative = true;
            isCreatePositive = true;


            for (int j = 1; j < N; j++) {
                List<Integer> containNegative = new ArrayList<>(); // 감소 리스트 초기화
                List<Integer> containPositive = new ArrayList<>(); // 증가 리스트 초기화
                List<Integer> negativeList = createNegativeNumber(j, negativeNumber, isCreateNegative);
                List<Integer> positiveList = createPositiveNumber(j, positiveNumber, isCreatePositive);


                for (int k = 0; k < negativeList.size(); k++) {
                    if (list.contains(negativeList.get(k))) {
                        result++;
                        negativeNumber = negativeList.get(k); // 감소 숫자 초기화 -> 다음 폭탄 위치를 정하기 위해
                        containNegative.add(k);
                    }
                }
                if (containNegative.isEmpty()) {
                    isCreateNegative = false; // 폭탄이 터지지 않았다면 감소리스트 생성 하지 않음
                }

                for (int k = 0; k < positiveList.size(); k++) {
                    if (list.contains(positiveList.get(k))) {
                        result++;
                        positiveNumber = positiveList.get(k); // 증가 숫자 초기화 -> 다음 폭탄 위치를 정하기 위해
                        containPositive.add(k);
                    }
                }
                if (containPositive.isEmpty()) {
                    isCreatePositive = false; // 폭탄이 터지지 않았다면 증가리스트 생성 하지 않음
                }
            }

            count = Math.max(result + 1, count);
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    // number 기준 증가 리스트 생성
    public static List<Integer> createPositiveNumber(int j, int number, boolean isCreatePositive) {
        List<Integer> numberList = new ArrayList<>();

        if (!isCreatePositive) { // 폭탄이 터지지 않았다면 빈배열 반환
            return numberList;
        }

        for (int i = 0; i < j; i++) {
            numberList.add(number + i + 1);
        }

        return numberList;
    }

    // number 기준 감소 리스트 생성
    public static List<Integer> createNegativeNumber(int j, int number, boolean isCreateNegative) {
        List<Integer> numberList = new ArrayList<>();

        if (!isCreateNegative) { // 폭탄이 터지지 않았다면 빈배열 반환
            return numberList;
        }
        int abs = Math.abs(j);

        for (int i = 0; -abs < i; i--) {
            int insertNumber = number + i - 1;
            if (((insertNumber) >= 0)) {
                numberList.add(insertNumber);
            }
        }

        return numberList;
    }
}