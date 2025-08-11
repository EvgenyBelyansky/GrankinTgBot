package org.grtgb.grankintgbot.service;

import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.dto.LessonDto;
import org.grtgb.grankintgbot.entity.LessonEntity;
import org.grtgb.grankintgbot.mappers.LessonMapper;
import org.grtgb.grankintgbot.repository.LessonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

//    public void registrationNewLesson(LessonDto lessonDto) {
//
//        LessonEntity lesson = LessonMapper.fromDtoToLessonEntity(lessonDto);
//        lessonRepository.save(lesson);
//    }
}
