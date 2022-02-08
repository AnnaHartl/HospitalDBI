package at.htl.service;

import at.htl.control.BedRepository;
import at.htl.entity.Bed;
import at.htl.entity.BedPatient;
import at.htl.entity.Patient;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class BedService {

    private final BedRepository bedRepository;

    public BedService(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    public void addBed(Bed bed){
        bedRepository.addBed(bed);
    }

    public void deleteBed(Long id){
        bedRepository.deleteBed(id);
    }

    public Bed updateBed(Bed bed){
        return bedRepository.updateBed(bed);
    }

    public List<Bed> getAllBeds(){
        return bedRepository.getAllBeds();
    }

    public Bed findBedById(Long id){
        return bedRepository.findBedById(id);
    }

    public BedPatient addBedForPatient(Bed b, Patient p, LocalDateTime from, LocalDateTime to) {
        return bedRepository.addBedForPatient(b, p,from,to);
    }

    public BedPatient findBedPatient(Patient p, Bed b) {
        return bedRepository.findBedPatient(p, b);
    }

    public List<Bed> findAvailableBeds(Long stationId, Long roomTypeId, LocalDateTime from, LocalDateTime to) {
        return bedRepository.findAvailableBeds(stationId,roomTypeId,from,to);
    }

    public List<Bed> getAllBedsByRoomId(Long id) {
        return bedRepository.getAllBedsByRoomId(id);
    }
}
