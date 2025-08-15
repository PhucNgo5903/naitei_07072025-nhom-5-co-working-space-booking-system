package naitei.group5.workingspacebooking.entity;

import jakarta.persistence.*;
import lombok.*;
import naitei.group5.workingspacebooking.entity.enums.NotificationChannel;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    private String msg;

    @Enumerated(EnumType.STRING)
    private NotificationChannel channel;

    private LocalDateTime sentAt;

    private String status;
}
