package telran.propets.hotels.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.propets.hotels.entities.HotelEntity;

//@Repository
public interface HotelsRepository extends MongoRepository<HotelEntity, String> {

}
