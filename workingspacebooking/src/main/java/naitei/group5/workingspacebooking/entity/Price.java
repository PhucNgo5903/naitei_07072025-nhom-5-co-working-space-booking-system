package naitei.group5.workingspacebooking.entity;

import jakarta.persistence.*;
import lombok.*;
import naitei.group5.workingspacebooking.entity.enums.WeekDay;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Enumerated(EnumType.STRING)
    private WeekDay dayOfWeek;

    private LocalTime timeStart;

    private LocalTime timeEnd;

    private BigDecimal price;
}
