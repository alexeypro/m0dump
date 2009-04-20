package ${groupId}.models;

import ${groupId}.base.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

@Entity
@Table(name="users")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_login")
    private String login;

    @Column(name="user_password")
    private String password;

    @Column(name="user_active")
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // next are the ones required for Spring Security

    // all users are in ROLE_ANONYMOUS if they are inactive
    // otherwise they all are considered logged in ROLE_USER
    public GrantedAuthority[] getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(1);
        if (this.getIsActive()) {
            authorities.add(new GrantedAuthorityImpl(Constants.ROLE_USER));
        } else {
            authorities.add(new GrantedAuthorityImpl(Constants.ROLE_ANONYMOUS));
        }
        return authorities.toArray(new GrantedAuthority[authorities.size()]);
    }

    public String getUsername() {
        return this.login;
    }

    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    public boolean isCredentialsNonExpired() {
        return this.isActive;
    }

    public boolean isEnabled() {
        return this.isActive;
    }
}
