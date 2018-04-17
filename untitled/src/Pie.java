public class Pie {
    public static void main(String []args) {
        System.out.println("Hello World!");
        Pie newPie =new Pie();
        newPie.outPie();
    }
    public void outPie(){
        int k0,k1,k2,a0,a1,b0,b1,d0,d1;
        k0=2;a0=4;b0=1;a1=12;b1=4;
        while (true){
            k0=k0+1;k1=k0*k0;k2=2*k0+1;
            a1=k1*a0+k2*a1;b1=k1*b0+k2*b1;
            a0=a1;b0=b1;
            d0=a0/b0;d1=a1/b1;
            while (d0==d1){
                System.out.print(d0);
                a0=10*(a0%b0);a1=10*(a1%b1);
                d0=a0/b0;d1=a1/b1;
            }
        }
    }
}
