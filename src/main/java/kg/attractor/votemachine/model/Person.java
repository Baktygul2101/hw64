package kg.attractor.votemachine.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document(collection="person")
@Data
public class Person implements UserDetails {
    @Id
    @Getter
    @Setter
    public String id;
    @Getter
    @Setter
    public String email;
    @Getter
    @Setter
    public String name;
    @Getter
    @Setter
    public String login;
    @Setter
    public String password;

    public String getPassword() {
        //return new BCryptPasswordEncoder().encode(this.password);
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public Collection<? extends GrantedAuthority>
    getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_FULL"));
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

    public Person(String email, String name, String login, String password) {
        this.email = email;
        this.name = name;
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
        //this.password = password;
    }
}