import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;


public class StudentSorter {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner stdin=new Scanner(System.in);
        List<Student> infoList = Student.makeInfoList();
        options();
        String userResponse = stdin.next();
        while(!userResponse.equalsIgnoreCase("q")) {
            switch (userResponse.toUpperCase()) {
                case "F":
                    Comparator<Student> f = new StudentFirstNameComparator();
                    Collections.sort(infoList, f);
                    System.out.println("students sorted by first name:");
                    printList(infoList);
                    break;
                case "L":
                    Comparator<Student> l = new StudentLastNameComparator();
                    Collections.sort(infoList, l);
                    System.out.println("students sorted by last name:");
                    printList(infoList);
                    break;
                case "I":
                    Comparator<Student> i = new StudentIdComparator();
                    Collections.sort(infoList, i);
                    System.out.println("students sorted by id:");
                    printList(infoList);
                    break;
                case "G":
                    Comparator<Student> g = new StudentGradeComparator();
                    Collections.sort(infoList, g);
                    System.out.println("students sorted by grade:");
                    printList(infoList);
                    break;
            }
            options();
            userResponse = stdin.next();
        }
        stdin.close();
    }

    public static void options() {
        System.out.println("Choices:\nF - sort by first name\nL - sort by last name\nI - sort by id\n" +
                "G - sort by grade\nQ - quit");
    }
    public static void printList(List infoList) {
        for (int i=0; i<infoList.size(); i++) {
            System.out.println(infoList.get(i));
        }
        System.out.println();
    }

}

class Student {
    private String firstName, lastName;
    private int id;
    private double grade;

    private Student (Scanner sc) {
        firstName=sc.next();
        lastName=sc.next();
        id=sc.nextInt();
        grade=sc.nextDouble();
    }

    public static List makeInfoList() throws FileNotFoundException {
        Scanner sc=new Scanner(new File("students.txt"));
        List studentInfo = new ArrayList<Student>();
        while (sc.hasNextLine()) {
            Student stu = new Student(sc);
            studentInfo.add(stu);
        }
        return studentInfo;
    }

    @Override
    public String toString() {
        return getFirstName()+" "+getLastName()+" "+getId()+" "+getGrade();
    }

    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public int getId() { return this.id; }
    public double getGrade() { return this.grade; }

}

class StudentFirstNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b) {
        return (a.getFirstName().compareTo(b.getFirstName()));
    }
}

class StudentLastNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b) {
        return (a.getLastName().compareTo(b.getLastName()));
    }
}

class StudentIdComparator implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b) {
        return Integer.compare(a.getId(), b.getId());
    }
}

class StudentGradeComparator implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b) {
        return Double.compare(a.getGrade(), b.getGrade());
    }
}
