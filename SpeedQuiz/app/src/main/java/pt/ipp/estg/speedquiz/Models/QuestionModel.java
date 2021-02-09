package pt.ipp.estg.speedquiz.Models;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions_table")
public class QuestionModel {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @Nullable
    @ColumnInfo(name = "question")
    private String question;

    @Nullable
    @ColumnInfo(name = "optA")
    private String optA;

    @ColumnInfo(name = "optB")
    private String optB;

    @Nullable
    @ColumnInfo(name = "optC")
    private String optC;

    @Nullable
    @ColumnInfo(name = "answer")
    private String answer;

    @Nullable
    @ColumnInfo(name = "points")
    private int points;

    public QuestionModel ( String question, String optA, String optB, String optC, String answer, int points) {
        this.question = question;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.answer = answer;
        this.points = points;
    }


    public QuestionModel () {
    }


    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getQuestion () {
        return question;
    }

    public void setQuestion (String question) {
        this.question = question;
    }

    public String getOptA () {
        return optA;
    }

    public void setOptA (String optA) {
        this.optA = optA;
    }

    public String getOptB () {
        return optB;
    }

    public void setOptB (String optB) {
        this.optB = optB;
    }

    public String getOptC () {
        return optC;
    }

    public void setOptC (String optC) {
        this.optC = optC;
    }

    public String getAnswer () {
        return answer;
    }

    public void setAnswer (String answer) {
        this.answer = answer;
    }

    public int getPoints () {
        return points;
    }

    public void setPoints (int points) {
        this.points = points;
    }

    @Override
    public String toString () {
        return "QuestionModel{" +
                "id_question=" + id +
                ", question='" + question + '\'' +
                ", optA='" + optA + '\'' +
                ", optB='" + optB + '\'' +
                ", optC='" + optC + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                '}';
    }
}
