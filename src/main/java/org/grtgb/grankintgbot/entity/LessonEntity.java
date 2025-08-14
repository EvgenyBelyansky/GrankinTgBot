package org.grtgb.grankintgbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grtgb.grankintgbot.enums.LessonDuration;

import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "lesson")
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "lesson_start_date")
    private Instant lessonStartDate;

    @Column(name = "lesson_end_date")
    private Instant lessonEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public LessonEntity(Instant lessonStartDate, LessonDuration lessonDuration) {
        this.lessonStartDate = lessonStartDate;
        this.lessonEndDate = lessonStartDate.plus(lessonDuration.getDuration());
    }


}
