package com.fun.listeners;

import com.fun.misc.ConnectionProperties;

import javax.batch.api.listener.JobListener;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomJobListener implements JobListener {
    @Inject
    JobContext jobContext;

    private static Logger logger = Logger.getLogger(CustomJobListener.class.getCanonicalName());

    @Override
    public void beforeJob() throws Exception {
        System.out.println("the job is about to start!");
    }

    @Override
    public void afterJob() throws Exception {
        System.out.println("we are done with " + jobContext.getJobName());
        updateJobStatus();
    }

    private void updateJobStatus() {
        String statement = "UPDATE dbo.BATCH_JOB SET JOB_STATUS=? WHERE JOB_NM=?";

        PreparedStatement preparedStatement;

        try (Connection connection = DriverManager.getConnection(ConnectionProperties.connectionURL)) {
            Class.forName(ConnectionProperties.driverClass);
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(statement);

            preparedStatement.setString(1, "COMPLETED");
            preparedStatement.setString(2, jobContext.getJobName());

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
