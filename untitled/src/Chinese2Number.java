import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chinese2Number {
    private String inText;//输入字符串
    ArrayList textNumber = new ArrayList();

    public Chinese2Number(String text){
        this.inText=text;
        int begin=0,end;
        int len=inText.length();
        while(begin<len) {
            end = begin + 1;
            if (begin == end) end++;
            textNumber.add(inText.substring(begin, end));
            begin = end;
        }
    }


    public boolean Rule(){
        boolean flag = true;
        //筛选非法汉字正则表达式
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
        //临近两字不能相同
        for(int i=0;i<textNumber.size()-1;i++){
            if(textNumber.get(i).equals(textNumber.get(i+1))){
                System.out.println("输入数字表达不正确,临近两字不能相同");
                flag=false;
                break;
            }
        }
        //“负”字一定在第一个，“万”、“亿不能在第一个，“负”、“万”、“亿”只能出现一次
        if(textNumber.indexOf("负")!=textNumber.lastIndexOf("负")||
                textNumber.indexOf("万")!=textNumber.lastIndexOf("万")||
                textNumber.indexOf("亿")!=textNumber.lastIndexOf("亿")||
                (textNumber.indexOf("负")!=0&&textNumber.indexOf("负")!=-1)||
                textNumber.indexOf("万")==0||textNumber.indexOf("亿")==0)
        {
            System.out.println("输入数字表达不正确,“负”字一定在第一个，“万”、“亿不能在第一个，“负”、“万”、“亿”只能出现一次");
            flag=false;
        }
        return flag;
    }
    public void splitNum(){

        if(textNumber.contains("亿")){

        }
    }
}
