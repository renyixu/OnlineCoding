package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/20 22:51
 */

/**
 * 题目描述：
 * 输入：
 * 分隔符
 * 要输入数据的行数n
 * n行数据，数据格式:id,name
 * 输出：
 * 输出数据的行数m
 * 被分割的数据：输出数据格式：id,name|
 *
 * 举例：
 * 输入：
 * *
 * 8
 * 1,aa
 * 2,bb
 * 3,cc
 * 4,*
 * 5,*
 * 6,dd
 * 7,*
 * 8,*
 * 输出：
 * 2
 * 1,aa|2,bb|3,cc
 * 6,dd
 */
public class HuaWei01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String split = s.next(); //分隔符
        int n = s.nextInt(); //输入数据的行数
        String ss = "";
        List<P> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            String str[] = s.next().split(",");
            list.add(new P(Integer.parseInt(str[0]), str[1]));
            ss += str[1];
        }
        int count = 0;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).name.equals(split)) {
                if (i > 0 && !list.get(i - 1).name.equals(split))
                    ++count;
            }
        }
        if (!list.get(list.size() - 1).equals(split))
            System.out.println(count);
        else
            System.out.println(count + 1);
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).name.equals(split))
                continue;
            if (i != list.size() - 1) {
                if (!list.get(i + 1).name.equals(split))
                    System.out.print(list.get(i) + "|");
                else {
                    System.out.println(list.get(i));
                }
            } else {
                if (!list.get(i).name.equals(split))
                    System.out.print(list.get(i));
            }
        }
    }

    static class P {
        int id;
        String name;

        public P(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return this.id + "," + this.name;
        }
    }
}
