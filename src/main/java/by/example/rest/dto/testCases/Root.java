package by.example.rest.dto.testCases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@Builder
public class Root {
    public boolean status;
    public Case result;
}
