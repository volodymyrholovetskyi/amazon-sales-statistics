package ua.vholovetskyi.amazonsalesstatistics.security.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
    private Set<UserRole> roles = new HashSet<>();


    public UserEntity(String firstName, String lastName, String email, String password, Set<UserRole> roles) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        this.roles=roles;
    }

}
