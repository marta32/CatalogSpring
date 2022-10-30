package payload;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class TeacherDto {
    private Integer id;
    @NotEmpty(message = "First name should not be empty")
    private String first_name;
    @NotEmpty(message = "Last name should not be empty")
    private String last_name;
    @NotEmpty(message = "Date of birth should not be empty")
    private LocalDate birthday;
}