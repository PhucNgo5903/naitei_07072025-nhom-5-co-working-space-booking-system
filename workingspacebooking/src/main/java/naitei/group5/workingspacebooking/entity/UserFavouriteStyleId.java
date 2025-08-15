package naitei.group5.workingspacebooking.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserFavouriteStyleId implements Serializable {

    private Long user;
    private Long style;

    public UserFavouriteStyleId() {
    }

    public UserFavouriteStyleId(Long user, Long style) {
        this.user = user;
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFavouriteStyleId that = (UserFavouriteStyleId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(style, that.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, style);
    }
}
