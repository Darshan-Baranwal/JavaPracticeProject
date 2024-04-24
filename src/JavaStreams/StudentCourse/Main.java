package JavaStreams.StudentCourse;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        Course jsMaterClass = new Course("JSM", "Javascript Masterclass");
        Course javaMS = new Course("JAVA", "Java Master class");
        Student s1 = new Student("IN", 2019, 27,"M", true, jsMaterClass, javaMS);
        Student s2 = new Student("CN", 2018, 26,"F", true, jsMaterClass, javaMS);
        Student s3 = new Student("AU", 2019, 27,"M", false, jsMaterClass);

        Student s4 = new Student("IN", 2019, 29,"M", true, jsMaterClass, javaMS);
        Student s5 = new Student("AU", 2019, 33,"M", false, jsMaterClass);

        s1.watchLecture(javaMS.courseCode(), 10,2,2023);
//        s2.watchLecture(jsMaterClass.courseCode(), 4,4,2022);

        List<Student> list = Arrays.asList(s1, s2, s3, s4, s5);
        list.stream().forEach(printData);
        BiPredicate<Student, String> satisfyingGender = (student, gender)->
                                                        student.getGender().equalsIgnoreCase(gender);
        /* print Male Students */
//        list.stream().filter(s ->satisfyingGender.test(s, "M")).forEach(printData);
        List<Student> longTimeLearnersUnmodifiableList = list.stream()
                .filter(s-> !s.isProgrammingExperience())
                .toList(); // return unmodifiable list, allowed to change.
//        System.out.println(longTimeLearnersUnmodifiableList);
        try {
//            Collections.shuffle(longTimeLearnersUnmodifiableList);
        } catch(UnsupportedOperationException e){
            System.out.println(e.toString());
        }

        //  above shuffle method will throw UnsupportedOperatedException on Immutable collection
        // to fix this use .collect(Collectors.toList());

        List<Student> longTimeLearnersMutableList = list.stream()
                .filter(s-> !s.isProgrammingExperience())
                .collect(Collectors.toList());
//        Collections.shuffle(longTimeLearnersMutableList); // this will not throw exception

        var arrayOfStudents = list.stream().filter(s-> (s.getAge()-s.getAgeEnrolled())>7)
                .filter(s-> !s.isProgrammingExperience())
                .toArray(s -> new Student[s]);

        Set<Student> youngLearner = list.stream().filter(s -> s.getAgeEnrolled() < 30)
                .collect(Collectors.toSet());
        Set<Student> indianLearners = list.stream().filter(s -> s.getCountryCode().equals("IN")).collect(Collectors.toSet());
        Set<Student> youngIndianStudents = new TreeSet<>(Comparator.comparing(Student::getStudentId));
        youngIndianStudents.addAll(youngLearner);

        System.out.println("---------");

        youngIndianStudents.retainAll(indianLearners);

        youngIndianStudents.forEach(printData);
        System.out.println("---------");
        list.stream().filter(s -> s.getAgeEnrolled() < 30)
                .filter(s -> s.getCountryCode().equals("IN"))
                .collect(() -> new TreeSet(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll).forEach(printData);

        // collect(supplier, accumulator, combiner)
        // supplier -> the final return of the collect method.
        // IN above example supplier is TreeSet with custom comparator provided.

        // accumulator -> how data is added/accumulated in supplier
        // combiner -> ???

        var stringOfAllCountries = list.stream().map(Student::getCountryCode).distinct()
                .reduce("", (result, value) -> result+" "+value);

        var totalPercentageCompleted = list.stream().map(s->s.getPercentageComplete("JAVA"))
                .reduce((double) 0, Double::sum);
        // .sum() can also be used
        double averagePercentageCompleted = totalPercentageCompleted/list.size();
        System.out.println(averagePercentageCompleted);

        List<Student> studentCompleted3QuarterOfTheCourse = list.stream()
                .filter(s -> s.getPercentageComplete("JAVA") >= averagePercentageCompleted * 1.25)
                .collect(Collectors.toList());
        System.out.println("-----------------------");
        System.out.println(studentCompleted3QuarterOfTheCourse);
        System.out.println("-----------------------");
        int minAge = 21; // change this to 30 to get data else this is how the null should be handled
        list.stream().filter(s -> s.getAgeEnrolled() <= minAge)
                .sorted(Comparator.comparing(Student::getStudentId)) // give students sorted with id
                .findAny() // no guarantee of the student order
//                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("No such data"))
                ;

        System.out.println("-----------------------");
//        list.stream().filter(s -> s.getAgeEnrolled() <= minAge).forEach(printData);
        Student minAgeStudent2 = list.stream().filter(s -> s.getAgeEnrolled() <= minAge)
                .min(Comparator.comparing(Student::getAgeEnrolled)) // returns minimum age among filtered students
                .orElse(null);
        System.out.println(minAgeStudent2);
        System.out.println("-----------------------");

        double averageAgeUnder30 = list.stream().filter(s->s.getAgeEnrolled()<30)
                .mapToInt(Student::getAgeEnrolled)
                .average().getAsDouble();
        System.out.println(averageAgeUnder30);
        System.out.println("-----------------------");

        String countriesForStudents = list.stream().filter(s -> s.getAgeEnrolled() < 30)
                .map(Student::getCountryCode).distinct()
                .reduce((a, b) -> String.join(",", a, b)).orElse("");
        System.out.println(countriesForStudents);
        System.out.println("-----------------------");

        Map<String, List<Student>> countryStudent = list.stream()
                .collect(groupingBy(Student::getCountryCode));

        countryStudent.entrySet().forEach(e -> System.out.println(e.getKey() + " "+ e.getValue()));
        System.out.println("-----------------------");

        Map<String, List<Student>> youngerCountryStudents = list.stream()
                .collect(
                        groupingBy(Student::getCountryCode, filtering(s -> s.getAgeEnrolled() < 29, toList())));
        youngerCountryStudents.forEach((key, value) -> System.out.println(key + " " + value));

        System.out.println("-----------------------");

        Map<Boolean, List<Student>> experiencedStudents = list.stream().collect(
                partitioningBy(Student::isProgrammingExperience));
        experiencedStudents.forEach((k,v) -> System.out.println(k +" "+v));
        System.out.println("-----------------------");
        Map<Boolean, Long> expStudentsCount = list.stream().collect(
                partitioningBy(Student::isProgrammingExperience, counting()));
        expStudentsCount.forEach((k,v) -> System.out.println(k +" "+v));
        System.out.println("-----------------------");

        Map<Boolean, Long> expAndInactive = list.stream().collect(
                partitioningBy(
                        s -> s.isProgrammingExperience() && s.getMonthsSinceActive()==0 ,
                        counting()));
        expAndInactive.forEach((k,v) -> System.out.println(k +" "+v));
        System.out.println("-----------------------");

        Map<String, Map<String, List<Student>>> multiLevel = list.stream().collect(
                groupingBy(Student::getCountryCode, groupingBy(Student::getGender))
        );
        multiLevel.forEach((k,v) -> {
            System.out.println(k);
            v.forEach((key,value) -> System.out.println(key+" "+value.size()));
        });
        System.out.println("-----------------------");

        multiLevel.values().stream().flatMap(map -> map.values().stream())
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() < 3)
                .count();
    }

    public static Consumer<Object> printData = System.out::println;


}
