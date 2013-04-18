import objectdraw.*;
import java.util.Arrays;

public class RandArrays extends WindowController{
	public void begin(){

		RandomIntGenerator valueGen = new RandomIntGenerator(0, 51);

		int[] tempValues = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
				16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
				32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
				48, 49, 50, 51};

		int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
				17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32,
				33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
				49, 50, 51};

		int num = 0;
		int from = valueGen.nextValue();

		while (num < 52) {

			if (tempValues[from] != 60) {
				System.arraycopy(tempValues, from, values, num, 1);
				tempValues[from] = 60;
				from = valueGen.nextValue();
				num++;
			} else {
				from = valueGen.nextValue();
			}
		}
		

		String string = Arrays.toString(values);
		new Text(string, 10, 10, canvas);
		
	}

}
