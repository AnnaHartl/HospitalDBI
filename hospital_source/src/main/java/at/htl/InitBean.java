package at.htl;

import at.htl.control.ConditionRepository;
import at.htl.control.PatientRepository;
import at.htl.control.RoomRepository;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {
    private final RoomRepository roomRepository;
    private final ConditionRepository conditionRepository;
    private final PatientRepository patientRepository;
    private final EntityManager em;

    public InitBean(RoomRepository roomRepository, ConditionRepository conditionRepository, PatientRepository patientRepository, EntityManager em) {
        this.roomRepository = roomRepository;
        this.conditionRepository = conditionRepository;
        this.patientRepository = patientRepository;
        this.em = em;
    }

    @Transactional
    public void Init(@Observes StartupEvent event){
        //patientRepository.getConditionHistoryForPatient(1L);
    }
}
