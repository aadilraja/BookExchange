package app.api.Security;

import app.api.Persistence.Entity.Role;
import app.api.Persistence.Entity.User;
import app.api.Persistence.Repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo UserRepo;

    @Autowired
    public MyUserDetailsService(UserRepo UserRepo) {
        this.UserRepo = UserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = UserRepo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new MyUserDetails(user);

    }

    private static List<GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }
}
