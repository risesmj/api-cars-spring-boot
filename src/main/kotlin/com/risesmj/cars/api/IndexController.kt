package com.risesmj.cars.api

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @GetMapping
    fun root(): String{
        return "API Cars"
    }

    @GetMapping("/user/info")
    fun getUserInfo(@AuthenticationPrincipal user: UserDetails): UserDetails{
        return user
    }
}