/**
 *
 * Generates 3x3 systems of equations with linear constants.
 *
 * @author Alvin Gao
 */

import java.util.Random;
import org.jlinalg.Matrix;
import org.jlinalg.rational.Rational;

public class SysEqnGen2 {

    Random rand = new Random();

    public Rational[][] genSysEqn() {
        /*
         * Randomly generates initial values and increments for all three
         * equations independently, based on a pseudorandom algorithm.
         */
        int initValue1 = rand.nextInt(50)+1;
        int initValue2 = rand.nextInt(50)+1;
        int initValue3 = rand.nextInt(50)+1;

        int increment1 = rand.nextInt(20)+1;
        int increment2 = rand.nextInt(20)+1;
        int increment3 = rand.nextInt(20)+1;

        /*
         * Randomly determines whether initial values and increments will be
         * negative or positive.
         */

        if(rand.nextBoolean()) {
            initValue1 = -initValue1;
        }

        if(rand.nextBoolean()) {
            initValue2 = -initValue2;
        }

        if(rand.nextBoolean()) {
            initValue3 = -initValue3;
        }

        if(rand.nextBoolean()) {
            increment1 = -increment1;
        }

        if(rand.nextBoolean()) {
            increment2 = -increment2;
        }

        if(rand.nextBoolean()) {
            increment3 = -increment3;
        }

        /*
         * Creates system of equations with parameters specified.
         */

        int a1 = initValue1;
        int b1 = initValue1 + increment1;
        int c1 = initValue1 + increment1 * 2;

        int a2 = initValue2;
        int b2 = initValue2 + increment2;
        int c2 = initValue2 + increment2 * 2;

        int a3 = initValue3;
        int b3 = initValue3 + increment3;
        int c3 = initValue3 + increment3 * 2;

        int d1 = initValue1 + increment1 * 3;
        int d2 = initValue2 + increment2 * 3;
        int d3 = initValue3 + increment3 * 3;

        Rational[][] results = {
            {Rational.FACTORY.get(a1), Rational.FACTORY.get(b1),
                     Rational.FACTORY.get(c1), Rational.FACTORY.get(d1)},
            {Rational.FACTORY.get(a2), Rational.FACTORY.get(b2),
                     Rational.FACTORY.get(c2), Rational.FACTORY.get(d2)},
            {Rational.FACTORY.get(a3), Rational.FACTORY.get(b3),
                     Rational.FACTORY.get(c3), Rational.FACTORY.get(d3)}
        };

        return results;
    }

    private String[] matrixToSystem3x3(Rational[][] M) {
        String eqn1 = M[0][0] + "*x+" + M[0][1] + "*y+" + M[0][2] +
                "*z==" + M[0][3];
        String eqn2 = M[1][0] + "*x+" + M[1][1] + "*y+" + M[1][2] +
                "*z==" + M[1][3];
        String eqn3 = M[2][0] + "*x+" + M[2][1] + "*y+" + M[2][2] +
                "*z==" + M[2][3];

        String[] results = {eqn1, eqn2, eqn3};

        return results;
    }

    public static void main(String[] args) {
        SysEqnGen2 gen = new SysEqnGen2();
        String[] test;

        Rational[][] matrix3x3;

        for(int k = 0; k < 100; k++) {
            matrix3x3 = gen.genSysEqn();
            test = gen.matrixToSystem3x3(matrix3x3);

            System.out.println("System Generated:");
            System.out.println(test[0]);
            System.out.println(test[1]);
            System.out.println(test[2]);

            Matrix result = new Matrix<Rational>(matrix3x3);

            System.out.println("Solutions: ");
            System.out.println(result.gaussjord());
            System.out.println();
        }
    }
}
