package Error;

public class Error
{
    public static double absoluteError(double expected , double actual){
        return Math.abs(actual-expected);
    }

    public static double relativeError(double expected, double actual){
        return absoluteError(expected,actual)/ (Math.abs(expected));
    }

}
