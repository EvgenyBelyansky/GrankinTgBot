package org.grtgb.grankintgbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class LessonDto {

    private String lastName;

    private LocalDate lessonDate;

    private LocalTime lessonTime;
}
