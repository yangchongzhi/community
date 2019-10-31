package life.ycz.community.controller;

public class Test {
    public static void main(String[] args) {
        B b = new B();
        b.setMessage(null);
        System.out.println(b.getMessage());
    }
}
interface A{
    String message = "aaaaaa";
}

class B implements A{
    private  String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}