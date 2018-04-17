import java.util.Scanner;

public class NumberConvert {
    public static void main(String []args) {

        System.out.println("请输入汉语数字，最大二十一亿：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String number = str.replaceAll(" ", "");//去输入空格
        //scanner.close();
        if(!number.equals("quit")) {//输入quit退出
            CheckChinese checkChinese = new CheckChinese();
            if (checkChinese.checkNameChese(number)) {
                try {
                    //BiMaxSegment bimax = new BiMaxSegment(2);
                    Chinese2Number c2n = new Chinese2Number(number);
                    c2n.Rule();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else {
                System.out.println("输入存在非法字符，只能输入汉字");
            }
            NumberConvert numberConvert = new NumberConvert();
            NumberConvert.main(null);
        }
    }
}
