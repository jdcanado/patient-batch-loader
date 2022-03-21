package com.pluralsight.springbatch.patientbatchloader.step;

import com.pluralsight.springbatch.patientbatchloader.domain.PatientEntity;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class PatientItemWriter extends FlatFileItemWriter<PatientEntity> {
    public PatientItemWriter() {
        this.setResource(new FileSystemResource("patient.csv"));
        this.setHeaderCallback(writer -> writer.write("patient_id"));
        BeanWrapperFieldExtractor<PatientEntity> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[]{"patient_id"});
        DelimitedLineAggregator<PatientEntity> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        this.setLineAggregator(lineAggregator);
    }
}
