package life.ycz.community.controller;

public class Test {
    public static void main(String[] args) {
        System.out.println(A.QUESTION);
    }
}
enum  A{
    QUESTION("QQQQQQQ");
    private String message;
    A(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

enum Day {
    MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
