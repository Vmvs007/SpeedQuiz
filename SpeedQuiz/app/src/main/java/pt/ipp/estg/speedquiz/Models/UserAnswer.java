package pt.ipp.estg.speedquiz.Models;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class UserAnswer {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String question;
    private String answer;
    private float points;

    public UserAnswer (int id, String username, String question, String answer, float points) {
        this.id = id;
        this.username = username;
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

@Ignore
    public UserAnswer () {
    }


    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getQuestion () {
        return question;
    }

    public void setQuestion (String question) {
        this.question = question;
    }

    public String getAnswer () {
        return answer;
    }

    public void setAnswer (String answer) {
        this.answer = answer;
    }

    public float getPoints () {
        return points;
    }

    public void setPoints (float points) {
        this.points = points;
    }
}
