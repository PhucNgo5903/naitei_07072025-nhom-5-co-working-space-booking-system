package naitei.group5.workingspacebooking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "style_matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(StyleMatchId.class)
public class StyleMatch {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favourite_style_id", nullable = false)
    private FavouriteStyle favouriteStyle;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_style_id", nullable = false)
    private VenueStyle venueStyle;
}
