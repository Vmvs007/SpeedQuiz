package pt.ipp.estg.speedquiz.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Ranking {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private int qi;

    @Ignore
    public Ranking() {
    }

    public Ranking(int id, String username, int qi) {
        this.id = id;
        this.username = username;
        this.qi = qi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQi() {
        return qi;
    }

    public void setQi(int qi) {
        this.qi = qi;
    }
}
