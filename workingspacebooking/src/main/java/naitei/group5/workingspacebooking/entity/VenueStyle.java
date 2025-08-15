package naitei.group5.workingspacebooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "venue_styles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "venueStyle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StyleMatch> styleMatches;

    @OneToMany(mappedBy = "venueStyle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venue> venues;
}
