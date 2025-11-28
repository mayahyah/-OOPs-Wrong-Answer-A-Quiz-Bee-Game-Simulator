public class Question {
    private String text;
    private String[] choices;
    private char correct;
    private String category;

    public Question(String text, String[] choices, char correct, String category) {
        this.text = text;
        this.choices = choices;
        this.correct = Character.toLowerCase(correct);
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public String[] getChoices() {
        return choices;
    }

    public char getCorrect() {
        return correct;
    }

    public String getCategory() {
        return category;
    }
}
