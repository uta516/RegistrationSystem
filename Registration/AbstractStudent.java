

import java.util.*;

public abstract class AbstractStudent {
    protected int totalCredits;
    private String studentID;
    protected Map<String, Integer> registeredCourses = new HashMap<>();

    public AbstractStudent(String id, int initialCredits) {
        this.studentID = id;
        this.totalCredits = initialCredits;
    }

    public String getID() { return studentID; }
    public int getTotalCredits() { return totalCredits; }
    public Map<String, Integer> getCourseList() { return registeredCourses; }

    public abstract boolean addCourse(String name, int units);
    public abstract boolean removeCourse(String name, int units);

    @Override
    public String toString() {
        return "学籍番号:" + studentID + ", 現在の登録単位:" + totalCredits + " / " + RegistrationConfiguration.MAX_CREDITS;
    }
}