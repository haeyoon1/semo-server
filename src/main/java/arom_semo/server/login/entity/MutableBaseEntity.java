package arom_semo.server.login.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class MutableBaseEntity extends BaseEntity { //수정일자, 수정자를 위한 공통 엔티티
    @LastModifiedDate
    @Column(updatable = true)
    private LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column(updatable = true)
    private String modifiedBy;
}

