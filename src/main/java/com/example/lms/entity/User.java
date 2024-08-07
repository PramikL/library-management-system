package com.example.lms.entity;



import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames = "user_name",name="unique_username"))
@Entity
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns =@JoinColumn(name="user_id", referencedColumnName = "id",
                    foreignKey =  @ForeignKey(name = "fk_user")),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id",
                    foreignKey =  @ForeignKey(name = "fk_role")))
    private List<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}

