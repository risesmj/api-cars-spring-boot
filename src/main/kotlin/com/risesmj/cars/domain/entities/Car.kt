package com.risesmj.cars.domain.entities

import javax.persistence.*

@Entity
@Table
data class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var urlPhoto: String? = null,
    var urlVideo: String? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    @Enumerated(EnumType.STRING)
    var type: CarType? = null,
)

enum class CarType{
    CLASSIC,
    LUXURY,
    SPORTS
}