package by.example.rest.dto.responses.TestCaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@Builder
public class TestCaseResponse {
    public boolean status;
    public ResultResponseTestCase result;
}