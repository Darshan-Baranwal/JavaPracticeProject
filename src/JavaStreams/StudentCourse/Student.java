package JavaStreams.StudentCourse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class Student {

    private static long lastStudentId = 1;
    private final static Random random = new Random();
    private final long studentId;

    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;

    private final String gender;
    private final boolean programmingExperience;

    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender,
                   boolean programmingExperience, Course... courses) {
        studentId = lastStudentId++;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;

        for(Course course: courses) {
            addCourse(course, LocalDate.of(yearEnrolled, 1,1));
        }
    }

    public void addCourse(Course course) {
        this.addCourse(course, LocalDate.now());
    }
    public void addCourse(Course course, LocalDate enrollDate) {
        engagementMap.put(course.courseCode(),
                new CourseEngagement(course, enrollDate, "Enrollment"));
    }

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean isProgrammingExperience() {
        return programmingExperience;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear()-yearEnrolled;
    }
    public int getAge() {
        return ageEnrolled+getYearsSinceEnrolled();
    }

    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement courseEngagementInfo = engagementMap.get(courseCode);
        return courseEngagementInfo == null?0:courseEngagementInfo.getMonthsSinceActive();
    }

    public int getMonthsSinceActive() {
        int inactiveMonths = (LocalDate.now().getYear()-2014)*12;
        for (String key :
                engagementMap.keySet()) {
            inactiveMonths = Math.min(inactiveMonths, getMonthsSinceActive(key));
        }
        return inactiveMonths;
    }

    public double getPercentageComplete(String courseCode) {
        CourseEngagement courseEngagementInfo = engagementMap.get(courseCode);
        return courseEngagementInfo==null?0:courseEngagementInfo.getPercentageComplete();
    }
    public void watchLecture(String courseCode, int lectureNumber, int month, int year) {
        CourseEngagement activity = engagementMap.get(courseCode);
        if (activity!=null) {
            activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

    private static String getRandomVal(String... data) {
        return data[random.nextInt(data.length)];
    }
    public static Student getRandomStudent(Course... courses)
    {
        int maxYear = LocalDate.now().getYear()+1;
        Student student = new Student(getRandomVal("IN", "AU", "CA", "GB"),
                random.nextInt(2015),
                random.nextInt(18),
                getRandomVal("M", "F"),
                random.nextBoolean(), courses);

        for(Course c: courses) {
            int lecture = random.nextInt(1);
            int year = random.nextInt();
        }
        return student;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", countryCode='" + countryCode + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                ", ageEnrolled=" + ageEnrolled +
                ", gender='" + gender + '\'' +
                ", programmingExperience=" + programmingExperience +
                ", engagementMap=" + engagementMap +
                '}';
    }
}
