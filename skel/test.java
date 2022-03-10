import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
    }

    public float avgStudentGrade(Student s) {
        float sum = 0;
        for (int i = 0; i < s.grades.length; i++) {
            sum += s.grades[i];
        }
        return sum/s.grades.length;
    }
}

