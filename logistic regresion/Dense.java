import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import java.util.Random;
import cern.colt.function.DoubleDoubleFunction;
import cern.colt.function.DoubleFunction;
import cern.jet.math.Functions;
//http://geek.csdn.net/news/detail/56086
class Dense{
	int input_dim;
	int output_dim;
	DoubleMatrix2D W;
	DoubleMatrix2D b;
	double e = 2.718281828459;
	double momentum = 0.8;
	double alpha = 0.15;
    DoubleDoubleFunction plus = new DoubleDoubleFunction(){
        public double apply(double a, double b){ return a + b; }
    };

    DoubleFunction sigmoid = new DoubleFunction(){
    	
        public double apply(double x){ return 1 / (Math.pow( e, -x) + 1); }
    };
    
	public Dense(int _input_dim, int _output_dim){
		input_dim = _input_dim;
		output_dim = _output_dim;
		W = new DenseDoubleMatrix2D( _output_dim , _input_dim);// input * output
		b = new DenseDoubleMatrix2D( 1, _output_dim );
		W.assign(0.1);
		b.assign(0);
		//Random r = new Random();
	}
	public DoubleMatrix2D forward(DoubleMatrix2D x){
		DoubleMatrix2D re = Algebra.DEFAULT.mult(x, Algebra.DEFAULT.transpose(W) );
		re.assign(b, plus);
		//return re.assign(b, plus);
		return re.assign(sigmoid);
	}

	public double getLoss(DoubleMatrix2D y, DoubleMatrix2D y_pre){
		double J = 0;
		for(int i = 0 ; i < input_dim ; i++){
			J += y.get(0, i) * Math.log(y_pre.get(0, i)) + (1 - y.get(0, i)) * Math.log(1 - y_pre.get(0, i));
		}
		return J;
	}
	public void update(DoubleMatrix2D x, DoubleMatrix2D y, DoubleMatrix2D y_pre){
		double new_w;
		for(int i = 0 ; i < input_dim ; i++){
			new_w = W.get(0, i) - alpha * (y_pre.get(0, i) - y.get(0, i) ) * x.get(0, i);
			W.set(0, i, new_w);
		}
		
	}
	public void train(DoubleMatrix2D x, DoubleMatrix2D y){
		DoubleMatrix2D y_pre = forward(x);
		System.out.println(getLoss(y, y_pre));
		update(x, y, y_pre);
	}
}
/*
double scalar_to_add  = 0.5;
DoubleMatrix2D matrix = new DenseDoubleMatrix2D(10, 10); // creates an empty 10x10 matrix
matrix.assign(DoubleFunctions.plus(scalar_to_add));
*/