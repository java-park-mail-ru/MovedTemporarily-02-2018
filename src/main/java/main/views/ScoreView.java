package main.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.models.User;

import javax.validation.constraints.NotNull;
import java.util.Comparator;

@SuppressWarnings("unused")
public class ScoreView {

    private @NotNull String login;
    private @NotNull Integer score;

    public ScoreView(@JsonProperty("login") String login,
                     @JsonProperty("score") Integer score) {
        this.login = login;
        this.score = score;
    }

    public ScoreView(User user) {
        this.login = user.getLogin();
        this.score = user.getScore();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public static class ScoreComparator implements Comparator<ScoreView> {
        @Override
        public int compare(ScoreView first, ScoreView second) {
            return Integer.compare(second.score, first.score);
        }
    }
}
