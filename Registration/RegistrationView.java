

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;
import java.util.Random;

public class RegistrationView extends JFrame implements ActionListener, EventListener, RegistrationConfiguration {
    JTextArea infoArea;
    JButton regButton, dropButton, lotteryButton, statusButton;
    JLabel statusLabel;

    public RegistrationView() {
        setTitle("履修登録システム");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        infoArea = new JTextArea();
        infoArea.setEditable(false); // ログエリアを編集不可に
        add(new JScrollPane(infoArea), BorderLayout.CENTER);

        // ボタンパネル（1行4列）
        JPanel p = new JPanel(new GridLayout(1, 4));
        regButton = new JButton("科目登録");
        dropButton = new JButton("履修辞退");
        lotteryButton = new JButton("抽選申請");
        statusButton = new JButton("状況確認");

        regButton.addActionListener(this);
        dropButton.addActionListener(this);
        lotteryButton.addActionListener(this);
        statusButton.addActionListener(this);

        p.add(regButton); 
        p.add(dropButton); 
        p.add(lotteryButton); 
        p.add(statusButton);
        add(p, BorderLayout.SOUTH);

        // 上部のステータス表示
        statusLabel = new JLabel("単位数: 0 / 24");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(new Font("MS ゴシック", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);
        
        setVisible(true);
        updateDisplay();
    }

    // 画面表示の更新
    public void updateDisplay() {
        statusLabel.setText("現在の合計単位数: " + MySystem.getStudent().getTotalCredits() + " / " + MAX_CREDITS);
        if (MySystem.getStudent().getTotalCredits() >= MAX_CREDITS) {
            statusLabel.setForeground(Color.RED); // 上限に達したら赤文字に
        } else {
            statusLabel.setForeground(Color.BLACK);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // --- 1. 科目登録（選択式に修正） ---
        if (e.getSource() == regButton) {
            // 選択肢のリストを作成
            String[] courses = {COURSE_PROG, COURSE_STAT,COURSE_SYS,COURSE_JHO,COURSE_KKJ,COURSE_SEN,COURSE_OR,COURSE_BTJ,COURSE_AI,COURSE_DS}; 
            
            String selected = (String) JOptionPane.showInputDialog(
                this, 
                "登録する科目を選択してください", 
                "科目登録メニュー", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                courses, 
                courses[0]
            );

            if (selected != null) {
                // 科目に応じた単位数を判定
                int units = 0;
                if (selected.equals(COURSE_PROG)) units = UNIT_PROG;
                else if (selected.equals(COURSE_STAT)) units = UNIT_STAT;
                else if (selected.equals(COURSE_SYS)) units = UNIT_SYS;
                else if (selected.equals(COURSE_JHO)) units = UNIT_JHO;
                else if (selected.equals(COURSE_KKJ)) units = UNIT_KKJ;
                else if (selected.equals(COURSE_SEN)) units = UNIT_SEN;
                else if (selected.equals(COURSE_OR)) units = UNIT_OR;
                else if (selected.equals(COURSE_BTJ)) units = UNIT_BTJ;
                

                // システムに登録
                if (MySystem.getStudent().addCourse(selected, units)) {
                    infoArea.append("[成功] " + selected + " (" + units + "単位) を登録しました。\n");
                } else {
                    infoArea.append("[エラー] 単位上限(" + MAX_CREDITS + ")を超えるため登録できません。\n");
                }
            }
        }

        // --- 2. 履修辞退（選択式） ---
        if (e.getSource() == dropButton) {
            Object[] currentCourses = MySystem.getStudent().getCourseList().keySet().toArray();
            
            if (currentCourses.length == 0) {
                JOptionPane.showMessageDialog(this, "現在登録されている科目はありません。");
            } else {
                String selected = (String) JOptionPane.showInputDialog(
                    this, "辞退する科目を選択してください", "履修辞退",
                    JOptionPane.WARNING_MESSAGE, null, currentCourses, currentCourses[0]);

                if (selected != null) {
                    int units = MySystem.getStudent().getCourseList().get(selected);
                    MySystem.getStudent().removeCourse(selected, units);
                    infoArea.append("[辞退] " + selected + " を削除しました。\n");
                }
            }
        }

        // --- 3. 抽選申請（赤・青表示） ---
        if (e.getSource() == lotteryButton) {
            String[] lotCourses = {COURSE_AI,COURSE_DS};
            String selected = (String) JOptionPane.showInputDialog(this, "抽選申請する科目", "抽選", 
                                JOptionPane.QUESTION_MESSAGE, null, lotCourses, lotCourses[0]);
            
            if (selected != null) {
                Random rand = new Random();
                double result = rand.nextDouble();
                
                StringBuilder html = new StringBuilder("<html><body style='width:200px; text-align:center;'>");
                if (result > 0.5) { // 50%当選
                    if (MySystem.getStudent().addCourse(selected, UNIT_AI)) {
                        html.append("<h2 style='color:red;'>当選！</h2>")
                            .append("<p><b>").append(selected).append("</b></p>")
                            .append("<p>履修が確定しました。</p>");
                        infoArea.append("[当選] " + selected + " を登録しました。\n");
                    }else if (MySystem.getStudent().addCourse(selected, UNIT_DS)) {
                        html.append("<h2 style='color:red;'>当選！</h2>")
                            .append("<p><b>").append(selected).append("</b></p>")
                            .append("<p>履修が確定しました。</p>");
                        infoArea.append("[当選] " + selected + " を登録しました。\n");
                    } else {
                        html.append("<h2 style='color:orange;'>当選ですが...</h2>")
                            .append("<p>単位上限のため登録できませんでした。</p>");
                    }
                } else {
                    html.append("<h2 style='color:blue;'>落選</h2>")
                        .append("<p>選外となりました。他科目を検討してください。</p>");
                    infoArea.append("[落選] " + selected + " の抽選に外れました。\n");
                }
                html.append("</body></html>");
                JOptionPane.showMessageDialog(this, html.toString(), "抽選結果", JOptionPane.PLAIN_MESSAGE);
            }
        }

        // --- 4. 状況確認 ---
        if (e.getSource() == statusButton) {
            String status = MySystem.getStudent().toString() + "\n登録科目: " + MySystem.getStudent().getCourseList().keySet();
            JOptionPane.showMessageDialog(this, status, "現在の履修状況", JOptionPane.INFORMATION_MESSAGE);
        }

        updateDisplay();
    }

    public static void main(String[] args) {
        // デバッグ用起動設定
        MySystem.init(new GeneralStudent("B2026-STUDENT", 0));
        new RegistrationView();
    }
}