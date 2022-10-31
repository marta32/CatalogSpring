package payload;

import com.example.springbootcatalog.entity.Grade;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class SubjectDto {
    private Integer id;
    @NotEmpty(message = "Name of subject should not be empty")
    private String name;
    @NotEmpty(message = "Teacher's id should not be empty")
    private Integer teacherId;
    private Set<Grade> grades;
}