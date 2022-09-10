package by.example.rest.dto.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    public String title;
    public String code;
    public String description;
    public String getCode() { return code; }
    public void setCode(String code) {
        this.code = code;
    }
}

