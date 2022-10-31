package payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SubjectDto {
    private Integer id;
    @NotEmpty(message = "Name of subject should not be empty")
    private String name;
    @NotEmpty(message = "Teacher's id should not be empty")
    private Integer teacherId;
}