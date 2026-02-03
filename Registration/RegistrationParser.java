
public class RegistrationParser implements RegistrationConfiguration {
    public String parse(String input) {
        // 簡易的なプロトコル解釈
        if (input.contains(CMD_REGISTER)) return OK;
        if (input.contains(QUIT)) return QUIT;
        return OK;
    }
}