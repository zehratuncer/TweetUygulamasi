import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Kullanici {
    private String username;
    private String email;
    private String password;
    private List<Tweet> tweets;
    private List<Kullanici> following;
    private List<Tweet> likedTweets;

    //Constructor
    public Kullanici(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.tweets = new ArrayList<>();
        this.following = new ArrayList<>();
        this.likedTweets = new ArrayList<>();
    }

    //Getter-Setter
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public List<Kullanici> getFollowing() {
        return following;
    }

    public List<Tweet> getLikedTweets() {
        return likedTweets;
    }


    //Fonksiyonlar
    public void postTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public void likeTweet(Tweet tweet) {
        tweet.like();
        likedTweets.add(tweet);
    }

    public void follow(Kullanici other) {
        if (!following.contains(other)) {
            following.add(other);
        }
    }

    @Override
    public String toString() {
        return username + " (" + email + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Kullanici other = (Kullanici) obj;
        return username.equals(other.username) && email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
