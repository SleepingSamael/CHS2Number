import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BiMaxSegment {

    /**最大分词长度**/
    private int max_len;
    /**词典**/
    private Set dict = Constant.dict;

    /****
     * 初始化max_len和词典
     * @param max_len
     */
    public BiMaxSegment(int max_len){
        this.max_len=max_len;
    }

    /**
     * 读取词典
     * @param dictPath 词典文件路径
     * @param charset 词典文件编码
     * @return 词典Set
     */
    private Set<String> initDict(String dictPath,String charset){
        Set<String> dict=new HashSet<String>();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(dictPath),charset));
            String s;
            //一行一行地读取文本内容
            while((s=br.readLine())!=null){
                //只读取词
                dict.add(s.split(",")[0]);
            }
            br.close();
        }catch (IOException ex) {
            Logger.getLogger(BiMaxSegment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dict;
    }

    /**
     * 正向最大匹配算法
     * @param text 要分词的文本内容
     * @return 分词结果
     */
    public String mm_segment(String text){
        StringBuilder sb=new StringBuilder();
        ArrayList textNumber = new ArrayList();
        int begin=0,end;
        int len=text.length();
        while(begin<len){
            end=begin+max_len;
            if(end>len)end=len;
            //不匹配则指针前移
            while(begin<end&&!dict.contains(text.substring(begin,end))){
                end--;
            }
            //一个字
            if(begin==end)end++;
            sb.append(text.substring(begin,end)+"/");
            textNumber.add(text.substring(begin,end));
            System.out.println(textNumber);
            begin=end;
        }
        return sb.toString();
    }

    /**
     * 反向最大匹配算法
     * @param text 要分词的文本内容
     * @return 分词结果
     */
    public String rmm_segment(String text){
        StringBuilder sb=new StringBuilder();
        int right=text.length();
        int left;
        while(right>0){
            left=right-max_len;
            if(left<0)left=0;
            //不匹配则指针后移
            while(right>left&&!dict.contains(text.substring(left,right))){
                left++;
            }
            //一个字
            if(right==left)left--;
            sb.insert(0,text.substring(left,right)+"/");
            right=left;
        }
        return sb.toString();
    }

}