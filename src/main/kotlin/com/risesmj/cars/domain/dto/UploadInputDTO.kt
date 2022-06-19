package com.risesmj.cars.domain.dto

data class UploadInputDTO(
    var fileName: String? = null,
    var mimeType: String? = null,
    var base64: String? = null
)
