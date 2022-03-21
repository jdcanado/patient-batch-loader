package com.pluralsight.springbatch.patientbatchloader.step;

import com.pluralsight.springbatch.patientbatchloader.domain.PatientEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PatientRowMapper implements RowMapper<PatientEntity> {

    @Override
    public PatientEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        PatientEntity patient = new PatientEntity();
        patient.setId(rs.getLong("patient_id"));

        return patient;
    }

}
