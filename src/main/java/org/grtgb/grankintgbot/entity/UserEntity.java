package org.grtgb.grankintgbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grtgb.grankintgbot.enums.RegistrationState;
import org.grtgb.grankintgbot.enums.UserState;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private Long chatId;

    @OneToMany(mappedBy = "user")
    private List<LessonEntity> lessons;

    @Column(name = "login_date")
    private Instant loginDate;

    @Enumerated(value = EnumType.STRING)
    private UserState userState;

    @Enumerated(value = EnumType.STRING)
    private RegistrationState userRegistrationState;

    @Builder
    public UserEntity(String firstName, String lastName, Long chatId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.chatId = chatId;
        this.loginDate = Instant.now();
        this.userState = UserState.DEFAULT;
        this.userRegistrationState = RegistrationState.UNREGISTERED;
    }

}
