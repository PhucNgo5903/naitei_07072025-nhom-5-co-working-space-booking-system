package naitei.group5.workingspacebooking.entity;

import jakarta.persistence.*;
import lombok.*;
import naitei.group5.workingspacebooking.entity.enums.AccountRecoveryStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_recoveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRecovery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "recovery_token", nullable = false)
    private String recoveryToken;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    @Enumerated(EnumType.STRING)
    private AccountRecoveryStatus status;
}
