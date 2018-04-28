This is an implement of logistic regression on JAVA based on colt

### Prerequisite

```
colt // a java library for science computing
```







## Colt Usage

- scalar add

```java
import cern.jet.math.Functions;

System.out.println(matrix.assign( Functions.plus(0.5)) );
```

- matrix add

```java
import cern.colt.function.DoubleDoubleFunction;
DoubleDoubleFunction plus = new DoubleDoubleFunction(){
	public double apply(double a, double b){ return a+b; }
};
System.out.println(matrix.assign(matrix, plus ) );
```



- transpose

```java
import cern.colt.matrix.linalg.Algebra;

DoubleMatrix2D re = Algebra.DEFAULT.mult(x, Algebra.DEFAULT.transpose(W) );
```



- general operation

**DoubleDoubeleFunction ** is a binary function that needs 2 parameters, while DoubeleFunction is an unary function requires 1 parameters. In these 2 function you must rewrite the apply function.

It can be applied on assign.



Notice that the *parameter* must be **DoubleMatrix** rather than **DenseDoubleMatrix**

```java
public DoubleMatrix2D forward(DoubleMatrix2D x){
  DoubleMatrix2D re = Algebra.DEFAULT.mult(x, Algebra.DEFAULT.transpose(W));
  return re.assign(b, plus);
}
```

