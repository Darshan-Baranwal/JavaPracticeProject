package JavaStreams.StudentCourse;

public record Course(String courseCode, String title, int lectureCount) {
    public Course(String courseCode, String title) {
        this(courseCode, title, 40);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
