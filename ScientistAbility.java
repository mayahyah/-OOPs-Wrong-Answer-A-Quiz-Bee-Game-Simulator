public interface ScientistAbility {
    // Called each question 
    default void beforeQuestion(GameCharacter scientist) {}

    // After question is answered
    default void onCorrect(GameCharacter scientist) {}

    default void onWrong(GameCharacter scientist) {}

    String getAbilityName();
}
