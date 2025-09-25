package app.api.Persistence.DTOS;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {
    private Long id;

    public BaseDTO() {}

    public BaseDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDTO baseDto = (BaseDTO) o;
        return id != null && id.equals(baseDto.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
