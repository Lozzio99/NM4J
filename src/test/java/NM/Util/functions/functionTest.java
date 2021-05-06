package NM.Util.functions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.*;

class functionTest {

    @Test
    @DisplayName("test function")
    void F() {
        function<Double> ff = (t)-> pow(t,3);
        assertEquals(8.0,ff.f(2.));
    }

    @ParameterizedTest(name = "testing {0}")
    @ValueSource(strings = {"something","reaally","cool",",","","concat"})
    void testF(String s){
        function<String> fs = (string)-> string+string;
        assertEquals(s+s, fs.f(s));
    }


}