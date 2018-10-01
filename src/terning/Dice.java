package terning;

public class Dice {
	// roll the die and return the value (1-6)
	public int roll() {
		//Math Random returns in the interval [0,1[
		int number = (int)(Math.random()*6);
		return number+1;
			}

	//Testkommentar
	// hej

	// roll the die n times and print the values
	public void rollMultiple(int n) {
		for (int i=1; i<=n; i++) {
			System.out.print(roll() + " ");
		}		
	}
}
