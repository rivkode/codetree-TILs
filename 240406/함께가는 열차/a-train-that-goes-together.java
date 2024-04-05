import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Train> trainList;
    static Integer count;

    /**
     * 핵심 로직
     *
     * i 번째 위치한 열차가 i+1 번째 이후로의 모든 열차에 대해서 부딪힘이 발생하는가 ?
     * 발생한다면 그 열차는 remove
     * 한 번도 부딪히지 않는다면 그 열차는 독립적이므로 pass
     *
     * 위 과정을 처음부터 끝까지 for문을 돌며 반복한다.
     *
     * 최종 결과는 trainList.size() - count
     * 왜냐하면 count된 만큼 열차가 합쳐지므로
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        trainList = new ArrayList<>();
        count = 0;

        // 입력 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Train train = new Train(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            trainList.add(train);
        }
        
        // 입력 끝

        for (int i=0; i<n; i++) {
            for (int j=i + 1; j<n; j++) {
                // i번째 열차 기준 i 위치 이후의 모든 열차에 대해서 부딪힘이 발생하는가 체크
                if (trainList.get(i).getSpeed() > trainList.get(j).getSpeed()) {
                    count++;
                    break;
                }
            }
        }

        bw.write(String.valueOf(n - count));
        bw.flush();
        bw.close();
    }

    public static class Train {
        Integer index;
        Integer speed;

        public Train(Integer index, Integer speed) {
            this.index = index;
            this.speed = speed;
        }

        public Integer getTrain() {
            return index;
        }

        public Integer getSpeed() {
            return speed;
        }
    }
}