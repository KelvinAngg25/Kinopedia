package Kinopedia.minigames.DinoRun;

/**
 * Mengelola sistem koin untuk DinoRun.
 *
 * Aturan pemberian koin:
 *   - Victory (score >= 100) : score + 20 bonus = 120 koin
 *   - Game Over (score < 100): score koin (1 score = 1 koin)
 *   - EXIT kapanpun          : 0 koin
 */
import Kinopedia.DataUser;
import Kinopedia.Session;

public class CoinManager {

    // Singleton agar saldo koin bisa diakses dari mana saja
    private static CoinManager instance;

    private static int totalCoins = 0;

    // Bonus tambahan saat menang
    public static final int WIN_BONUS = 20;

    private CoinManager() {

    }

    public static CoinManager getInstance() {
        if (instance == null) {
            instance = new CoinManager();
        }
        return instance;
    }

    /**
     * Hitung koin yang didapat dari satu sesi permainan.
     *
     * @param score    skor akhir pemain
     * @param isVictory true jika pemain menang (score >= WIN_SCORE)
     * @return jumlah koin yang didapat sesi ini
     */
    public static int calculateCoins(
            int score,
            boolean isVictory
    ) {

        if (isVictory) {
            return score + WIN_BONUS;
        }

        return score;
    }

    /**
     * Tambahkan koin ke total setelah sesi selesai (Game Over / Victory).
     *
     * @param score     skor akhir pemain
     * @param isVictory apakah pemain menang
     * @return koin yang baru ditambahkan
     */
    public static int awardCoins(
            int score,
            boolean isVictory
    ) {

        int earned =
                calculateCoins(score, isVictory);

        totalCoins += earned;

        // Tambahkan ke akun user
        DataUser user =
                Session.getInstance().getCurrentUser();

        if (user != null) {

            user.setKoin(
                    user.getKoin() + earned
            );
        }

        return earned;
    }

    /** EXIT tidak memberi koin — panggil ini saat pemain menekan EXIT. */
    public void exitWithoutReward() {
        // Tidak ada perubahan saldo
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    /** Untuk keperluan integrasi dengan sistem Kinopedia (simpan ke profil, dll.) */
    public void setTotalCoins(int coins) {
        this.totalCoins = coins;
    }
}