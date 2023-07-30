package quarkus.projects.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Project p ORDER BY p.projectId")
@NamedQuery(
    name = "Projects.findByProjectId",
    query = "SELECT p FROM Project p WHERE p.projectId = :projectId ORDER BY p.projectId")
@Entity
public class Project {
  private Long id;
  private Long projectId;
  private String projectName;
  private int durationInMonths;
  private int numResourcesAllocated;
  private String projectStatus;
  private String clientName;
  private String budgetStatus;

  @Id
  @SequenceGenerator(
      name = "projectIdSequenceGenerator",
      sequenceName = "project_id_seq",
      allocationSize = 1,
      initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectIdSequenceGenerator")
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
