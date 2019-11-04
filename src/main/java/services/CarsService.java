package services;

import models.entities.Engine;
import models.service.CarServiceModel;

import java.util.List;

public interface CarsService {

    List<CarServiceModel> getAll();

    void create(String brand, String model, String year, String engine);
}
