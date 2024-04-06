package hei.shool.transportationmanagementsystem.entities;

import hei.shool.transportationmanagementsystem.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class User extends Model implements Serializable, UserDetails {

    private String lastName;

    private String firstName;

    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String driverLicense;

    @Column(unique = true)
    private String email;

    private String photo;

    private String token;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserType> userTypes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userTypes.stream()
                .map(userType -> new SimpleGrantedAuthority(userType.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
