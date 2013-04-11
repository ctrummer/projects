package at.edu.hti.st.pathfinder;

public class SimplePathFinder extends AbstractPathFinder {

	private int[][] wM;

	@Override
	public void wrritePathToConsole(int[][] wMatrix) {
		this.wM = wMatrix;
		calculate(0, 0, Directions.START);
	}

	private int calculate(int i, int j, Directions direction) {
		if (i == wM.length && j == wM[wM.length].length)
			return wM[i][j];

		switch (direction) {
		case START:

			break;

		case FROM_LEFT:

			break;
		case FROM_TOP:
			break;
		case FROM_RIGTH:
			break;
		case FROM_BOTTOM:
			break;

		default:
			System.out.println("Unknown from where we are coming");
		}

		return -1;
	}

}
