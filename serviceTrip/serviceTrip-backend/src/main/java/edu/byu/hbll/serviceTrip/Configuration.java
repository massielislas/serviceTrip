package edu.byu.hbll.serviceTrip;

import com.fasterxml.jackson.databind.JsonNode;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import edu.byu.hbll.json.YamlLoader;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** Configuration manager for this application. */
@Singleton
@Startup
public class Configuration {

  /** The system property containing the list of config files to be loaded. */
  private static final String CONFIGS_PROPERTY = "edu.byu.hbll.serviceTrip.configs";

  /**
   * If {@code true}, then this project will fail to deploy if no config file(s) are defined, or if
   * they are empty. If {@code false}, then missing configuration will merely raise a warning during
   * deployment.
   */
  private static final boolean FAIL_ON_MISSING_CONFIG = true;

  private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

  // TODO: Define additional class properties/members as needed here.

  private BasicDataSource ds;

  /**
   * Loads configuration data read from the config file(s) listed in the {@link #CONFIGS_PROPERTY}
   * property.
   *
   * <p>No checked exceptions are thrown by this method. Instead, all exceptions encountered will be
   * logged at the ERROR level, after which a {@link RuntimeException} is thrown, causing the
   * deployment to fail.
   */
  @PostConstruct
  private void postConstruct() {
    try{
      if (System.getProperties().containsKey(CONFIGS_PROPERTY)) {
        loadConfig();
      } else {
        if (FAIL_ON_MISSING_CONFIG) {
          throw new RuntimeException("configuration property not defined");
        }
      }
    } catch(Exception e){
      logger.error("serviceTrip-backend failed to deploy", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Loads configuration data read from the given YAML config file(s).
   *
   * @throws IOException if the given files are not readable or cannot be parsed as YAML
   */
  public void loadConfig() throws IOException {
    String configs = System.getProperty(CONFIGS_PROPERTY);
    JsonNode config = new YamlLoader().loadPaths(configs);

    if (config == null || config.size() == 0) {
      if (FAIL_ON_MISSING_CONFIG) {
        throw new RuntimeException("configuration is empty");
      } else {
        logger.warn("configuration is empty");
      }
    }


    String username = config.path("username").asText();
    String password = config.path("password").asText();
    String url = config.path("url").asText();

    ds = new BasicDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);

    /*TODO: After creating a new project, add the application-specific instructions to this method needed to
    process your config data and make it available to the rest of your application. You should not need to
    modify the postConstruct() method, which simply calls this method using the config files listed in the
    system property identified by CONFIGS_PROPERTY.*/
  }

  public BasicDataSource getDs(){
    return ds;
  }

}
