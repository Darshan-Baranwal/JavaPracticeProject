package Optional;

import java.util.Optional;

public class OptionalClassExamples {
    public static void main(String[] args) {
        System.out.println(
                Optional.ofNullable(null)
                        .map(v -> v+" Baranwal").orElse("Dummy Value"));

        System.out.println(
                Optional.ofNullable("Darshan")
                        .map(v -> v+" Baranwal").orElse("Dummy Value"));

    }
}
