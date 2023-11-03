package com.example.polls.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(value = {"createdBy","updateBy"},allowGetters = true)
public abstract class UserDateAudit extends DateAudit {
    @CreatedBy
    @Column(updatable = false)
    private Long createdBy ;
    @LastModifiedBy
    private Long updateBy ;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}
