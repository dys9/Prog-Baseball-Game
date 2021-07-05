package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Baseball {
    private final static String ENDING_MESSAGE =
            "3개의 숫자를 모두 맞추셨습니다. ! 게임 종료 " +
            "\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private final static String INPUT_MESSAGE = "숫자를 입력해주세요 : ";
    private final static String STR_STRIKE = " 스트라이크 ";
    private final static String STR_BALL = " 볼 ";
    private final static String STR_NOTHING = " 낫씽";

    private final static int INPUT_SIZE = 3;
    private final static int NUM_MAX = 9;
    private final static int NUM_MIN = 1;

    private final Scanner Sc;
    private final int [] Answer;    // 랜덤으로 생성된 정답
    private int [] Input;           // 사용자 입력

    /* Constructor */
    public Baseball(Scanner scanner){
        this.Answer = getRandNum();
        this.Sc = scanner;
    }

    public void setInput(){
        while (true){
            System.out.print(INPUT_MESSAGE);
            String strInput = Sc.next();
            if (strInput.length() == INPUT_SIZE) {
                this.Input = Stream.of(strInput.split("")).mapToInt(Integer::parseInt).toArray();
                break;
            }
        }
    }

    public boolean isAnswer(){
        int Strike = getStrike();
        int Ball = getBall();

        if (Strike > 0 && Strike < INPUT_SIZE) {// Strike
            System.out.println(Strike + STR_STRIKE);
        }
        if (Ball != 0) {                        // Ball
            System.out.println(Ball + STR_BALL);
        }

        if (Strike == 0 && Ball == 0) {         // Nothing
            System.out.println(STR_NOTHING);
        } else if (Strike == INPUT_SIZE){       // Answer
            System.out.println(ENDING_MESSAGE);
            return false;
        }
        return true;
    }

    private int[] getRandNum(){
        int cnt = 0;
        int [] numbers = new int[INPUT_SIZE];

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        boolean[] flag = new boolean[NUM_MAX]; // False Array

        while (cnt < INPUT_SIZE) {
            int idx = random.nextInt(NUM_MAX - 1) + NUM_MIN; // Range 1 ~ 9
            if (!flag[idx]) { // isFalse?
                numbers[cnt] = idx;
                flag[numbers[cnt]] = true;
                cnt++;
            }
        }
        return numbers;
    }

    private int getStrike(){
        int Strike = 0;
        for (int i = 0; i < INPUT_SIZE; i++)
        {
            if (Answer[i] == Input[i]) {
                Strike++;
            }
        }
        return Strike;
    }

    private int getBall(){
        int Ball = 0;
        for (int i = 0; i < INPUT_SIZE; i++)
        {
            int finalI = i;
            /*  isInAnswer AND isNotSame -> BALL++  */
            if ((Arrays.stream(Answer).anyMatch(x -> x == Input[finalI])) &&
                    (Input[i] != Answer[i])) {
                Ball += 1;
            }
        }
        return  Ball;
    }
}