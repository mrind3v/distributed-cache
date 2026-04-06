public class UserProfile implements CacheValue{
    String name;
    String email;
    @Override
    public long getSizeBytes() {
        return this.name.length() + this.email.length();
    }

}
