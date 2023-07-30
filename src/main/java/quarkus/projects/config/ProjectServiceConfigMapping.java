package quarkus.projects.config;

import io.smallrye.config.ConfigMapping;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigMapping(prefix = "project-service")
public interface ProjectServiceConfigMapping {

  @ConfigProperty(name = "client-name")
  String clientName();
}
