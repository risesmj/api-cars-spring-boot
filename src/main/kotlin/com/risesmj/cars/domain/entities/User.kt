package com.risesmj.cars.domain.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table
class User: UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private var mail: String? = null
    private var login: String? = null
    private var name: String? = null
    private var password: String? = null

    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name= "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name= "role_id", referencedColumnName = "id")]
    )
    @ManyToMany(fetch = FetchType.EAGER)
    var  roles: List<Role>? = null

    fun getMail() = mail
    override fun getAuthorities() = roles
    override fun getPassword() = this.password
    override fun getUsername() = login

    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
