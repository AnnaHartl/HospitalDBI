package at.htl.service;

import at.htl.control.BedRepository;
import at.htl.entity.Bed;
import at.htl.entity.BedPatient;
import at.htl.entity.Patient;
import at.htl.entity.Room;

import javax.enterprise.context.ApplicationScoped;
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

    public BedPatient addBedForPatient(Bed b, Patient p) {
        return bedRepository.addBedForPatient(b, p);
    }
}
