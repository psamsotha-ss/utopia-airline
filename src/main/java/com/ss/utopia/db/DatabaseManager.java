package com.ss.utopia.db;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

    private static final Logger logger = LogManager.getLogger(DatabaseManager.class);
    private static DatabaseManager instance;

    private DataSource dataSource;

    private DatabaseManager() {
        if (System.getenv("MYSQL_PASSWORD") == null) {
            throw new DatabaseException("MYSQL_PASSWORD env var not set.");
        }
        Properties dataSourceProps = getProperties("/database.properties");
        dataSourceProps.setProperty("dataSource.password", System.getenv("MYSQL_PASSWORD"));
        HikariConfig config = new HikariConfig(dataSourceProps);

        // With a multi-container startup, the database may not be ready, so we keep retrying
        // See https://docs.docker.com/compose/startup-order/
        Properties appProps = getProperties("/application.properties");
        int retries = Integer.parseInt(appProps.getProperty("databaseManager.startupRetries"));
        logger.debug("Attempting to create DataSource...");
        do {
            if (loadDateSource(config)) {
                break;
            } else {
                logger.debug("Retrying to create data source: {} retried left.", retries);
                retries--;
                try {
                    logger.debug("Waiting {} ms to retry.", 1000);
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } while (retries > 0);

        if (dataSource == null) {
            throw new DatabaseException("DataSource could not be initialized.");
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    private boolean loadDateSource(HikariConfig config) {
        try {
            dataSource = new HikariDataSource(config);
        } catch (Exception ex) {
            logger.info("Could not create data source: {}", ex.getMessage());
            return false;
        }
        return true;
    }

    private Properties getProperties(String resource) {
        Properties properties = new Properties();
        try {
            logger.debug("Loading properties file: {}", resource);
            properties.load(getClass().getResourceAsStream(resource));
        } catch (IOException ex) {
            logger.error("Could not load resource: {}", resource);
            throw new RuntimeException("Could not load resource: " + resource);
        }
        return properties;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
