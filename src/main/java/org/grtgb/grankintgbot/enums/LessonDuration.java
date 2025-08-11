package org.grtgb.grankintgbot.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
@Getter
public enum LessonDuration {
    ONE_HOUR(Duration.ofMinutes(60));

    private final Duration duration;


}
