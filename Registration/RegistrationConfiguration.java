


public interface RegistrationConfiguration {
    final public int SERVER_PORT = 15000;
    final public String OK = "OK";
    final public String ABORT = "ABORT";
    final public String QUIT = "QUIT";
    final public String ESTABLISH = "establish";

    // 通信コマンド
    final public String CMD_REGISTER = "REGISTER"; // 登録
    final public String CMD_DROP = "DROP";         // 削除
    final public String CMD_LOTTERY = "LOTTERY";   // 抽選

    // 科目設定
    final public String COURSE_PROG = "プログラミング応用実習";
    final public String COURSE_STAT = "統計及び演習";
    final public String COURSE_SYS = "システムプログラミング応用実習";
    final public String COURSE_JHO = "情報工学及び演習";
    final public String COURSE_KKJ = "経営工学実験";
    final public String COURSE_SEN = "線型代数";
    final public String COURSE_OR="オペレーションズ・リサーチ";
    final public String COURSE_BTJ="物理学実験";
    final public String COURSE_AI = "人工知能論(抽選)";
    final public String COURSE_DS="データサイエンス・AI概論(抽選)";
    
    final public int UNIT_PROG = 4;
    final public int UNIT_STAT = 2;
    final public int UNIT_SYS = 4;
    final public int UNIT_JHO = 2;
    final public int UNIT_KKJ = 4;
    final public int UNIT_SEN = 2;
    final public int UNIT_OR = 2;
    final public int UNIT_BTJ = 4;
    final public int UNIT_AI = 2;
    final public int UNIT_DS = 2;

    final public int MAX_CREDITS = 47; // 履修上限
}

