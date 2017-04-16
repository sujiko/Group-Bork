package GroupBork;

/**
 * @author elliot
 */
public class ScoreCommand extends Command
{
	private int currentScore;

	public ScoreCommand()
	{
		this.currentScore = GameState.Instance().getScore();
	}
	String execute()
	{
		return "Your score is " + currentScore;
	}

}
