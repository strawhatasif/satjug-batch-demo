package com.fun.itemwriter;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ObjectUtils;

import javax.batch.api.chunk.ItemWriter;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class FileItemWriter<SettledAccount> implements ItemWriter {
    private static String filePath = "outputFile.txt";

    private FlatFileItemWriter<SettledAccount> writer;
    private ExecutionContext ec = null;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        ec = (ExecutionContext) checkpoint;

        if (ObjectUtils.isEmpty(ec))  ec = new ExecutionContext();

        writer = new FlatFileItemWriter<>();
        FileSystemResource resource = new FileSystemResource(filePath);

        writer.setAppendAllowed(true);
        writer.setResource(resource);
        writer.setLineAggregator(new PassThroughLineAggregator<>());
        writer.afterPropertiesSet();
        writer.open(new ExecutionContext());
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        List<SettledAccount> accounts =
                items.stream()
                .map(item -> (SettledAccount) item)
                .collect(Collectors.toList());

        writer.write(accounts);
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        //do nothing
        return null;
    }
}
