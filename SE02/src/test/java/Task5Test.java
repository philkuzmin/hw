/**
 * Created by Air on 18/01/2017.
 */
import org.junit.Test;
import task5.*;

public class Task5Test {

    @Test
    public void testDisciplines() {

        Student s1 = new Student("John", "Smith");
        Student s2 = new Student("Mark", "Smith");
        Student s3 = new Student("George", "Jones");
        Student s4 = new Student("James", "Brown");
        Student s5 = new Student("Frank", "Black");
        String g1 = "Group1";
        String g2 = "Group2";
        String g3 = "Group3";

        Schedule schedule = new Schedule();
        schedule.addGroup(Disciplines.MATH, g1);
        schedule.addGroup(Disciplines.MATH, g2);
        schedule.addGroup(Disciplines.BIOLOGY, g1);
        schedule.addGroup(Disciplines.CHEMISTRY, g1);
        schedule.addGroup(Disciplines.PHYSICS, g1);

        schedule.addStudent(Disciplines.MATH, g1, s1, 5);
        schedule.addStudent(Disciplines.MATH, g1, s2, 3);
        schedule.addStudent(Disciplines.MATH, g2, s3, 4);
        schedule.addStudent(Disciplines.BIOLOGY, g3, s4, 5.2);
        schedule.addStudent(Disciplines.BIOLOGY, g1, s1, 5.2);
        schedule.addStudent(Disciplines.BIOLOGY, g3, s5, 3.4);
        schedule.addStudent(Disciplines.CHEMISTRY, g1, s1, 3.1);
        schedule.addStudent(Disciplines.PHYSICS, g1, s1, 3);

        System.out.println(schedule.getStudentGrades(s2));
    }

}
