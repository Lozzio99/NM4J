package NM.Util.functions;

public interface Fx<E> {
    E f_x(E x);

    default E fGx(Fx<E> g, E x) {
        return f_x(g.f_x(x));
    }

    default double derivative(double x, double h) {
        return (f(x + h) - f(x)) / h;
    }

    @SuppressWarnings({"unchecked", "unsafe"})
    default double f(double x) {
        return ((Fx<Double>) this).f_x(x);
    }


}
