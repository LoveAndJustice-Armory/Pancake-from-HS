package tacos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class User implements UserDetails {  // UserDetails可以为框架提供更多信息，用户权限、账号是否可用等等
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String username;
    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    private final String province;
    private final String postCode;
    private final String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    // 返回用户被授予权限的集合
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {  // 账号没有过期？
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {   // 账号没有被锁定？
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // 凭证没有过期？
        return true;
    }

    @Override
    public boolean isEnabled() {    // 账号启用了？
        return true;
    }
}
