package by.example.rest.dto.responses.TestCaseResponse;


import com.google.gson.annotations.SerializedName;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultById {

    public int id;
    public int position;
    public String title;
    public Object description;
    public Object preconditions;
    public Object postconditions;
    public int severity;
    public int priority;
    public int type;
    public int layer;
    @SerializedName("is_flaky")
    public int isFlaky;
    public int behavior;
    public int automation;
    public int status;
    @SerializedName("milestone_id")
    public Object milestoneId;
    @SerializedName("suite_id")
    public Object suiteId;
    public List<Object> links;
    @SerializedName("custom_fields")
    public List<Object> customFields;
    public List<Object> attachments;
    @SerializedName("steps_type")
    public Object stepsType;
    public List<Object> steps;
    public List<Object> params;
    @SerializedName("member_id")
    public int memberId;
    @SerializedName("project_id")
    public int projectIid;
    public List<Object> tags;
    public Object deleted;
    public Date created;
    public Date updated;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
}
