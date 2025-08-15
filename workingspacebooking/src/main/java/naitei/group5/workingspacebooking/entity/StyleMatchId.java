package naitei.group5.workingspacebooking.entity;

import java.io.Serializable;
import java.util.Objects;

public class StyleMatchId implements Serializable {

    private Long favouriteStyle;
    private Long venueStyle;

    public StyleMatchId() {
    }

    public StyleMatchId(Long favouriteStyle, Long venueStyle) {
        this.favouriteStyle = favouriteStyle;
        this.venueStyle = venueStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StyleMatchId that = (StyleMatchId) o;
        return Objects.equals(favouriteStyle, that.favouriteStyle) &&
                Objects.equals(venueStyle, that.venueStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favouriteStyle, venueStyle);
    }
}
