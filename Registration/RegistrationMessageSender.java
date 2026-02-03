

public class RegistrationMessageSender implements RegistrationConfiguration {
    public String createMsg(String cmd, String studentID, String course) {
        return cmd + ":" + studentID + ":" + course;
    }
}