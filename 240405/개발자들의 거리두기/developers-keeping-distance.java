import java.util.*;
import java.io.*;

/**
 * 핵심 로직
 * Programmer 객체를 클래스 내부에 선언하였습니다.
 * R을 각 요소를 순회하며 (다음 프로그래머 위치 - 현재 프로그래머 위치) 계산으로 최소 거리를 찾습니다
 *
 * 그리고 while 문을 통해 감염자를 찾기 위해 재귀함수를 호출합니다.
 *
 * 재귀함수 내에서의 동작은 아래와 같습니다.
 * ---
 * 먼저 가장 끝 인덱스에 도달하였는지 체크합니다.
 * 그리고 다음 인덱스의 개발자가 감염이 되었는지 체크합니다.
 * 감염되지 않았다면 최초 감염자 수에 들어가지 않아도 되기 때문입니다.
 * 다음 개발자도 감염이 되었다며 이제 거리를 측정합니다.
 * 거리가 만약 양수거리 R 범위 내에 속해있다면 그것은 감염으로 인한 것이므로
 * 다음 감염자를 찾기 위해 다시 자기 자신 재귀함수를 호출합니다.
 * ---
 * 만약 양수거리 안에 속해있지 않았는데 다음 개발자가 감염이 되었다면
 * 이는 최초감염자로 판단하고 i + 1을 반환하여 재귀함수를 호출하지 않고 다음 while문을 방문합니다.
 *
 * 즉, 재귀함수에서는 감염자를 찾아 끝까지 들어갑니다.
 * 재귀함수가 끝나는 시점은
 * - 양수거리 R 범위 밖이거나, 마지막 인덱스이거나, 다음 개발자가 감염자가 아니거나
 * 위 3경우 입니다.
 * 재귀함수가 끝났을 때에는 최초감염자 수인 count를 증가시켜줍니다.
 */
public class Main {
    static ArrayList<Programmer> programmers;
    static Integer count;
    static Integer R;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<Integer> betweenInfectionList = new ArrayList<>();
        ArrayList<Integer> betweenNotInfectionList = new ArrayList<>();

        // 입력 시작
        int n = Integer.parseInt(br.readLine());

        programmers = new ArrayList<>();
        count = 0;
        List<Integer> r = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Programmer programmer = new Programmer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            programmers.add(programmer);
        }
        // 입력 끝

        // Comparator.comparing()을 통해 정렬
        programmers.sort(Comparator.comparing(Programmer::getIndex));

        for (int i = 0; i < n - 1; i++) {
            Programmer p1 = programmers.get(i);
            Programmer p2 = programmers.get(i + 1);
            int tmpR;

            // 다음 개발자 위치 - 현재 개발자 위치
            if (!(p1.getInfection().equals(p2.getInfection()))) {
                tmpR = p2.getIndex() - p1.getIndex();
                betweenNotInfectionList.add(tmpR);
            }

            if (p1.getInfection().equals(p2.getInfection())) {
                tmpR = p2.getIndex() - p1.getIndex();
                betweenInfectionList.add(tmpR);
            }
        }

        // 양수거리 R 최소값 계산
        if (betweenNotInfectionList.isEmpty()) {
            R = Collections.max(betweenInfectionList);
        } else {
            R = Collections.min(betweenNotInfectionList) - 1;
        }

        int lastIndex = programmers.size() - 1;

        // 시작시 처음 개발자가 감염되었을 경우 count
        if (programmers.get(0).getInfection() == 1) {
            count++;
        }

        int index = 0;
        while (true) {
            if (index == lastIndex) {
                break;
            }

            // 감염 개발자를 찾기 위해 재귀함수 호출, 끝까지 찾은 뒤 다음 탐색 위치인 index 반환
            index = loop(index, lastIndex);

            // 마지막 개발자에 도달했을 경우 while문 탈출
            if (index == 0) {
                break;
            }

            // 다음 개발자가 감염일 경우 감염의 시작점이므로 count 증가
            if (programmers.get(index).getInfection() == 1) {
                count++;
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    public static Integer loop(int i, int lastIndex) {
        if (i + 1 > lastIndex) {
            return 0;
        }
        if (programmers.get(i + 1).getInfection() == 1) {
            if ((R >= programmers.get(i + 1).getIndex() - programmers.get(i).getIndex())) {
                return loop(i + 1, lastIndex);
            } else {
                return i + 1;
            }
        }
        return i + 1;
    }

    private static class Programmer {
        Integer index;
        Integer infection;

        public Programmer(Integer index, Integer infection) {
            this.index = index;
            this.infection = infection;
        }

        public Integer getIndex() {
            return index;
        }

        public Integer getInfection() {
            return infection;
        }
    }
}