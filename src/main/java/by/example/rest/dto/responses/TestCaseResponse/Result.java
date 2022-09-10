package by.example.rest.dto.responses.TestCaseResponse;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    public int id;
}