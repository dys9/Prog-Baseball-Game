package com.company;
import java.util.Scanner;

public class Main {
    private final static int RESTART_CODE = 1;
    private final static int EXIT_CODE = 2;

    public static void main(String[] args) {
	// Write your code here
        Scanner sc = new Scanner(System.in);

        do{
            Baseball baseball = new Baseball(sc);
            do {
                baseball.setInput();
            }while(baseball.isAnswer());
        }while (isReStart(sc));
    }

    private static boolean isReStart(Scanner sc){
        while (true) {
            int input= sc.nextInt();
            if (input == RESTART_CODE) {
                return true;
            } else if (input == EXIT_CODE) {
                return false;
            }
        }
    }
}
