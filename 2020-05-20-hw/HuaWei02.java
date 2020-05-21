package LeetCode;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/19 17:14
 */

/**
 * 题目描述：
 * 输出一个有向图中的环路，该有向图至多有一个环路，如果无环路则输出"-"
 * 输入：有向图的字符串表示
 * 输出：有向图环路顶点，以最小的顶点作为环路的开始
 * <p>
 * 举例：
 * 输入：
 * A->B;B->D;C->B;C->E;D->C
 * 输出：
 * BDC
 */
public class HuaWei02 {
    static HashMap<Character, Integer> map = new HashMap<>();
    static VNode[] vList = new VNode[10];
    static TreeSet<Character> treeSet = new TreeSet<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.next();
        String[] strArray;
        if (str.contains(";"))
            strArray = str.split(";");
        else {
            strArray = new String[1];
            strArray[0] = str;
        }

        for (int i = 65; i <= 74; ++i) {
            vList[i - 65] = new VNode((char) i, null);
            map.put(Character.valueOf((char) i), 0);
        }

        for (String ss : strArray) {
            char[] ch = ss.toCharArray();
            for (VNode vv : vList) {
                if (vv.node == ch[0]) {
                    if (vv.firstArc == null) {
                        vv.firstArc = new Arc(ch[3], null);
                    } else {
                        Arc a = vv.firstArc;
                        while (a.nextArc != null)
                            a = a.nextArc;
                        a.nextArc = new Arc(ch[3], null);
                    }
                }
            }
        }

        //dfs
        for (int i = 0; i < 10; ++i) {
            //把所有点清空
            for (Character c : map.keySet())
                map.put(c, 0);
            dfs(vList[i], vList[i].node);
        }
        if (treeSet.size() > 0) {
            char ch = treeSet.first();
            //打印环路
            printHuanlu(ch);
        } else
            System.out.println("-");
//        dfs(vList[0]);
    }

    private static void printHuanlu(char ch) {
        System.out.print(ch);
        //寻找一个既在treeSet集合也是ch邻边的点
        Arc arc = vList[(int) ch - 65].firstArc;
        while (arc != null) {
            if (treeSet.contains(arc.node) && arc.node != treeSet.first()) {
                printHuanlu(arc.node);
            }
            arc = arc.nextArc;
        }
    }

    private static void dfs(VNode vNode, char n) {
        map.put(vNode.node, 1);
//        System.out.println(vNode.node);
        Arc arc = vNode.firstArc;
        while (arc != null) {
            if (map.get(arc.node) == 0) {
                dfs(vList[(int) (arc.node - 65)], n);
            } else {
                if (arc.node == n) {
//                    System.out.println(arc.node);
                    treeSet.add(arc.node);
                    return;
                }
            }
            arc = arc.nextArc;
        }
    }

    //边表
    static class VNode {
        char node;
        Arc firstArc;

        public VNode(char node, Arc firstArc) {
            this.node = node;
            this.firstArc = firstArc;
        }
    }

    //顶点表
    static class Arc {
        char node;
        Arc nextArc;

        public Arc(char node, Arc nextArc) {
            this.node = node;
            this.nextArc = nextArc;
        }
    }
}
