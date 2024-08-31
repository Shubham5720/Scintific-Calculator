package calculator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class CalculatorEngine {
    public double evaluate(String expression) {
        return evalExpression(expression);
    }

    private double evalExpression(String expr) {
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            return ((Number) engine.eval(expr)).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}