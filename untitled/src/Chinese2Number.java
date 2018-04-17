import java.util.ArrayList;

public class Chinese2Number {
    public void Rule(String text){
        ArrayList textNumber = new ArrayList();
        int begin=0,end;
        int len=text.length();
        while(begin<len) {
            end = begin + 1;
            if (begin == end) end++;
            textNumber.add(text.substring(begin, end));
            System.out.println(textNumber);
            begin = end;
        }
    }
}
