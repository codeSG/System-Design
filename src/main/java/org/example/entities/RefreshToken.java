package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="refresh_token" , indexes = {
  @Index(name="idx_token" , columnList = "token" , unique = true),
        @Index(name = "idx_userId" , columnList = "userId"),
})
@Getter
@Setter
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId" , nullable = false)
    private User user;

    @Column(nullable = false , unique = true)
    private String token;

//    @Column(nullable = true)
//    private String family;

//    @Column(nullable = false)
//    private Boolean used = false;

    @Column(nullable = false)
    private Instant expiresAt;

//    @Column(nullable = false)
//    private Instant absoluteExpiry;

    private String userAgent;

    private String userIp;

    //constructor
    public RefreshToken(User user, String token, Instant expiresAt) {
        this.user = user;
        this.token = token;
        this.expiresAt = expiresAt;
    }
    
}
