package com.fun.misc;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    public static DataSource getDataSource() {
        SQLServerDataSource sqlServerDataSource = new SQLServerDataSource();

        sqlServerDataSource.setURL(ConnectionProperties.connectionURL);
        return sqlServerDataSource;
    }
}
