package NM.Integration;

import static java.lang.Math.abs;

public class RiemannSums
{
    double[] partitions;
    int n;
    double dx;
    public RiemannSums(int nodes){
        n = nodes;
    }

    private void partition(double a, double b) {
        double bounds = abs(b) + abs(a);
        dx = bounds/n;
        double xa = a;
        partitions = new double[n+1];
        partitions[0] = xa;
        for (int i = 1; i<= n; i++)
            partitions[i] = (xa+=dx);
    }

    public double[] getPartitions() {
        return partitions;
    }

    double integrate(double a, double b){
        partition(a,b);
        return 0;
    }

    public static void main(String[] args) {
        new RiemannSums(8).integrate(-2,2);
    }
}
