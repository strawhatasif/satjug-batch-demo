package com.fun.itemreader;

import com.fun.CorrectSpellingRowMapper;
import com.fun.misc.DataSourceFactory;
import com.fun.model.SettledAccount;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.util.ObjectUtils;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;
import java.io.Serializable;

public class JdbcReader implements ItemReader {

    @BatchProperty
    @Inject
    String sqlStatement;

    private ExecutionContext ec;
    private JdbcCursorItemReader<SettledAccount> reader;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        ec = (ExecutionContext) checkpoint;

        if (ObjectUtils.isEmpty(ec))  ec = new ExecutionContext();
        reader = new JdbcCursorItemReader<>();
        reader.setRowMapper(new CorrectSpellingRowMapper());
        reader.setDataSource(DataSourceFactory.getDataSource());
        reader.setSql(sqlStatement);
        reader.afterPropertiesSet();
        reader.open(ec);
    }

    @Override
    public Object readItem() throws Exception {
        return reader.read();
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
