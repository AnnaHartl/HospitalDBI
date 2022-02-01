package at.htl.service;

import at.htl.control.StationRepository;
import at.htl.entity.Room;
import at.htl.entity.Station;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public void addStation(Station station){
        stationRepository.addStation(station);
    }

    public void deleteStation(Long id){
        stationRepository.deleteStation(id);
    }

    public Station updateStation(Station station){
        return stationRepository.updateStation(station);
    }

    public List<Station> getAllStations(){
        return stationRepository.getAllStations();
    }

    public Station findStationById(Long id){
        return stationRepository.findStationById(id);
    }
}
