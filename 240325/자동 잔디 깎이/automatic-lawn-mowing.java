import java.io.*;
import java.util.*;

public class Main {

    // 좌표 노드 클래스 정의
    static class Node{
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        List<Node> nodes = new ArrayList<>();
        int x = 0;
        int y = 0;
        int count = 0;
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String direction = st.nextToken();
            Integer value = Integer.parseInt(st.nextToken());

            // 노드 생성

            for (int j = 0; j < value; j++) {
                count++;

                if (direction.equals("N")) {
                    y++;
                    nodes.add(new Node(x, y, count));
                }

                if (direction.equals("E")) {
                    x++;
                    nodes.add(new Node(x, y, count));
                }

                if (direction.equals("W")) {
                    x--;
                    nodes.add(new Node(x, y, count));
                }

                if (direction.equals("S")) {
                    y--;
                    nodes.add(new Node(x, y, count));
                }
            }
        }

        for (int i = 0; i < nodes.size() - 1; i++) {
            Node node = nodes.get(i);

            for (int j = i + 1; j < nodes.size(); j++) {
                int nodeX = node.getX();
                int nodeY = node.getY();
                int nodeListx = nodes.get(j).getX();
                int nodeListy = nodes.get(j).getY();

                // x좌표 비교
                if (nodeX == nodeListx) {
                    // y좌표 비교
                    if (nodeY == nodeListy){
                        // 만약 서로 다른 두개의 좌표가 동일하다면 겹쳐졌다는 의미이므로 서로의 count를 뺀 값을 계산한다.
                        results.add(nodes.get(j).count - node.count);
                    }
                }
            }
        }

        // 만약 result에 아무 값도 없다면 겹친 부분이 없으므로 -1 출력
        if (results.size() == 0) {
            bw.write(String.valueOf(-1));
        } else {
            // 값이 존재할 경우 최소값을 출력
            bw.write(String.valueOf(Collections.min(results)));
        }

        bw.flush();
        bw.close();
    }
}