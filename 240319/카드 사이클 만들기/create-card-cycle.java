import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> list1;
    static ArrayList<Integer> list2;
    static ArrayList<Integer> visit;
    static PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
    static int cycleCount;
    static int cnt;
    static int cycleFlag;
    static int cycleExit;




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        visit = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list1.add(Integer.valueOf(br.readLine()));
        }

        for (int i = 0; i < n; i++) {
            list2.add(Integer.valueOf(br.readLine()));
        }

        for (int i = 0; i < n; i++) {
            visit.add(Integer.valueOf(i));
        }

        int tmp = 0;
        int startIdx = 0;
        cnt = 0;
        cycleFlag = 1;
        cycleExit = 0;
        for (int i = 0; i < n; i++) {
            int a = list1.get(tmp);
            tmp = check(list1.get(tmp), startIdx, cnt);
            cnt += 1;

            // 싸이클이 종료되면
            if (cycleFlag == 0) {
                tmp = restartCycle();
                if (cycleExit == 1) {
                    break;
                }
            }
        }

        if (!pQ.isEmpty()) {
            System.out.print(cycleCount + " " + pQ.peek());
        } else {
            System.out.println("0 -1");
        }
    }

    private static int restartCycle() {
        cycleCount += 1;
        // 유효하지 않은 개수
        if (cnt > 1) {
            pQ.add(cnt);
        }

        // 개수 초기화
        cnt = 0;
        return getNextVisit();
    }

    private static int check(int i, int startIdx, int cnt) {
        // 다음 탐색 인덱스
        Integer idx = list2.indexOf(i);

        // 방문 인덱스 삭제
        visit.remove(idx);

        if (idx.equals(startIdx)) {
            cycleFlag = 0;
        }

        return idx;
    }

    private static int getNextVisit() {
        if (visit.size() > 1) {
            return visit.get(0);
        } else {
            cycleExit = 1;
            return cycleExit;
        }
    }
}