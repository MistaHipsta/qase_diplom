package by.example.rest.dto.responses.TestCaseResponse;

import com.google.gson.annotations.Expose;
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

public class TestCaseResponse {

    public boolean status;
    public Result result;
}