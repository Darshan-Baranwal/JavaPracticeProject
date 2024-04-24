package JavaStreams.StudentCourse;

import java.time.LocalDate;
import java.time.Period;

public class CourseEngagement {
    private final Course course;
    private final LocalDate enrollmentDate;

    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = this.lastActivityDate = enrollmentDate;
        this.engagementType = engagementType;
    }

    public String getCourseCode() {
        return course.courseCode();
    }

    public int getEnrollmentYear() {
        return enrollmentDate.getYear();
    }

    public String getEngagementType() {
        return engagementType;
    }

    public int getLastLecture() {
        return lastLecture;
    }

    public int getLastActivityYear() {
        return lastActivityDate.getYear();
    }

    public String getLastActivityMonth() {
        return "%tb".formatted(lastActivityDate);
    }

    public double getPercentageComplete() {
        return lastLecture*100/ course.lectureCount();
    }

    public int getMonthsSinceActive() {
        LocalDate now = LocalDate.now();
        return (int) Period.between(lastActivityDate, now).toTotalMonths();
    }

    public void watchLecture(int lectureNumber, LocalDate currentDate) {
        lastLecture = Math.max(lastLecture, lectureNumber);
        lastActivityDate = currentDate;
        engagementType = "Lecture "+lastLecture;
    }

    @Override
    public String toString() {
        return "%s: %s %d %s [%d]".formatted(course.courseCode(), getLastActivityMonth(),
                getLastActivityYear(), engagementType, getMonthsSinceActive());
    }
}
