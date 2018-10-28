package edu.byu.hbll.serviceTrip.database;

import edu.byu.hbll.serviceTrip.Configuration;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

@Startup
@Singleton
//@SuppressWarnings("CheckStyle")
public class TableCreator {

  @Inject
  Configuration configuration;

  @Inject
  TableFiller tableFiller;

  BasicDataSource ds;
  QueryRunner runner;

  @PostConstruct
  public void init() {
    ds = configuration.getDs();
    runner = new QueryRunner(ds);

    createTables();

    try{
      tableFiller.fillTables();
    } catch (Exception e){
      e.printStackTrace();
    }

  }

  public void createTables() {
    try {
      runner.update("CREATE TABLE IF NOT EXISTS `EventInformation` ("
          + "`EventId` VARCHAR(150) NOT NULL PRIMARY KEY,"
          + "`EventName` VARCHAR(50) DEFAULT NULL,"
          + "`Organization` VARCHAR(50) DEFAULT NULL )");
      runner.update("CREATE TABLE IF NOT EXISTS `Media` ("
          + "`EventId` VARCHAR(150) NOT NULL PRIMARY KEY,"
          + "`EventDescription` VARCHAR(2500) DEFAULT NULL,"
          + "`EventPicture` BLOB DEFAULT NULL )");
      runner.update("CREATE TABLE IF NOT EXISTS `Tag` ("
          + "`EventId` VARCHAR(150) NOT NULL,"
          + "`Tag` VARCHAR(50) DEFAULT NULL )");
      runner.update("CREATE TABLE IF NOT EXISTS `Specifications` ("
          + "`EventId` VARCHAR(150) NOT NULL PRIMARY KEY,"
          + "`NumberEnrolled` INT DEFAULT NULL,"
          + "`Cost` DECIMAL(10,2) DEFAULT NULL,"
          + "`Place` VARCHAR(50) DEFAULT NULL,"
          + "`StartDate` DATE DEFAULT NULL,"
          + "`EndDate` DATE DEFAULT NULL )");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



}
