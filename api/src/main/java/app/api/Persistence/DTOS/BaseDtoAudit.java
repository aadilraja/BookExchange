package app.api.Persistence.DTOS;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class BaseDtoAudit extends BaseDTO implements Serializable {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseDtoAudit() {
        super();
    }

    public BaseDtoAudit(Long id) {
        super(id);
    }

    public BaseDtoAudit(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
