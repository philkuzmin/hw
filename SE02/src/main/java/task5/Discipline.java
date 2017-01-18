package task5;

import java.util.ArrayList;

/**
 * Created by Air on 18/01/2017.
 */
public class Discipline<T extends Number> {

    private Disciplines discipline;
    private ArrayList<Group> groups;

    public Discipline(Disciplines discipline) {
        this.discipline = discipline;
        groups = new ArrayList<>();
    }

    public boolean addNewGroup(String groupName) {
        for (Group g: groups) {
            if (g.getName().equals(groupName)) {
                return false;
            }
        }
        return groups.add(new Group<T>(groupName));
    }

    public boolean addStudentToGroup(String groupName, Student student, T grade) {
        for (Group g: groups) {
            if (g.getName().equals(groupName)) {
                g.addStudent(student, grade);
                return true;
            }
        }
        return false;
    }

    public String getGrade(String groupName, Student student) {
        for (Group g: groups) {
            if (g.getName().equals(groupName)) {
                Number grade = g.getGrade(student);
                if (grade != null) {
                    return grade.toString();
                } else {
                    return "No such student in this group.";
                }
            }
        }
        return "Group not found";
    }

    public String printDiscipline() {
        StringBuilder out = new StringBuilder("Discipline: ").append(discipline).append("\n");
        for (Group g: groups) {
            out.append(g.printGroup()).append("\n");
        }
        return out.toString();
    }

    public String getGrades(Student student) {
        StringBuilder out = new StringBuilder();
        for (Group g: groups) {
            Number grade = g.getGrade(student);
            if (grade != null) {
                out.append("Group: ").append(g.getName()).append(", Grade: ").append(grade.toString()).append("\n");
            }
        }
        return out.toString();
    }

    public String getDisciplineTitle() {
        return discipline.name();
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }
}
