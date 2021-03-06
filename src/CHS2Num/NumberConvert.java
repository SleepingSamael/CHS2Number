package CHS2Num;

import java.util.Scanner;

public class NumberConvert {
    public static void main(String []args) {
        System.out.println("请输入汉语数字，最大九千九百九十九亿九千九百九十九万九千九百九十九：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        /**去除输入空格**/
        String number = str.replaceAll(" ", "");
        //scanner.close();

        /**输入quit退出**/
        if(!number.equals("quit")) {
            CheckChinese checkChinese = new CheckChinese();
            if (checkChinese.checkNameChese(number)) {

                    //CHS2Num.BiMaxSegment bimax = new CHS2Num.BiMaxSegment(2);
                    Chinese2Number c2n = new Chinese2Number(number);
                    if(c2n.Rule()) c2n.numHandle();

            } else {
                System.out.println("输入存在非法字符，只能输入汉字");
            }
            NumberConvert.main(null);
        }
    }
}
