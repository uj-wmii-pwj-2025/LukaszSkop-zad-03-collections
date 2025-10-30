package uj.wmii.pwj.collections;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;


public class brainFuckImplementation implements Brainfuck {
    private final String program;
    private final PrintStream out;
    private final InputStream in;
    private final byte[] arr;
    private int pointer = 0;

    public brainFuckImplementation(String program, PrintStream out, InputStream in, int stackSize) {
        this.program = program;
        this.out = out;
        this.in = in;
        this.arr = new byte[stackSize];


    }


    @Override
    public void execute() {
        int cnt = 0;


        while (cnt < program.length()) {
            char c = program.charAt(cnt);
            if (c == '>') {
                pointer++;

            } else if (c == '<') {
                pointer--;
            } else if (c == '+') {
                arr[pointer]++;

            } else if (c == '-') {
                arr[pointer]--;

            } else if (c == '.') {
                out.print((char) (arr[pointer]));

            } else if (c == ',') {
                try {
                    int input = in.read();
                    arr[pointer] = (byte) (input);

                } catch (IOException e) {
                    arr[pointer] = 0;

                }

            } else if (c == '[') {

                if (arr[pointer] == 0) {
                    int depth = 1;
                    while (depth > 0 && ++cnt < program.length()) {
                        if (program.charAt(cnt) == '[') depth++;
                        else if (program.charAt(cnt) == ']') depth--;
                    }
                }
            } else if (c == ']') {
                if (arr[pointer] != 0) {
                    int depth = 1;
                    while (depth > 0 && --cnt >= 0) {
                        if (program.charAt(cnt) == ']') depth++;
                        else if (program.charAt(cnt) == '[') depth--;
                    }
                }
            }


            cnt++;
        }


    }
}