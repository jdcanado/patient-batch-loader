package com.pluralsight.springbatch.patientbatchloader;

import com.pluralsight.springbatch.patientbatchloader.config.ApplicationProperties;
import com.pluralsight.springbatch.patientbatchloader.config.DefaultProfileUtil;
import com.pluralsight.springbatch.patientbatchloader.domain.PatientEntity;
import com.pluralsight.springbatch.patientbatchloader.step.PatientCursorItemReader;
import com.pluralsight.springbatch.patientbatchloader.step.PatientItemProcessor;
import com.pluralsight.springbatch.patientbatchloader.step.PatientItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableBatchProcessing
//@EnableConfigurationProperties({ LiquibaseProperties.class, ApplicationProperties.class })
public class VenusApplication {
    //private static final Logger log = LoggerFactory.getLogger(VenusApplication.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public static void main(String[] args) {
        SpringApplication.run(VenusApplication.class, args);
    }

    @Bean("patientJob")
    public Job buildPatientJob(final Step patientStep) {
        return jobBuilderFactory.get("patientJob")
            .incrementer(new RunIdIncrementer())
            .flow(patientStep)
            .end()
            .build();
    }

    @Bean("patientStep")
    public Step buildPatientStep(PatientCursorItemReader patientItemReader, PatientItemProcessor patientItemProcessor,
                                 PatientItemWriter patientItemWriter) {
         return stepBuilderFactory.get("patientStep")
            .<PatientEntity, PatientEntity>chunk(10)
            .reader(patientItemReader)
            .processor(patientItemProcessor)
            .writer(patientItemWriter)
            .build();
    }
}
