import java.util.Objects;

class UserKey implements CacheKey {
    private int userId;

    UserKey(int userId) {
        this.userId = userId;
    }

    public int hashCode() {
        return Objects.hash(userId);
    }

    public boolean equals(Object other) {
        if (!(other instanceof UserKey)) return false;
        return this.userId == ((UserKey) other).userId;
    }
}