import java.util.*;

public class Main {

    private static List<Kullanici> users = new ArrayList<>();
    private static List<Tweet> tweetler = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Kullanici gecerli_kullanici = null;


    public static void main(String[] args) {

        int secim;
        do {
            menuyuGoster();
            secim = scanner.nextInt();
            scanner.nextLine(); // yeni satır
            switch (secim) {
                case 1 -> kayitOl();
                case 2 -> girisYap();
                case 3 -> sifreYenile();
                case 4 -> TweetAt();
                case 5 -> TweetBegen();
                case 6 -> kullaniciTakipEt();
                case 7 -> tumTweetleriGoster();
                case 8 -> onerileriGoster();
                case 9 -> cikisYap();
                case 0 -> System.out.println("Görüşürüz, yine gel!👑");
                default -> System.out.println("Geçerli bir seçim yap!🤨");
            }
        } while (secim != 0);
    }

    private static void menuyuGoster() {
        System.out.println("""
                \n--- Twitter Ana Menü ---
                1. Kayıt Ol
                2. Giriş Yap
                3. Şifremi Unuttum
                4. Tweet At
                5. Tweet Beğen
                6. Kullanıcı Takip Et
                7. Tüm Tweet'leri Görüntüle
                8. Bana Öneri Göster
                9. Çıkış
                0. Programı Sonlandır
                Seçimin: """);
    }

    //Kayıt Olma Fonksiyonu
    public static void kayitOl() {
        if (gecerli_kullanici != null) {
            System.out.println("Zaten '" + gecerli_kullanici.getUsername() + "' giriş yapmış durumdasın.");
            return;
        }

        System.out.print("Kullanıcı adını gir: ");
        String username = scanner.nextLine();

        System.out.print("E-posta adresini gir: ");
        String email = scanner.nextLine();

        if (!email.contains("@")) {
            System.out.println("❌ Geçersiz e-posta. Lütfen geçerli bir e-posta gir (örn: ornek@gmail.com)");
            return;
        }

        for (Kullanici user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("❌ Bu kullanıcı adı zaten alınmış, başka bir tane dene.");
                return;
            }
        }

        System.out.print("Şifre belirle: ");
        String password = scanner.nextLine();

        Kullanici yeniKullanici = new Kullanici(username, email, password);
        users.add(yeniKullanici);
        gecerli_kullanici = yeniKullanici;

        System.out.println("✅ Kayıt başarılı ve giriş yapıldı. Hoş geldin " + gecerli_kullanici.getUsername() + "!💖");
    }


    //Giriş Yapma Fonksiyonu
    public static void girisYap() {
        if (gecerli_kullanici != null) {
            System.out.println("Zaten giriş yapmışsın: " + gecerli_kullanici.getUsername());
            return;
        }

        System.out.print("Kullanıcı adını gir: ");
        String username = scanner.nextLine();

        System.out.print("Şifreni gir: ");
        String password = scanner.nextLine();

        for (Kullanici user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                gecerli_kullanici = user;
                System.out.println("✅ Giriş başarılı, hoş geldin " + gecerli_kullanici.getUsername() + "!🎉");
                return;
            }
        }

        System.out.println("❌ Giriş başarısız. Kullanıcı adı veya şifre hatalı.😢");
    }

    //Tweet Atma Fonksiyonu
    private static void TweetAt() {
        if (gecerli_kullanici == null) {
            System.out.println("Önce giriş yap.💁‍♀️");
            return;
        }

        System.out.print("Tweet içeriği: ");
        String content = scanner.nextLine();
        System.out.print("Hashtag'leri boşlukla ayır (örn: yapayzeka programlama): ");
        String tagsLine = scanner.nextLine();
        List<String> hashtags = Arrays.asList(tagsLine.split(" "));

        Tweet tweet = new Tweet(content, hashtags, gecerli_kullanici);
        gecerli_kullanici.postTweet(tweet);
        tweetler.add(tweet);
        System.out.println("Tweet başarıyla atıldı.🎉");
    }

    //Tweet Beğenme Fonksiyonu
    private static void TweetBegen() {
        if (gecerli_kullanici == null) {
            System.out.println("Önce giriş yap.😇");
            return;
        }

        tumTweetleriGoster();
        System.out.print("Beğenmek istediğin tweet'in numarasını yaz: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < tweetler.size()) {
            Tweet tweet = tweetler.get(index);
            gecerli_kullanici.likeTweet(tweet);
            System.out.println("Tweet beğenildi!❤️");
        } else {
            System.out.println("Geçersiz tweet numarası...");
        }
    }

    //Kullanıcı Takip Etme Fonksiyonu
    private static void kullaniciTakipEt() {
        if (gecerli_kullanici == null) {
            System.out.println("Önce giriş yapman gerek.💁‍♂️");
            return;
        }

        System.out.println("Takip edebileceğin kullanıcılar:");
        for (int i = 0; i < users.size(); i++) {
            Kullanici kullanici = users.get(i);
            if (!kullanici.equals(gecerli_kullanici)) {
                System.out.println(i + ". " + kullanici.getUsername());
            }
        }

        System.out.print("Takip etmek istediğin kullanıcı numarasını yaz: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < users.size() && !users.get(index).equals(gecerli_kullanici)) {
            gecerli_kullanici.follow(users.get(index));
            System.out.println("Artık onu takip ediyorsun!💫");
        } else {
            System.out.println("Geçersiz seçim");
        }
    }

    //Tüm Tweetleri Gösterme Fonksiyonu
    private static void tumTweetleriGoster() {
        System.out.println("\n--- Tüm Tweet'ler ---");
        for (int i = 0; i < tweetler.size(); i++) {
            System.out.println(i + ". " + tweetler.get(i));
        }
    }

    //Öneri Tweetleri Gösterme Fonksiyonu
    private static void onerileriGoster() {
        if (gecerli_kullanici == null) {
            System.out.println("Önce giriş yap.😊");
            return;
        }

        List<Tweet> oneri = OneriMotoru.oneriTweetler(gecerli_kullanici, tweetler);

        if (oneri.isEmpty()) {
            System.out.println("Şu an sana özel bir öneri yok...✨");
        } else {
            System.out.println("\n--- Sana Önerilen Tweet'ler ---");
            for (Tweet tweet : oneri) {
                System.out.println(tweet);
            }
        }
    }

    //Çıkış Yapma Fonksiyonu
    public static void cikisYap() {
        if (gecerli_kullanici == null) {
            System.out.println("Zaten giriş yapmamışsın.😉");
        } else {
            System.out.println("Çıkış yapıldı: Görüşürüz " + gecerli_kullanici.getUsername() + " 👋");
            gecerli_kullanici = null;
        }
    }

    //Şifreyi Yenileme Fnksiyonu
    public static void sifreYenile() {
        System.out.print("E-posta adresinizi girin: ");
        String email = scanner.nextLine();

        for (Kullanici user : users) {
            if (user.getEmail().equals(email)) {
                System.out.print("Yeni şifrenizi girin: ");
                String newPassword = scanner.nextLine();
                user.setPassword(newPassword);
                System.out.println("✅ Şifre başarıyla güncellendi.");
                return;
            }
        }

        System.out.println("❌ Bu e-posta ile kayıtlı kullanıcı bulunamadı.");
    }

}
