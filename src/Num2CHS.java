/**
 * Int转换为汉字对应数字
 */

import java.util.*;

public class Num2CHS {
    public static int in;
    /**数字对应字符集**/
    public static String[] chnNumSet = {"零","一","二","三","四","五","六","七","八","九"};
    /**进位对应字符集**/
    public static String[] chnCarryBit = {"","十","百","千","万"};
    public static void main(String[] args){
        System.out.println("Input an integer:");
        Scanner scanner = new Scanner(System.in);
        try {
            in = scanner.nextInt();
            Num2CHS num2CHS = new Num2CHS();
            System.out.println(num2CHS.Convert(in));
        }catch (Exception e) {
            System.out.println("Incorrect input");
        }
    }
    private String Convert(int in){
        boolean minus = false;
        StringBuilder sb = new StringBuilder();

        /**判断负数**/
        if(in<0) {
            minus=true;
            in=Math.abs(in);
        }

        String num_overall="";
        String buffer1,buffer2,buffer3="";//每四个数分开
        /**将数字存入固定size的String，位数不足补零**/
        num_overall+=in;
        int size = num_overall.length();
        for (int i=0;i<10-size;i++)
        {
            num_overall="0"+num_overall;
        }
        buffer1 = num_overall.substring(0,2);//亿
        buffer2 = num_overall.substring(2,6);//万
        buffer3 = num_overall.substring(6,10);

        /**
         * 按xx亿xx万xx的顺序转化数字
         * 当当前级不为零且下一级千位为0时补零
         **/
        sb.append(partConvert(buffer1,"亿"));
        if(Integer.parseInt(buffer1)!=0&&buffer2.startsWith("0")) sb.append("零");
        sb.append(partConvert(buffer2,"万"));
        if(Integer.parseInt(buffer2)!=0&&buffer3.startsWith("0")) sb.append("零");
        sb.append(partConvert(buffer3,""));

        /**负数处理**/
        if (minus) sb.insert(0,"负");
        return  sb.toString();
    }

    /**
     * 将千位级的数字转化成汉字
     * @param buffer 千位级别数字
     * @param bit 添加数字位：亿、万
     * @return
     */
    private String partConvert(String buffer,String bit){
        StringBuilder out = new StringBuilder();
        int bufferInt =Integer.parseInt(buffer);
        if(bufferInt==0){
            return "";
        }

        int bitPos = 0;//位数计数器
        boolean zeroFlag=false;//补零flag
        while (bufferInt>0){
            int digit = bufferInt%10;//每次取最后一位
            if(digit!=0) {
                out.insert(0, chnNumSet[digit] + chnCarryBit[bitPos]);
                zeroFlag = true;
            }
            else{
                /**在上一位非零，当前位为零时补零**/
                if (zeroFlag){
                    zeroFlag=false;
                    out.insert(0, chnNumSet[digit]);
                }
            }
            bitPos++;
            bufferInt=bufferInt/10;
        }
        out.append(bit);
        return out.toString();
    }
}
