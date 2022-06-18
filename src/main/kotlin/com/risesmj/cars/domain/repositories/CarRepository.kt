package com.risesmj.cars.domain.repositories

import com.risesmj.cars.domain.entities.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository: JpaRepository<Car, Long>{
    fun findByType(type: String): MutableList<Car>
}