package by.example.rest.dto.testCases;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@Builder
public class Case {

    public List<String> steps;
    public String title;
    public String description;
    public String preconditions;
    public String postconditions;
    public int severity;
    public int priority;
    public int behavior;
    public int type;
    public int layer;
    @SerializedName("is_flaky")
    public int isFlaky;
    public int automation;
    public int status;
}

