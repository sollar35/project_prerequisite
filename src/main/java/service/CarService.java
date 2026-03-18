package service;

import model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    Car BMW = new Car("BMW", "M3 Competition", 2025);
    Car AUDI = new Car("AUDI", "RS6 Avant", 2025);
    Car MERCEDES = new Car("MERCEDES", "GLE 450", 2025);
    Car KIA = new Car("KIA", "EV6 GT", 2025);
    Car CHEVROLET = new Car("CHEVROLET", "Corvette Stingray", 2025);

    List<Car> list = List.of(BMW, AUDI, MERCEDES, KIA, CHEVROLET);

    public List<Car> getCars(int count) {
        if (count >= list.size() || count < 0) {
            return list;
        } else {
            return list.subList(0, count);
        }
    }

}
