package by.example.rest.dto.responses.projectByCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class Counts {
    public int cases;
    public int suites;
    public int milestones;
    public Runs runs;
    public Defects defects;
}
