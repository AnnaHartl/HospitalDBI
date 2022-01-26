package at.htl;

import at.htl.control.ConditionRepository;
import at.htl.control.RoomRepository;
import at.htl.entity.Condition;
import at.htl.entity.Room;
import at.htl.entity.RoomType;
import at.htl.entity.Symptom;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {
    private final RoomRepository roomRepository;
    private final ConditionRepository conditionRepository;
    private final EntityManager em;

    public InitBean(RoomRepository roomRepository, ConditionRepository conditionRepository, EntityManager em) {
        this.roomRepository = roomRepository;
        this.conditionRepository = conditionRepository;
        this.em = em;
    }

    @Transactional
    public void Init(@Observes StartupEvent event){
        Room room1 = new Room(10, 10, new RoomType("BigRoom"));
        Room room2 = new Room(69, 69, new RoomType("SmallRoom"));

        roomRepository.addRoom(room1);
        roomRepository.addRoom(room2);

        Condition c = new Condition();
        c.setDescription("c");
        c.setName("c");

        Symptom s = new Symptom();
        s.setName("s");

        //c.addSymptom(s,true);

        em.persist(s);
        em.persist(c);

        c.addSymptom(s, true);

    }
}
