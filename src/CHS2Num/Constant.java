package CHS2Num;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Constant {
    /**字集**/
    public static String[] chnNumSet = {"负","零","一","二","三","四","五","六","七","八","九","十","百","千","万","亿"};
    /**数字位**/
    public static String[] chnNum = {"零","一","二","三","四","五","六","七","八","九"};
    /**权位**/
    public static String[] chnUnitSection = {"十","百","千"};
    /**映射表**/
    public static HashMap<String,Integer> numberMap = new HashMap();
    static {
        numberMap.put("零",0);
        numberMap.put("一",1);
        numberMap.put("二",2);
        numberMap.put("两",2);
        numberMap.put("三",3);
        numberMap.put("四",4);
        numberMap.put("五",5);
        numberMap.put("六",6);
        numberMap.put("七",7);
        numberMap.put("八",8);
        numberMap.put("九",9);
        numberMap.put("十",10);
        numberMap.put("百",100);
        numberMap.put("千",1000);
        numberMap.put("万",10000);
        numberMap.put("亿",100000000);
    }

    /**分词词典**/
    public static Set dict = new HashSet();{
        dict.add("负");
        dict.add("零");
        dict.add("一");
        dict.add("二");
        dict.add("两");
        dict.add("三");
        dict.add("四");
        dict.add("五");
        dict.add("六");
        dict.add("七");
        dict.add("八");
        dict.add("九");
        dict.add("十");
        dict.add("百");
        dict.add("千");
        dict.add("万");
        dict.add("十万");
        dict.add("百万");
        dict.add("千万");
        dict.add("亿");
        dict.add("十亿");
    }

}
