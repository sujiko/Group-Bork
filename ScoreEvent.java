/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *
 * @author Qures
 */
public class ScoreEvent {

    int scoreToAdd;

    public void ScoreEvent(String number) {
        scoreToAdd = Integer.valueOf(number);
    }

    public void execute() {
        GameState state = GameState.Instance();
        state.addScore(scoreToAdd);
    }
}
