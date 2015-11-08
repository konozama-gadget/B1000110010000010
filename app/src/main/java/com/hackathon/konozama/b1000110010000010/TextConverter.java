package com.hackathon.konozama.b1000110010000010;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大輔 on 2015/11/08.
 */
public class TextConverter {
    /* 日本語から2進数文字列に変換 */
    public static String JpToBin(String jp_text, String enc) {
        String res = "";

        try {
            byte data[] = jp_text.getBytes(enc);
            for (byte b : data) {
                res += Integer.toBinaryString(b);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static String JpToBin(String jp_text) {
        String res = "";

        try {
            byte data[] = jp_text.getBytes("UTF-8");
            for (byte b : data) {
                res += Integer.toBinaryString(b);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return res;
    }

    /* 2進数文字列から日本語に変換 */
    public static String BinToJp(String bin_text) {
        String res = "";
        String tmp = bin_text;
        int count = 0;

        List<String> data = new ArrayList<String>();
        for (int i = 0; i < bin_text.length(); i++) {
            // 1バイトコードの場合
            if ("0".equals(tmp.substring(i, 1))) {
                data.add(count, tmp.substring(i, 8 * 1));
                count++;
                i = i + 8 * 1;
            }
            // 2バイトコードの場合
            else if ("10".equals(tmp.substring(i, 2))) {
                data.add(count, tmp.substring(i, 8 * 2));
                count++;
                i = i + 8 * 2;
            }
            // 3バイトコードの場合
            else if ("110".equals(tmp.substring(i, 3))) {
                data.add(count, tmp.substring(i, 8 * 3));
                count++;
                i = i + 8 * 3;
            }
            // 4バイトコードの場合
            else if ("1110".equals(tmp.substring(i, 4))) {
                data.add(count, tmp.substring(i, 8 * 4));
                count++;
                i = i + 8 * 4;
            }
            Log.d("01debug", data.get(count));
        }
        return res;
    }
}
