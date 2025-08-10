package app.api.Service.mapper;

import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.Entity.ERole;
import app.api.Persistence.Entity.Role;
import app.api.Persistence.Entity.User;
import app.api.Persistence.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
     private final RoleRepo roleRepo;

     @Autowired
     public UserMapper(RoleRepo roleRepo) {
         this.roleRepo = roleRepo;
     }

    User toEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(userCreateDto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        Set<Role> roles = userCreateDto.getRole()
                .stream()
                .map(roleString -> {
                    ERole eRole = ERole.valueOf(roleString);
                    return roleRepo.findByName(eRole)
                            .orElseThrow(() -> new RuntimeException("Role doesn't exist: " + eRole));
                })
                .collect(Collectors.toSet());

        user.setRoles(roles);

        return user;

    }
}
