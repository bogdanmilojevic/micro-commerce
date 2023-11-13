package com.microcommerce.userservice.data.entity;

import com.microcommerce.userservice.data.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "t_role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Override
    public String getAuthority() {
        return authority.toString();
    }

    public Role(String authority) {
        this.authority = Authority.valueOf(authority);
    }
}
