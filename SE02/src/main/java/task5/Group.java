package task5;

import java.util.HashMap;

/**
 * Created by Air on 18/01/2017.
 */
 class Group<T extends Number> {

    private String name;
    private HashMap<Student, T> students;

    Group(String name) {
        this.name = name;
        students = new HashMap<Student, T>();
    }

    void addStudent(Student student, T grade) {
        students.put(student, grade);
    }

    T getGrade(Student student) {
        return students.get(student);
    }

     String getName() {
        return name;
    }

     void setName(String name) {
        this.name = name;
    }

    String printGroup(){
        StringBuilder out = new StringBuilder("Group: ").append(name).append("\n");
        for (Student s: students.keySet()) {
            out.append(s.getLastName()).append(", ").append(s.getFirstName()).append(": ").append(students.get(s)).append("\n");
        }
        return out.toString();
    }

}
