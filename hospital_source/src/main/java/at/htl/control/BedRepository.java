package at.htl.control;

import at.htl.entity.Bed;
import at.htl.entity.BedPatient;
import at.htl.entity.Patient;
import at.htl.id.BedPatientId;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class BedRepository {
    private final EntityManager entityManager;

    public BedRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Bed> getAllBeds(){
        return entityManager.createQuery("Select b from Bed b", Bed.class)
                .getResultList();
    }

    @Transactional
    public void addBed(Bed bed){
        entityManager.persist(bed);
    }

    @Transactional
    public void deleteBed(Long id){
        entityManager.remove(findBedById(id));
    }

    public Bed findBedById(Long id){
        return entityManager.createQuery("Select b from Bed b where b.id = :id", Bed.class)
                .setParameter("id", id).getResultList()
                .stream().findFirst().orElse(null);
    }

    @Transactional
    public Bed updateBed(Bed bed){
        return entityManager.merge(bed);
    }

    @Transactional
    public BedPatient addBedForPatient(Bed b, Patient p) {
        BedPatient bp = new BedPatient();
        BedPatientId id = new BedPatientId();
        id.setPatient(p);
        id.setBed(b);
        bp.setId(id);
        bp.setFromDateTime(LocalDateTime.now());

        return entityManager.merge(bp);
    }

    public BedPatient findBedPatient(Patient p, Bed b) {
        return entityManager.createQuery("Select bp from BedPatient bp where bp.id.bed = :bed and bp.id.patient = :patient"
                        , BedPatient.class)
                .setParameter("bed", b)
                .setParameter("patient", p).getResultList()
                .stream().findFirst().orElse(null);
    }

    public List<Bed> findAvailableBeds(Long stationId, Long roomTypeId, LocalDateTime from, LocalDateTime to) {
        var availableBeds = entityManager
                .createQuery("select b from Bed b " +
                        "where b.room.station.id = :STATION_ID " +
                        "and b.room.roomType.id = :ROOMTYPE_ID " +
                        "and not b.id in " +
                        "(select bp.id.bed.id from b.patients bp " +
                        "where :FROM < coalesce(bp.toDateTime,:MAX_TIMESTAMP) and :TO > bp.fromDateTime)", Bed.class)
                .setParameter("STATION_ID",stationId)
                .setParameter("ROOMTYPE_ID",roomTypeId)
                .setParameter("FROM", from)
                .setParameter("TO", to)
                .setParameter("MAX_TIMESTAMP",
                        LocalDateTime.of(9999,12,31,23,59,59))
                .getResultList();

        /*var x = entityManager.createQuery("select bp.id.bed.id from Bed b join b.patients bp " +
                        "where :FROM < coalesce(bp.toDateTime,:MAX_TIMESTAMP) and :TO > bp.fromDateTime")
                .setParameter("FROM", from)
                .setParameter("TO", to)
                .setParameter("MAX_TIMESTAMP",LocalDateTime.of(9999,12,31,23,59,59))
                .getResultList();*/

        System.out.println(availableBeds);
        return availableBeds;

    }

    public List<Bed> getAllBedsByRoomId(Long id) {
        return entityManager.createQuery("Select b from Bed b where b.room.id = :id", Bed.class)
                .setParameter("id", id).getResultList();
    }
}
