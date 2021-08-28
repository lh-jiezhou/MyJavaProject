package com.lh.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 全组合 https://www.cnblogs.com/lifegoesonitself/p/3225803.html
 *  基本思路：求全组合，则假设原有元素n个，则最终组合结果是2^n个。原因是：
 * 用位操作方法：假设元素原本有：a,b,c三个，则1表示取该元素，0表示不取。故去a则是001，取ab则是011.
 * 所以一共三位，每个位上有两个选择0,1.所以是2^n个结果。
 * 这些结果的位图值都是0,1,2....2^n。所以可以类似全真表一样，从值0到值2^n依次输出结果：即：
 * 000,001,010,011,100,101,110,111 。对应输出组合结果为：
 * 空,a,b,ab,c,ac,bc,abc.
 * 这个输出顺序刚好跟数字0~2^n结果递增顺序一样
 * 取法的二进制数其实就是从0到2^n-1的十进制数
 */
public class Combination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        List<String> res = allCombination2(str);

        System.out.println(res);

    }

    public static List<String> allCombination1(String str) {

        List<String> res = new LinkedList<>();
        int n = str.length();
        //求出位图全组合的结果个数：2^n
        // “<<” 表示 左移:各二进位全部左移若干位，高位丢弃，低位补0。:即求出2^n=2Bit。
        int nbit = 1<<n;
        System.out.println("全组合结果个数为："+nbit);
        //结果有 nbit 个。输出结果从数字小到大输出：即输出0,1,2,3,....2^n。
        for(int i=0 ;i<nbit ; i++) {
            StringBuffer sb = new StringBuffer();
            //每个数二进制最多可以左移n次，即遍历完所有可能的变化新二进制数值了
            for(int j=0; j<n ; j++) {
                int tmp = 1<<j ;
                // & 表示与。两个位都为1时，结果才为1
                if((tmp & i)!=0) {
                    sb.append(str.charAt(j));
                }
            }
            res.add(sb.toString());
        }
        return res;
    }

    public static List<String> allCombination2(String str) {
        /** 全组合：
         * 思路是利用二进制的特性，每次加1即可遍历所有位的不同情况，很好理解 代码同上
         */
        List<String> res = new LinkedList<>();
        int all = str.length();
        int nbit = 1 << all;
        for (int i = 0; i < nbit; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < all; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(str.charAt(j));
                }
            }
            res.add(sb.toString());
        }
        return res;
    }
}
