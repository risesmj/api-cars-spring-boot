package com.risesmj.cars.api

import com.risesmj.cars.domain.dto.CarDTO
import com.risesmj.cars.domain.entities.Car
import com.risesmj.cars.domain.services.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


/*
Annotations
RestController = Indica que é uma classe de Api
Verbose+Mapping = Indica a rota de acesso de cada endpoint de acordo com a verbose
Autowired = Indica que as depedências serão injetadas pelo spring boot

Classe DTO = Classe de response (retorno JSON) de uma entidade
ResponseEntity = Objeto de retorno para controle de status de código HTTP

Hierarquia
Controller > Service > Repository
 */


@RestController
@RequestMapping("/api/v1/car")
class CarController {
    @Autowired
    private lateinit var service: CarService

    @GetMapping
    fun get(): ResponseEntity<List<CarDTO>> {
        return ResponseEntity.ok(service.getCars())
    }

    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Long): ResponseEntity<CarDTO>{
         return ResponseEntity.ok(service.getCarById(id))
    }

    @GetMapping("/type/{type}")
    fun getCarByType(@PathVariable type: String): ResponseEntity<MutableList<CarDTO>>{
        var listType = service.getCarByType(type)
        return if(listType.isNotEmpty())
            ResponseEntity.ok(listType)
            else ResponseEntity.noContent().build()
    }

    @PostMapping
    fun post(@RequestBody car: Car): ResponseEntity<CarDTO>{
        var c = service.insert(car)
        return ResponseEntity.created(getUri(c.id)).build()
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: Long, @RequestBody car: Car) : ResponseEntity<CarDTO>{
        var carDTO = service.update(id, car)
        return ResponseEntity.ok(carDTO)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any>{
        service.delete(id)
        return ResponseEntity.ok().build()
    }

    private fun getUri(id: Long?) =
        ServletUriComponentsBuilder
        .fromCurrentRequestUri()
        .path("/{id}")
        .buildAndExpand(id)
        .toUri()
}