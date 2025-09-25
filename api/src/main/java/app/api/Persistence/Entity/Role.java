package app.api.Persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role extends BaseEntityAudit {


    @Enumerated(EnumType.STRING)
    @Column(name="name",length = 20)
    private ERole name;


    public Role() {
        super();
    }
    public Role(ERole name) {
        this.name = name;
    }
    public ERole getName()
    {
        return name;
    }
    public void setName(ERole name) {
        this.name = name;
    }

}
