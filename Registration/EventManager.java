
import java.util.*;

public class EventManager {
    public static void fireEvent(String target, Object data) {
        System.out.println("Event to " + target + ": " + data.toString());
    }
}