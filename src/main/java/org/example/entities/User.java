package org.example.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false , unique = true)
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String password; // store encrypted password

    @Column(nullable = false , updatable = false)
    private Instant createdAt;

    @PrePersist
    private void setCreatedAt(){
        this.createdAt = Instant.now();
    }

}
