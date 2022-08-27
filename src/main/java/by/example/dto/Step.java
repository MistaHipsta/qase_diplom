package by.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@Builder
public class Step {
    public String action;
    public String expected_result;
    public String data;
    public int position;
}