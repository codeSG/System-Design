package org.example.repositories;

import org.example.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video , Long> {
}
