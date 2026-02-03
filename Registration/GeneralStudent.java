
public class GeneralStudent extends AbstractStudent {
    public GeneralStudent(String id, int initialCredits) {
        super(id, initialCredits);
    }

    @Override
    public boolean addCourse(String name, int units) {
        if (totalCredits + units <= RegistrationConfiguration.MAX_CREDITS) {
            totalCredits += units;
            registeredCourses.put(name, units);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCourse(String name, int units) {
        if (registeredCourses.containsKey(name)) {
            totalCredits -= units;
            registeredCourses.remove(name);
            return true;
        }
        return false;
    }
}