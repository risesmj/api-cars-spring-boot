package com.risesmj.cars.domain.repositories

import com.risesmj.cars.domain.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User,Long> {

    fun findByLogin(login: String?): User?
}