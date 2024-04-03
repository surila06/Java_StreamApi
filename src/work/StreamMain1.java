package work;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain1
{
    public static void main(String[] args)
    {
       List<Student> studentList=Stream.of(
               new Student(1,"Rohit",30,"Male","Mech Engineering","Mumbai",122,Arrays.asList("857647493","6575846383")),
               new Student(2,"Chetan",28,"Male","Chemical Engineering","Delhi",67,Arrays.asList("4161772738","897653433")),
               new Student(3,"Pulkit",23,"Male","Mech Engineering","Mumbai",164,Arrays.asList("564392872","123456789")),
               new Student(4,"Shanu",27,"Female","Computer Engineering","Kerela",26,Arrays.asList("234567891","763652938")),
               new Student(5,"Meher",30,"Female","Mech Engineering","Karnataka",12,Arrays.asList("7728853929","2638383922")),
               new Student(6,"Ajay",25,"Male","Biotech Engineering","Delhi",90,Arrays.asList("4390876656","237864191")),
               new Student(7,"Ram",32,"Male","Electrical Engineering","Mumbai",324,Arrays.asList("2009437812","5378990243")),
               new Student(8,"Rohit",26,"Male","Computer Engineering","Karnataka",433,Arrays.asList("2890451272","1673452738")),
               new Student(9,"Soniya",27,"Female","Instrumental Engineering","Mumbai",70,Arrays.asList("8502417493","7399426411")),
               new Student(10,"Reshu",31,"Male","Mech Engineering","Karnataka",44,Arrays.asList("08426154322","7264391022"))
       ).collect(Collectors.toList());


       //filter students whose rank between 50 to 100
        List<Student> rankListStudent =studentList.stream().filter(student->student.getRank()>=50 && student.getRank()<=100).collect(Collectors.toList());
        System.out.println(rankListStudent);

        //find student who stays in "Mumbai" and sort them by name
        List<Student> studentsByCity=studentList.stream()
                .filter(student->student.getCity().equalsIgnoreCase("Mumbai"))
                /*for reverse order
                        .sorted(Comparator.comparing(Student::getFirstName,Comparator.reverseOrder()))*/
                .sorted(Comparator.comparing(Student::getFirstName))
                .collect(Collectors.toList());


        studentsByCity.forEach(s-> System.out.println(s.getFirstName()+" "+s.getCity()));

        //extract all available dept names unique result
        List<String> deptName=studentList.stream().map(Student::getDept).distinct().collect(Collectors.toList());
        Set<String> uniqueDeptNamesSet=studentList.stream().map(Student::getDept).collect(Collectors.toSet());
        System.out.println(deptName);


        //fetch all contact numbers from student list
        List<List<String>> contactMapList=studentList.stream().map(Student::getContacts).collect(Collectors.toList());
        for(List<String> s:contactMapList)
        {
            System.out.println(s);
        }

        List<String> contactFlatMapList=studentList.stream().flatMap(student->student.getContacts().stream()).collect(Collectors.toList());
        System.out.println(contactFlatMapList);

        //group Student by deptname
        Map<String,List<Student>> studentGropuByDeptMap=
                studentList.stream().collect(Collectors.groupingBy(Student::getDept));
        System.out.println(studentGropuByDeptMap);

        //group by deptname and count of students
        Map.Entry<String,Long> studentGropuByDeptCountMap=
                studentList.stream().collect(Collectors.groupingBy(Student::getDept,Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(studentGropuByDeptCountMap);

       // average age of male and female students
        Map<String, Double> genderAverageAge =
                studentList.stream()
                        .collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(student->student.getAge())));
        System.out.println(genderAverageAge);

        //find highest rank in each department
        Map<String, Optional<Student>> highestRankInEachDept =
                studentList.stream()
                           .collect(Collectors.groupingBy(Student::getDept, Collectors.maxBy(Comparator.comparing(Student::getRank))));

        System.out.println(highestRankInEachDept);

        //get student who has second highest rank
        Student student = studentList.stream().sorted(Comparator.comparing(Student::getRank))
                .skip(1).findFirst().get();

        System.out.println(student);


    }
}
