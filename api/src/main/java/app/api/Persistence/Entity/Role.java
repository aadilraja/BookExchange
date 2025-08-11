package app.api.Persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name="name",length = 20)
    private ERole name;


    public Role() {}
    public Role(ERole name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public ERole getName()
    {
        return name;
    }
    public void setName(ERole name) {
        this.name = name;
    }

}
