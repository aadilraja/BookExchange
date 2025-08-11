package app.api.Service.mapper;

import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
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

   public User toEntity(UserCreateDto userCreateDto) {
         if(userCreateDto == null) {
             return null;
         }
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(userCreateDto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRoles(convertStringToRoles(userCreateDto.getRole()));

        return user;

    }
    public UserDto toDto(User user) {
         if(user == null) {
             return null;
         }
         UserDto userDto = new UserDto();
         userDto.setId(user.getId());
         userDto.setName(user.getName());
         userDto.setEmail(user.getEmail());
         userDto.setUpdatedAt(LocalDateTime.now());
         userDto.setCreatedAt(user.getCreatedAt());
         userDto.setRoles(convertRolesToString(user.getRoles()));


         return userDto;

    }






    private Set<String> convertRolesToString(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().toString())
                .collect(Collectors.toSet());
    }
    private Set<Role> convertStringToRoles(Set<String> roleNames) {
        return roleNames.stream()
                .map(roleString -> {
                    ERole eRole = ERole.valueOf(roleString);
                    return roleRepo.findByName(eRole)
                            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + eRole));
                })
                .collect(Collectors.toSet());
    }

}
