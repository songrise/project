package hk.edu.polyu.comp.comp2021.cvfs.model.operation;

public interface Operation {
    String getName();
    boolean eval(Object operand1, Object operand2);
}