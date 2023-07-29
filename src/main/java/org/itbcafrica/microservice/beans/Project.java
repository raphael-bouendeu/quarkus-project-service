package org.itbcafrica.microservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project{
    private Long projectId;
    private String projectName;
    private int durationInMonths;
    private int numberOfResources;
    private String projectStatus;

}
