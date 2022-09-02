package by.example.rest.dto.testCases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@Builder
public class Case {
    //public List<String> attachments;
    public List<Step> steps;
    //public List<String> tags;
    public String title;
    public String description;
    public String preconditions;
    public String postconditions;
    public int severity;
    public int priority;
    public int behavior;
    public int type;
    public int layer;
    public int is_flaky;
    //public int suite_id;
   // public int milestone_id;
    public int automation;
    public int status;
}