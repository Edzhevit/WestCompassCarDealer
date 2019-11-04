package services.implementations;

import models.entities.Car;
import models.entities.Engine;
import models.service.CarCreateServiceModel;
import models.service.CarServiceModel;
import org.modelmapper.ModelMapper;
import services.CarsService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CarsServiceImpl implements CarsService {

    private final ModelMapper mapper;
    private final EntityManager entityManager;

    @Inject
    public CarsServiceImpl(ModelMapper mapper, EntityManager entityManager) {
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<CarServiceModel> getAll() {
        return entityManager.createQuery("SELECT c FROM Car AS c", Car.class)
                .getResultList()
                .stream()
                .map(car -> mapper.map(car, CarServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void create(String brand, String model, String year, String engine) {
        CarCreateServiceModel carModel = new CarCreateServiceModel();
        carModel.setBrand(brand);
        carModel.setModel(model);
        carModel.setYear(year);
        carModel.setEngine(engine);

        Car car = this.mapper.map(carModel, Car.class);

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();
    }
}
