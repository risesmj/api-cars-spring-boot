package com.risesmj.cars

import com.risesmj.cars.domain.dto.CarDTO
import com.risesmj.cars.domain.entities.Car
import com.risesmj.cars.domain.entities.CarType
import com.risesmj.cars.domain.services.CarService
import org.junit.jupiter.api.Test
import org.modelmapper.internal.util.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CarsApplicationTests {

	@Autowired
	private lateinit var service: CarService;

	@Test
	fun insertCar(){
		val car = Car(
			name = "Kwid Test",
			type = CarType.CLASSIC
		)

		//Create object database
		val carDTO: CarDTO = service.insert(car)
		Assert.notNull(carDTO)

		var id = carDTO.id
		Assert.notNull(id)

		var op = service.getCarById(id as Long)
		Assert.notNull(op)

		var c = op
		Assert.isTrue(c.name.equals(car.name))
		Assert.isTrue(c.type.hashCode() == car.type.hashCode())
	}

	fun deleteCar(){
		val id: Long = 0
		//Delete object
		service.delete(id)
		Assert.notNull(service.getCarById(id))

	}

	@Test
	fun getCarById(){
		var id: Long = 28

		var car = service.getCarById(id)
		Assert.notNull(car)
		Assert.isTrue(car is CarDTO)
	}

	@Test
	fun getCars(){
		var list = service.getCars()

		Assert.notNull(list)
		Assert.isTrue(list.isNotEmpty())
	}

	@Test
	fun contextLoads() {
	}

}
