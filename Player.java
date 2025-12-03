public class Player {
    private int score = 0;
    private int unlockedLevels = 1;
    private Scientists scientist;

    public int getScore() { 
        return score; 
    }
    
    public void addScore(int amount) { 
        score += amount; 
    }

    public int getUnlockedLevels() { 
        return unlockedLevels; 
    }

    public boolean isUnlocked(int level) {
        return level <= unlockedLevels;
    }

    public void unlock(int level) {
        if (level > unlockedLevels) unlockedLevels = level;
    }

    public void setScientist(Scientists s) {
        this.scientist = s;
    }

    public Scientists getScientist() {
        return scientist;
    }
}

