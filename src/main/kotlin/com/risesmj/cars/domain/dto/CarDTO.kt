package com.risesmj.cars.domain.dto

import com.risesmj.cars.domain.entities.Car
import com.risesmj.cars.domain.entities.CarType
import org.modelmapper.ModelMapper




class CarDTO(
    var id: Long?,
    var name: String?,
    var type: CarType?){

    companion object{
        fun fromCar(c: Car): CarDTO{
            return CarDTO(id = c.id, name = c.name, type = c.type)
        }

        fun toCar(c: CarDTO): Car{
            return Car(id = c.id, name = c.name, type = c.type)
        }
    }
}