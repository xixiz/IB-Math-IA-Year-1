/**
 *
 * Generates systems of equations with linear constants.
 *
 * @author Alvin Gao
 */
import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.form.output.OutputFormFactory;
import org.matheclipse.core.form.output.StringBufferWriter;
import org.matheclipse.core.interfaces.IExpr;

import edu.jas.kern.ComputerThreads;

import java.util.Random;

public class SysEqnGen1 {

    Random rand = new Random();

    public String[] genSysEqn() {
        /*
         * Randomly generates initial values and increments for both equations
         * independently, based on a pseudorandom algorithm.
         */
        int initValue1 = rand.nextInt(25)+1;
        int initValue2 = rand.nextInt(25)+1;

        int increment1 = rand.nextInt(10)+1;
        int increment2 = rand.nextInt(10)+1;

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
            increment1 = -increment1;
        }

        if(rand.nextBoolean()) {
            increment2 = -increment2;
        }

        /*
         * Creates system of equations with parameters specified.
         */

        int a1 = initValue1;
        int b1 = initValue1 + increment1;
        int a2 = initValue2;
        int b2 = initValue2 + increment2;

        int c1 = initValue1 + increment1 * 2;
        int c2 = initValue2 + increment2 * 2;

        String eqn1 = a1 + "*x+" + b1 + "*y==" + c1;
        String eqn2 = a2 + "*x+" + b2 + "*y==" + c2;

        String[] results = {eqn1, eqn2};

        return results;
    }

    public static void main(String[] args) {
        SysEqnGen1 gen = new SysEqnGen1();
        String[] test;
        String input = "";
        String output = "";
        IExpr result;

        F.initSymbols(null);
        EvalUtilities util = new EvalUtilities();

        for(int k = 0; k < 100; k++) {
            test = gen.genSysEqn();
            input = "Solve[{" + test[0] + "," + test[1] + "},{x,y}]";

            System.out.println("System Generated:");
            System.out.println(test[0]);
            System.out.println(test[1]);

            try {
                StringBufferWriter buf = new StringBufferWriter();
                result = util.evaluate(input);
                OutputFormFactory.get().convert(buf, result);
                output = buf.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Solutions: " + output);
            System.out.println();
        }

        ComputerThreads.terminate();
    }
}
