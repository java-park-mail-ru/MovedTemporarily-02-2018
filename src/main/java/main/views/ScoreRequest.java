package main.views;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class ScoreRequest {
    private Integer position;
    private Integer count;

    public ScoreRequest(@JsonProperty("position") Integer position,
                        @JsonProperty("count") Integer count) {
        this.position = position;
        this.count = count;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
