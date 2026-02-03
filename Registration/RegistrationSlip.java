
public class RegistrationSlip {
    public void printSlip(String subject, int units, String comment) {
        System.out.println("---- 履修登録確認書 ----");
        System.out.println("処理内容: " + subject);
        System.out.println("単位数: " + units);
        System.out.println("備考: " + comment);
        System.out.println("-----------------------");
    }
}
