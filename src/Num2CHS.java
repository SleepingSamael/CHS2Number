import java.util.*;

public class Num2CHS {
    public static int in;
    private static Hashtable<Integer,String > numberTable = new Hashtable<Integer,String>();
    static {
        numberTable.put(0,"零");
        numberTable.put(1,"一");
        numberTable.put(2,"二");
        numberTable.put(3,"三");
        numberTable.put(4,"四");
        numberTable.put(5,"五");
        numberTable.put(6,"六");
        numberTable.put(7,"七");
        numberTable.put(8,"八");
        numberTable.put(9,"九");
    }
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
        String in_str = in+"";
        boolean minus = false;
        StringBuilder sb = new StringBuilder();

        if (in_str.length() == 0) return "no input";

        /**判断负数**/
        if(in_str.startsWith("-")) {
            minus=true;
            in_str=in_str.substring(1,in_str.length());
        }
        /**反转存入Arraylist**/
        ArrayList numlist = new ArrayList();
        for (int i = in_str.length()-1;i>=0;i--)
        {
            numlist.add(in_str.charAt(i));
        }

        /****/
        Iterator<String> iterator = numlist.iterator();
        while(iterator.hasNext()){
            String key = (String)iterator.next();
            int value = numberTable.get(key);
            System.out.println("keySet:"+key+" "+value);
        }


        return  sb.toString();
    }
}
