import java.util.*;

public class OneriMotoru {

    public static List<Tweet> oneriTweetler(Kullanici kullanici, List<Tweet> allTweets) {

        Map<String, Integer> hashtagSayisi = new HashMap<>();

        // Kullanıcının attığı tweet'lerdeki hashtag'ler
        for (Tweet tweet : kullanici.getTweets()) {
            for (String tag : tweet.getHashtags()) {
                hashtagSayisi.put(tag, hashtagSayisi.getOrDefault(tag, 0) + 2); // 2 puan
            }
        }

        // Kullanıcının beğendiği tweet'lerdeki hashtag'ler
        for (Tweet tweet : kullanici.getLikedTweets()) {
            for (String tag : tweet.getHashtags()) {
                hashtagSayisi.put(tag, hashtagSayisi.getOrDefault(tag, 0) + 1); // 1 puan
            }
        }

        // Puanları kullanarak ilgisini çekebilecek tweet'leri seç
        List<Tweet> oneriler = new ArrayList<>();

        for (Tweet tweet : allTweets) {
            if (tweet.getAuthor().equals(kullanici)) continue; // Kendinin tweet’i olmasın

            for (String tag : tweet.getHashtags()) {
                if (hashtagSayisi.containsKey(tag)) {
                    oneriler.add(tweet);
                    break;
                }
            }
        }

        return oneriler;
    }
}

