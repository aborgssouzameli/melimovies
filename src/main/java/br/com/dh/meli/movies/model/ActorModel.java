package br.com.dh.meli.movies.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "actor")
public class ActorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60, nullable = false)
    private String first_name;

    @Column(length = 60, nullable = false)
    private String last_name;

    @Column(insertable = true, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created_at;

    @Column(insertable = true, updatable = true, nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date updated_at;

    @Column(nullable = true)
    private Double rating;

    @PrePersist
    private void onCreate() {
        Date now = new Date();
        created_at = now;
        updated_at = now;
    }
}
