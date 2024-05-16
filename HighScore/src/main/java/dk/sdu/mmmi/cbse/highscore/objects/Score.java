package dk.sdu.mmmi.cbse.highscore.objects;

import lombok.Data;

import java.util.Date;

@Data
public class Score {
    private Date date;
    private double score;
    private String playerName;

    public Score() {}

    public Score(Date date, double score, String playerName) {
        this.date = date;
        this.score = score;
        this.playerName = playerName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
