package com.pluralsight.springbatch.patientbatchloader.step;

import com.pluralsight.springbatch.patientbatchloader.domain.PatientEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientItemProcessor implements ItemProcessor<PatientEntity, PatientEntity> {
    @Override
    public PatientEntity process(final PatientEntity patient) {
        patient.setId(Optional.ofNullable(patient.getId()).orElse(null));

        return patient;
    }
}
