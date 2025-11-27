import java.util.ArrayList;
import java.util.List;

public class Player {
    private Scientists scientist;
    private int score = 0;

    // unlocked levels 1–20 (index 0 unused)
    private boolean[] unlocked = new boolean[21];

    public Player(Scientists scientist) {
        this.scientist = scientist;

        // Only Level 1 unlocked initially (per requirement)
        unlocked[1] = true;
    }

    public Scientists getScientist() {
        return scientist;
    }

    public void setScientist(Scientists s) {
        this.scientist = s;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int s) {
        this.score += s;
    }

    public boolean isUnlocked(int level) {
        if (level < 1 || level > 20) return false;
        return unlocked[level];
    }

    public void unlock(int level) {
        if (level >= 1 && level <= 20) {
            unlocked[level] = true;
        }
    }

    public List<Integer> getUnlockedLevels() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            if (unlocked[i]) list.add(i);
        }
        return list;
    }
}
