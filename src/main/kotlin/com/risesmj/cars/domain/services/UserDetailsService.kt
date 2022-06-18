package com.risesmj.cars.domain.services

import com.risesmj.cars.domain.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service(value = "userDetailsService")
class UserDetailsService: UserDetailsService {

    @Autowired
    private lateinit var repository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        return repository.findByLogin(username) ?: throw UsernameNotFoundException("user not found")
    }
}