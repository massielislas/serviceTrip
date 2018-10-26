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
public class TableCreator {

  @Inject
  Configuration configuration;

  BasicDataSource ds;
  QueryRunner runner;
  TableFiller tableFiller;

  @PostConstruct
  public void init() {
    ds = configuration.getDs();
    runner = new QueryRunner(ds);

    createTables();
    tableFiller = new TableFiller();

    try{
      tableFiller.fillTables();
    } catch (Exception e){
      e.printStackTrace();
    }

  }

  public void createTables() {
    try {
      runner.update("CREATE TABLE IF NOT EXISTS `EventInformation` ("
          + "`EventId` VARCHAR(20) NOT NULL PRIMARY KEY,"
          + "`EventName` VARCHAR(50) DEFAULT NULL,"
          + "`Organization` VARCHAR(50) DEFAULT NULL )");
      runner.update("CREATE TABLE IF NOT EXISTS `Media` ("
          + "`EventId` VARCHAR(20) NOT NULL PRIMARY KEY,"
          + "`EventDescription` VARCHAR(50) DEFAULT NULL,"
          + "`EventPicture` BLOB DEFAULT NULL )");
      runner.update("CREATE TABLE IF NOT EXISTS `Tag` ("
          + "`EventId` VARCHAR(20) NOT NULL PRIMARY KEY,"
          + "`Tag` VARCHAR(20) DEFAULT NULL )");
      runner.update("CREATE TABLE IF NOT EXISTS `Specifications` ("
          + "`EventId` VARCHAR(20) NOT NULL PRIMARY KEY,"
          + "`NumberEnrolled` INT DEFAULT NULL,"
          + "`Cost` DECIMAL(10,2) DEFAULT NULL,"
          + "`Place` VARCHAR(50) DEFAULT NULL,"
          + "`StartDate` VARCHAR(50) DEFAULT NULL,"
          + "`EndDate` VARCHAR(50) DEFAULT NULL )");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



}
