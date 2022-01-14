package org.springframework.samples.petclinic.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedingService {

    private FeedingRepository feedingRepository;

    @Autowired
    public FeedingService(FeedingRepository feedingRepository) {
        this.feedingRepository = feedingRepository;
    }

    public List<Feeding> getAll(){
        return feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return feedingRepository.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        return feedingRepository.findFeedingTypeByName(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {

        FeedingType feedingType = p.getFeedingType();

        FeedingType feedingTypeR = feedingRepository.findFeedingTypeByName(feedingType.getName());

        if(!(feedingType.getPetType().equals(p.getPet().getType()))){
            throw new UnfeasibleFeedingException();
        }

        return feedingRepository.save(p);
    }

    
}
