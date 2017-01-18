package task5;

import java.util.ArrayList;
import static task5.Disciplines.*;

/**
 * Created by Air on 18/01/2017.
 */
public class Schedule {

    private ArrayList<Discipline> disciplines;

    public Schedule() {
        disciplines = new ArrayList<>();
        disciplines.add(new Discipline<Integer>(MATH));
        disciplines.add(new Discipline<Integer>(PHYSICS));
        disciplines.add(new Discipline<Double>(BIOLOGY));
        disciplines.add(new Discipline<Double>(CHEMISTRY));
    }

    public boolean addGroup(Disciplines discipline, String groupName) {
        for (Discipline d: disciplines) {
            if (d.getDiscipline() == discipline) {
                return d.addNewGroup(groupName);
            }
        }
        return false;
    }

    public boolean addStudent(Disciplines discipline, String groupName, Student student, Number grade) {
        for (Discipline d: disciplines) {
            if (d.getDiscipline() == discipline) {
                return d.addStudentToGroup(groupName, student, grade);
            }
        }
        return false;
    }

    public String printSchedule() {
        StringBuilder out = new StringBuilder("Schedule: ").append("\n");
        for (Discipline d: disciplines) {
            out.append(d.printDiscipline()).append("\n");
        }
        return out.toString();
    }

    public String getStudentGrades(Student student) {
        StringBuilder out = new StringBuilder("Student: ").append(student.toString()).append("\n");
        for (Discipline d: disciplines) {
            out.append("-----------------------------------\n");
            out.append("Discipline: ").append(d.getDisciplineTitle()).append("\n");
            out.append(d.getGrades(student));
        }
        return out.toString();
    }
}
