package com.jszczygiel.sortedlistexample.ui.utils;

import java.util.Random;

public class RandomNumeric {
    private static final char[] symbols;
    private final Random random = new Random();
    private final char[] buf;

    public RandomNumeric(int length) {
        if(length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        } else {
            this.buf = new char[length];
        }
    }

    public String nextString() {
        for(int idx = 0; idx < this.buf.length; ++idx) {
            this.buf[idx] = symbols[this.random.nextInt(symbols.length)];
        }

        return new String(this.buf);
    }

    static {
        StringBuilder tmp = new StringBuilder();

        char ch;
        for(ch = 48; ch <= 57; ++ch) {
            tmp.append(ch);
        }

        symbols = tmp.toString().toCharArray();
    }
}