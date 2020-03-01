package com.fun.itemreader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ObjectUtils;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;
import java.io.Serializable;

public class FileItemReader<T> implements ItemReader {
    @BatchProperty
    @Inject
    String inputFilePath;

    @BatchProperty
    @Inject
    String fullyQualifiedTargetClass;

    @BatchProperty
    @Inject
    String delimiter = ";";

    @BatchProperty
    @Inject
    String fieldNames;

    private FlatFileItemReader<T> reader;
    private ExecutionContext ec;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        ec = (ExecutionContext) checkpoint;

        if (ObjectUtils.isEmpty(ec))  ec = new ExecutionContext();

        reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(inputFilePath));
        reader.setLineMapper(setupLineMapper());
        reader.afterPropertiesSet();
        reader.open(ec);
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }

    @Override
    public Object readItem() throws Exception {
        return reader.read();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }

    protected DefaultLineMapper setupLineMapper() throws ClassNotFoundException {

        DefaultLineMapper lineMapper = new DefaultLineMapper();
        BeanWrapperFieldSetMapper<Object> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        fieldSetMapper.setTargetType(Class.forName(fullyQualifiedTargetClass));

        tokenizer.setDelimiter(delimiter);
        tokenizer.setNames(fieldNames.split(delimiter));

        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(tokenizer);
        return lineMapper;
    }
}
