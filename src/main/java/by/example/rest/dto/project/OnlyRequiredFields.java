package by.example.rest.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@Builder
public class OnlyRequiredFields {
    public String title;
}