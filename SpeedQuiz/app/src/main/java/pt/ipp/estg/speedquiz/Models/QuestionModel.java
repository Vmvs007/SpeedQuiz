package pt.ipp.estg.speedquiz.Models;

public class QuestionModel {

    private String question;
    private String optA;
    private String optB;
    private String optC;
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optA;
    }

    public void setOptionA(String optA) {
        this.optA = optA;
    }

    public String getOptionB() {
        return optB;
    }

    public void setOptionB(String optB) {
        this.optB = optB;
    }

    public String getOptionC() {
        return optC;
    }

    public void setOptionC(String optC) {
        this.optC = optC;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
