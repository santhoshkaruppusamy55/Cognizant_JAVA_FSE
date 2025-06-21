package Week_1_Design_patterns_and_principles;

class Single_ton{
    private static Single_ton instance;

    private Single_ton(){
    }

    public static Single_ton getInstance() {
        if(instance==null){
            instance=new Single_ton();
        }
        return instance;
    }

    public  void statement(String s){
        System.out.println("instance created"+" "+s);
    }
}

public class singleton{
    public static void main(String[] args) {
        Single_ton log1=Single_ton.getInstance();
        log1.statement("user1");
        Single_ton log2=Single_ton.getInstance();
        log2.statement("user2");
        if(log2==log1){
            System.out.println("Both instance are equal");
        }
        else{
            System.out.println("Both instance are not equal");
        }
    }
}