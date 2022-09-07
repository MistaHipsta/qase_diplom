package by.example.rest.dto.responses.TestCaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@Builder
public class ResultResponseTestCase {
    public int id;
}