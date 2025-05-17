import java.util.List;

public class Tweet {

    private String icerik;
    private List<String> hashtags;
    private int begenme_sayisi;
    private Kullanici yazar;

    //Constructor
    public Tweet(String icerik, List<String> hashtags, Kullanici yazar) {
        this.icerik = icerik;
        this.hashtags = hashtags;
        this.yazar = yazar;
        this.begenme_sayisi = 0;
    }

    //Getter-Setter
    public String getContent() {
        return icerik;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public int getLikeCount() {
        return begenme_sayisi;
    }

    public Kullanici getAuthor() {
        return yazar;
    }

    public void like() {
        begenme_sayisi++;
    }

    @Override
    public String toString() {
        return yazar.getUsername() + ": " + icerik + "\n❤️" + begenme_sayisi + "\nHashtagler: " + hashtags;
    }
}
