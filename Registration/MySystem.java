
public class MySystem {
    private static AbstractStudent currentStudent;

    public static void init(AbstractStudent s) {
        currentStudent = s;
    }

    public static AbstractStudent getStudent() {
        return currentStudent;
    }
}