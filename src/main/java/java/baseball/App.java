/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package main.java.java.baseball;

import java.util.InputMismatchException;
import java.util.Scanner;


public class App {
    static Scanner sc = new Scanner(System.in);
    static int strikeCount;
    static int ballCount;
    static boolean needNewInput = true;
    static final int STRIKE_OUT = 3;
    static final int MAX_INPUT_LENGTH = 3;
    static final int PLAY_MORE = 1;
    static final int TERMINATE = 2;

    public static String createRandomNum() {
        StringBuilder randomNum = new StringBuilder();
        while (randomNum.length() < MAX_INPUT_LENGTH) {
            String oneDigit = String.valueOf((int) (Math.random() * 9 + 1));
            if (!randomNum.toString().contains(oneDigit)) {
                randomNum.append(oneDigit);
            }
        }
        return randomNum.toString();
    }

    public static void calculate(String random, String myNum) {
        strikeCount = 0;
        ballCount = 0;
        for (int i = 0; i < random.length(); i++) {
            if (random.charAt(i) == myNum.charAt(i)) {
                strikeCount += 1;
            } else if (random.contains(myNum.substring(i, i + 1))) {
                ballCount += 1;
            }
        }
    }

    public static void printResult() {
        StringBuilder result = new StringBuilder();
        if (strikeCount == STRIKE_OUT) {
            needNewInput = false;
            return;
        }
        if (strikeCount + ballCount > 0) {
            if (strikeCount > 0) {
                result.append(strikeCount).append(" 스트라이크 ");
            }
            if (ballCount > 0) {
                result.append(ballCount).append(" 볼 ");
            }
            System.out.println(result);
        } else {
            System.out.println("낫싱");
        }
    }

    public static void play() {
        String randomNum = createRandomNum();
        needNewInput = true;
        while (needNewInput) {
            System.out.println("숫자를 입력해 주세요.");
            try {
                String myNum = String.valueOf(sc.nextInt());
                calculate(randomNum, myNum);
                printResult();
            } catch (InputMismatchException ime) {
                ime.printStackTrace();
                System.out.println("---------------숫자만 입력가능합니다.---------------");
                sc.next();  //Scanner 버그 방지용 코드
            }
        }
    }

    public static void main(String[] args) {
        int key;
        do {
            play();
            System.out.println("3개의 숫자를 맞히셧습니다! 게임 종료.");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            key = sc.nextInt();
        } while (key != TERMINATE);
        sc.close();
    }
}
