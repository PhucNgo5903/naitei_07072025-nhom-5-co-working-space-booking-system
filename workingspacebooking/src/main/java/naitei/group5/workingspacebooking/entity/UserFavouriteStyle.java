package naitei.group5.workingspacebooking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_favourite_styles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserFavouriteStyleId.class)
public class UserFavouriteStyle {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id", nullable = false)
    private FavouriteStyle style;

    private Boolean isPrimary;
}
