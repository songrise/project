/**
* -*- coding : utf-8 -*-
* @FileName  : LogicOpsFactory.java
* @Author    : Ruixiang JIANG (Songrise)
* @Time      : 2020-11-19
* @Github    ：https://github.com/songrise
* @Descriptions: Logic operations and their simple factory. https://medium.com/nestedif/java-simple-factory-pattern-9c2538dd0265
**/

package hk.edu.polyu.comp.comp2021.cvfs.model.operator;

/**
 *
 */
class LogicAndOperator extends ConcreteOperator {
    /**
    *
    */
    private static final long serialVersionUID = 2021L;
    /**
     *
     */
    LogicAndOperator() {
        super("&&");
    }

    @Override
    public boolean eval(Object operand1, Object operand2) {
        Boolean criA = (Boolean) operand1;
        Boolean criB = (Boolean) operand2;
        return criA && criB;
    }
}

/**
 *
 */
class LogicOrOperator extends ConcreteOperator {
    /**
    *
    */
    private static final long serialVersionUID = 2021L;
    /**
     *
     */
    LogicOrOperator() {
        super("||");
    }

    @Override
    public boolean eval(Object operand1, Object operand2) {
        Boolean criA = (Boolean) operand1;
        Boolean criB = (Boolean) operand2;
        return criA || criB;
    }
}

/**
 *
 */
class LogicNotOperator extends ConcreteOperator {
    /**
    *
    */
    private static final long serialVersionUID = 2021L;
    /**
     *
     */
    LogicNotOperator() {
        super("!");
    }

    @Override
    public boolean eval(Object operand1, Object operand2) {
        // in this case, operand2 are ommited since not is unary.
        Boolean criA = (Boolean) operand1;
        return !criA;
    }
}

/**
 * A simple factory class for logic operations.
 */
public class LogicOpsFactory {
    /**
     * @param opName name of the criterion
     * @return dispathed Operation object.
     */
    public static Operator createOperation(String opName) {
        switch (opName) {
            case "&&":
                return new LogicAndOperator();
            case "||":
                return new LogicOrOperator();
            case "!":
                return new LogicNotOperator();
        }
        throw new UnsupportedOperationException("Invalid Operation name: " + opName);
    }
}
