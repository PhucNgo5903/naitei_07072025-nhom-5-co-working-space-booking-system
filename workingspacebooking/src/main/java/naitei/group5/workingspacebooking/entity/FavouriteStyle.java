package naitei.group5.workingspacebooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "favourite_styles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavouriteStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "favouriteStyle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StyleMatch> styleMatches;

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFavouriteStyle> userFavouriteStyles;
}
