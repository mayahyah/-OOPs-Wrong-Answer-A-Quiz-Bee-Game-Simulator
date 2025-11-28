public class Scientists extends GameCharacter implements ScientistAbility {

    public enum Ability {
        HINT,
        SKIP_QUESTION,
        COPY_ABILITY,
        REDUCE_QUESTION,
        EXTRA_LIFE,
        HEAL,
        NONE
    }

    private Ability ability;
    private String description;

    private int skipUses = 2;
    private boolean extraLifeGranted = false;

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

    public int getSkipUses() {
        return skipUses;
    }

    public void useSkip() {
        if (skipUses > 0) skipUses--;
    }

    public boolean isExtraLifeGrantedThisLevel() {
        return extraLifeGranted;
    }

    public void setExtraLifeGranted(boolean val) {
        this.extraLifeGranted = val;
    }

    public void resetPerLevelFlags() {
        this.skipUses = 2;
        this.extraLifeGranted = false;
    }

    @Override
    public String getAbilityName() {
        return ability.name();
    }
}
