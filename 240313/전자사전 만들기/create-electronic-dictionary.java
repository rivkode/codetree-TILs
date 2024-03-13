import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 초기화 변수 : 전체 단어 개수, 조건 단어 개수, 기존 단어 리스트, 정렬 단어 리스트, 조건 단어
 *
 * 입력받은 단어들을 정렬
 * 각 단어개수만큼 반복문 진행
 *
 * 사전에서 단어를 포함하는 범위를 찾기 위해 단어 길이만큼 start, end 인덱스 찾기
 * 단어를 포함하는 범위를 찾은 경우 start, end, interval를 비교하여 찾는 단어가 있는지 체크
 * 만약 없으면 -1 반환
 * 있다면 start인덱스에서 interval만큼 더한 인덱스를 기존 단어리스트에서 찾고
 * 그 단어의 인덱스를 반환
 */
public class Main {
    static int N;
    static int T;
    static List<String> existLst;
    static List<String> sortedLst;
    static String[][] arrInput;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        T = Integer.parseInt(arr[1]);

        existLst = new ArrayList<>();
        sortedLst = new ArrayList<>();

        // 2개의 리스트로 나누어 입력
        // existLst : 기존 입력 리스트
        // sortedLst : 정렬할 리스트
        for (int i=0; i<N; i++) {
            String s = br.readLine();
            existLst.add(s);
            sortedLst.add(s);
        }

        // 단어조건 입력
        arrInput = new String[T][2];
        for (int i=0; i<T; i++) {
            // 4 a
            arrInput[i] = br.readLine().split(" ");
        }

        // 리스트 정렬 : 사전
        Collections.sort(sortedLst);

        // 단어 탐색
        for (int i = 0; i < T; i++) {
            String word = arrInput[i][1];
            int wordLength = word.length();
            int start = 0;
            int end = N - 1;


            // 글자 길이만큼 탐색 반복
            // 최종으로 2개의 인덱스를 가져가야 한다
            for (int j = 0; j < wordLength; j++) {

                // j : 단어 탐색하고자 하는 인덱스
                start = searchStart(j, word, start, end);
                end = searchEnd(j, word, start, end);
            }

            // 특정 단어로 시작하는 단어의 수가 k개가 되지 않는다면 -1를 출력
            if ((end - start) < Integer.parseInt(arrInput[i][0])) {
                System.out.println(-1);
            } else {
                int interval = Integer.parseInt(arrInput[i][0]);
                // 단어를 기존의 리스트에서 인덱스를 찾아서 인덱스 위치 반환
                String findWord = sortedLst.get(start + interval - 1);
                int result = existLst.indexOf(findWord) + 1;
                System.out.println(result);
            }
        }
    }

    // j : 단어 내의 검색 순서
    // 사전(sortedLst)에서 찾고자 하는 단어의 첫글자와 매칭 후 일치하면 해당 인덱스 반환
    // 찾는 단어가 없으면 ?
    //
    public static int searchStart(int j, String word, int start, int end) {
        for (int i = start; i < end; i++) {
            String s = sortedLst.get(i);

            // 사전에 있는 단어가 찾고자 하는 word보다 작을 경우
            if (j >= s.length()) {
                continue;
            } else {
                if (s.charAt(j) == word.charAt(j)) {
                    return i;
                }
            }
        }
        return start;
    }

    public static int searchEnd(int j, String word, int start, int end) {
        for (int i = end; i > start; i--) {
            String s = sortedLst.get(i);
            if (j >= s.length()) {
                return i + 1;
            } else {
                if (s.charAt(j) == word.charAt(j)) {
                    return i;
                }
            }
        }
        return end;
    }
}