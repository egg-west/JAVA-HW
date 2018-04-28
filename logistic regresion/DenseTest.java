import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import java.util.Random;
import cern.colt.function.DoubleDoubleFunction;

class DenseTest{
	public static void main(String[] args) {
		double[][][] x_array = new double[][][]{
			{{1, 0}}, {{0, 1}}, {{1, 1}}, {{0, 0}}
		};
		double[][][] y_array = new double[][][]{
			{{0}}, {{0}}, {{1}}, {{0}}
		};
		DoubleMatrix2D x = new DenseDoubleMatrix2D(1, 2);
		
		DoubleMatrix2D y = new DenseDoubleMatrix2D(1, 1);
		Dense d = new Dense(2, 1);
		for( int i = 0 ; i < 100 ; i++){
			x.assign( x_array[i % 4]);
			y.assign( y_array[i % 4]);
			d.train(x, y);
		}
	}
}