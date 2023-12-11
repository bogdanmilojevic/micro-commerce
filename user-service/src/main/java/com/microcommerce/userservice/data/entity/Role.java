package com.microcommerce.userservice.data.entity;

import com.microcommerce.userservice.data.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;

    @Override
    public String getAuthority() {
        return authority.toString();
    }

    public Role(String authority) {
        this.authority = Authority.valueOf(authority);
    }
}
