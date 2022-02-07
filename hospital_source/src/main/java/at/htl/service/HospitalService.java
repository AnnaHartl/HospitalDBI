package at.htl.service;

import at.htl.control.ConditionRepository;
import at.htl.control.PatientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HospitalService {

    @Inject
    PatientRepository patientRepository;

    @Inject
    ConditionRepository conditionRepository;
}
