package CHS2Num;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chinese2Number {
    /**输入字符串**/
    private String inText;
    /**输出结果**/
    private int out;
    private boolean minus = false;

    /***
     * 初始化输入值text
     * @param text
     */
    public Chinese2Number(String text){
        this.inText=text;
    }

    /**
     * 输入字符规则
     * 符合规则的字符串才可以继续解析，否则提示错误
     * @return 全部符合规则返回true否则返回false
     */

    public boolean Rule(){
        boolean flag = true;
        /**筛选非法汉字正则表达式**/
        String regEx ;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<Constant.chnNumSet.length;i++){
            sb.append(".*"+Constant.chnNumSet[i]+".*||");
        }
        regEx = sb.toString();

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(inText);
        if(!matcher.matches())
        {
            System.out.println("输入数字表达不正确,含非法汉字");
            flag=false;
        }
        /**临近两字不能相同**/
        for(int i=0;i<inText.length()-1;i++){
            if(inText.substring(i,i+1).equals(inText.substring(i+1,i+2))){
                System.out.println("输入数字表达不正确,临近两字不能相同");
                flag=false;
                break;
            }
        }
        /**
         * “负”字一定在第一个
         * 第一个字一定是数值或十或负
         * “负”、“万”、“亿”只能出现一次
         */
        if(inText.indexOf("负")!=inText.lastIndexOf("负")||
                inText.indexOf("万")!=inText.lastIndexOf("万")||
                inText.indexOf("亿")!=inText.lastIndexOf("亿")||
                (inText.indexOf("负")!=0&&inText.indexOf("负")!=-1)||
                !(Arrays.asList(Constant.chnNum).contains(inText.substring(0,1))||
                        inText.startsWith("十")||
                        inText.startsWith("负")))
        {
            System.out.println("输入数字表达不正确,“负”字一定在第一个，" +
                    "第一个字一定是数值，“负”、“万”、“亿”只能出现一次");
            flag=false;
        }
        /**
         * 亿要在万前面
         */
        if (inText.indexOf("亿")>inText.indexOf("万")){
            System.out.println("亿要在万前面");
            flag=false;
        }
        return flag;
    }
    public void numHandle(){
        if(inText.equals("零"))
        {
            out=0;
            System.out.println(out);
            return;
        }

        if(inText.startsWith("负")) {
            minus=true;
            inText = inText.substring(1,inText.length());
        }
        /**去掉所有零**/
        for(int i=0;i<inText.length();i++){
            if('零' == (inText.charAt(i))){
                inText = inText.substring(0, i) + inText.substring(i+1);
            }
        }
        /**从“亿”、“万”处分割数字**/
        String yiBuffer = "",wanBuffer="",buffer="";
        int k=0;
        for (int j = 0;j<inText.length();j++){

            if('亿' == (inText.charAt(j))){
                yiBuffer = inText.substring(0,j);
                k = j+1;
            }
            if('万' == (inText.charAt(j))){
                wanBuffer = inText.substring(k,j);
                k = j+1;
                break;
            }
        }
        buffer = inText.substring(k,inText.length());
        /**判断小结语法***/
        if(partRule(yiBuffer)&&partRule(wanBuffer)&&(partRule(buffer))) {
            out = parthandle(yiBuffer) * Constant.numberMap.get("亿") +
                    parthandle(wanBuffer) * Constant.numberMap.get("万") +
                    parthandle(buffer);
            if(minus) out = out = 0-out;
            System.out.println(out);
        }


    }

    /**
     * 小结的语法规则
     * @param part 小结文字
     * @return 本小节是否符合规则
     */
    private boolean partRule(String part){
        if(part.equals("")) return true;
        /**
         * 第一个字一定是数值或十
         */
        if(!(Arrays.asList(Constant.chnNum).contains(part.substring(0,1))||part.startsWith("十"))){
            System.out.println("错误的输入语法1");
            return false;
        }
        /**权位不能挨着,数位不能挨着**/
        for (int i = 0;i<part.length()-1;i++){
            if(Arrays.asList(Constant.chnUnitSection).contains(part.substring(i,i+1))&&
                    Arrays.asList(Constant.chnUnitSection).contains(part.substring(i+1,i+2))){
                System.out.println("错误的输入语法1");
                return false;
            }
            if(Arrays.asList(Constant.chnNum).contains(part.substring(i,i+1))&&
                    Arrays.asList(Constant.chnNum).contains(part.substring(i+1,i+2))){
                System.out.println("错误的输入语法2");
                return false;
            }
        }
        /**权位需要按着“千”，“百”，“十”的顺序**/
        if(part.indexOf("十")>0){
            if(!(part.indexOf("千")<part.indexOf("十")&&part.indexOf("百")<part.indexOf("十"))){
                System.out.println("错误的输入语法3");
                return false;
            }
        }
        if(part.indexOf("百")>0){
            if(!(part.indexOf("千")<part.indexOf("百"))){
                System.out.println("错误的输入语法4");
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param part
     * @return
     */
    private int parthandle(String part){
        int result = 0;
        if(part.startsWith("十")){
            part="一"+part;
        }
        int sectionNum = 0;
        for(int i=0;i<part.length();i++){
            int v = Constant.numberMap.get(part.substring(i,i+1));
            if( v == 10 || v == 100 || v == 1000 ){//如果数值是权位则相乘
                sectionNum = v * sectionNum;
                result = result + sectionNum;
            }else if(i == part.length()-1){
                result = result + v;
            }else{
                sectionNum = v;
            }
        }
        return result;
    }
}
