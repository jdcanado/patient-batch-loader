package com.pluralsight.springbatch.patientbatchloader.step;

import com.pluralsight.springbatch.patientbatchloader.domain.PatientEntity;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class PatientCursorItemReader extends JdbcCursorItemReader<PatientEntity> {
    private static final String SQL_COMMAND = "SELECT patient_id FROM patient ORDER BY patient_id";

    public PatientCursorItemReader(final DataSource dataSource, final PatientRowMapper patientRowMapper) {
        this.setDataSource(dataSource);
        this.setRowMapper(patientRowMapper);
        this.setSql(SQL_COMMAND);
    }
}
