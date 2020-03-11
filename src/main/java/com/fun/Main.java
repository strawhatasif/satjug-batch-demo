package com.fun;

import com.fun.misc.ConnectionProperties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getCanonicalName());
    private static final String SAMPLE_JOB_NAME = "sampleBatch";
    private static final String DATABASE_JOB_NAME = "dataCorrectionBatch";

    public static void main(String[] args) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Properties properties = new Properties();

        //long executionId = jobOperator.start(SAMPLE_JOB_NAME, properties);
        long executionId = jobOperator.start(DATABASE_JOB_NAME, properties);
        persistMetadata(executionId);
    }

    private static void persistMetadata(long executionId) {
        String statement = "INSERT INTO dbo.BATCH_JOB (JOB_NM, EXECUTION_ID, JOB_DATE, JOB_STATUS) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement;

        try (Connection connection = DriverManager.getConnection(ConnectionProperties.connectionURL)) {
            Class.forName(ConnectionProperties.driverClass);
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(statement);

            preparedStatement.setString(1, DATABASE_JOB_NAME);
            preparedStatement.setLong(2, executionId);
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(4,"RUNNING");

            preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException ce) {
           logger.log(Level.SEVERE, "we have an exception finding a driver! " + ce);
        }
        catch (SQLException se) {
            logger.log(Level.SEVERE, "we have an exception connecting to the database: " + se);
        }
    }
}
