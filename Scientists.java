public class Scientists extends GameCharacter {

    public enum Ability {
        HINT,            // Galileo – gives hints for Astronomy
        SKIP_QUESTION,   // Newton – can skip Physics questions (2 per level)
        COPY_ABILITY,    // Darwin – copies random ability each QUESTION
        REDUCE_QUESTION, // Freud – reduces total questions by 5 each level
        EXTRA_LIFE,      // Curie – adds +30 HP at start of each level
        HEAL,            // Lovelace – +5 HP on correct answer
        NONE
    }

    private Ability ability;
    private String description;

    // Per-level values
    private int skipUses = 2;                // Newton: 2 skips per level
    private boolean extraLifeGranted = false; // Curie: only once per level

    public Scientists(String name, int health, Ability ability, String description) {
        super(name, health);
        this.ability = ability;
        this.description = description;
    }

    public Ability getAbility() {
        return ability;
    }

    public String getDescription() {
        return description;
    }

    // --- Newton SKIPS ---
    public int getSkipUses() {
        return skipUses;
    }

    public void useSkip() {
        if (skipUses > 0) skipUses--;
    }

    // --- Curie EXTRA LIFE FLAG ---
    public boolean isExtraLifeGrantedThisLevel() {
        return extraLifeGranted;
    }

    public void setExtraLifeGranted(boolean val) {
        this.extraLifeGranted = val;
    }

    // --- Reset system per level ---
    public void resetPerLevelFlags() {
        this.skipUses = 2;        // Newton resets
        this.extraLifeGranted = false; // Curie resets
    }
}
