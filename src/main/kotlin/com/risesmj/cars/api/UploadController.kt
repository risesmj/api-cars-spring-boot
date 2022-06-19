package com.risesmj.cars.api

import com.risesmj.cars.domain.dto.UploadInputDTO
import com.risesmj.cars.domain.dto.UploadOutputDTO
import com.risesmj.cars.domain.services.UploadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/upload")
class UploadController {

    @Autowired
    private lateinit var service: UploadService

    @PostMapping
    fun upload(@RequestBody uploadInputDTO: UploadInputDTO): ResponseEntity<UploadOutputDTO>{
        return ResponseEntity.ok(UploadOutputDTO(url = service.upload(uploadInputDTO)))
    }
}