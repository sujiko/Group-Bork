package GroupBork;

/**
 * @author elliot
 */
public class ScoreCommand extends Command
{
	private int currentScore;

	public HealthCommand()
	{
		this.currentScore = GameState.Instance().getSore();
	}

	@Override
	String execute()
	{
		return "Your score is " + currentScore;
	}

}
