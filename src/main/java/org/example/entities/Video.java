package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.constants.VideoVisibility;

import java.time.Instant;

@Entity
@Table(name="video")
@NoArgsConstructor
@Getter
public class Video {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="userId" , nullable = false)
//    private User user;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    private String description;

    @Setter
    @Column(nullable = false)
    private String videoUrl; //storage key

    @Setter
    private String thumbnailUrl;

    @Setter
    @Enumerated(EnumType.STRING)
    private VideoVisibility visibility;

    @Column(nullable = false)
    private Instant createdAt;

    @Setter
    private Long viewCount;

    @PrePersist
    private void setCreatedAt(){
        this.createdAt = Instant.now();
    }

    public Video(String title , String videoUrl , VideoVisibility visibility ){
        this.title = title;
        this.videoUrl = videoUrl;
        this.visibility = visibility;
    }


}
