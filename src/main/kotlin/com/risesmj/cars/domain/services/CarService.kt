package com.risesmj.cars.domain.services

import com.risesmj.cars.api.exception.MyNotFoundException
import com.risesmj.cars.domain.dto.CarDTO
import com.risesmj.cars.domain.entities.Car
import com.risesmj.cars.domain.repositories.CarRepository
import org.modelmapper.internal.util.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class CarService {
    @Autowired(required = true)
    lateinit var repository: CarRepository

    fun getCars(): List<CarDTO> {
        return repository.findAll().stream().map { CarDTO.fromCar(it) }.toList()
    }

    fun getCarById(id: Long): CarDTO{
        return repository.findById(id).map { CarDTO.fromCar(it) }.orElseThrow{ MyNotFoundException() }
    }

    fun getCarByType(type: String): MutableList<CarDTO>{
        return repository.findByType(type).stream().map { CarDTO.fromCar(it) }.toList().toMutableList()
    }

    fun insert(car: Car): CarDTO{
        Assert.isNull(car.id)
        return CarDTO.fromCar(repository.save(car))
    }

    fun update(id: Long, car: Car): CarDTO{
        var optional = repository.findById(id)

        if(optional.isPresent){
            var db = optional.get()
            db.name = car.name
            db.type = car.type

            repository.save(db)

            return CarDTO.fromCar(db)
        }else{
            throw MyNotFoundException()
        }
    }

    fun delete(id: Long){
        repository.deleteById(id)
    }

    fun getCarsFake(): List<Car>{
        val car = mutableListOf<Car>()
        car.add(Car(1L,"Fusca"))
        car.add(Car(2L,"Brasília"))
        car.add(Car(3L,"Chevette"))
        return car
    }
}