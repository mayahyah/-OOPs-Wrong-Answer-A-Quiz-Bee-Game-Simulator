import java.util.Random;

public class Darwin extends Scientists {

    private Random random = new Random();

    public Darwin() {
        super("Charles Darwin", 50, Ability.COPY_ABILITY,
              "Mimics a different scientist’s ability each question.");
    }

    @Override
    public void beforeQuestion(GameCharacter scientist) {
        // Darwin changes ability each question
        Ability[] list = {
            Ability.HINT,
            Ability.SKIP_QUESTION,
            Ability.HEAL
        };

        Ability newA = list[random.nextInt(list.length)];
        try {
            java.lang.reflect.Field f = Scientists.class.getDeclaredField("ability");
            f.setAccessible(true);
            f.set(this, newA);
        } 
        catch (Exception e) {}

        System.out.println("(Darwin mimics: " + getAbilityName() + ")");
    }

    @Override
    public String getAbilityName() {
        return "COPIED_" + super.getAbility().name();
    }
}
