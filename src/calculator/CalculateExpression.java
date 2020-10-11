package calculator;

import model.Expression;

import java.util.Stack;

public class CalculateExpression {
    CalculateUtil calculateUtil = new CalculateUtil();

    /**
     * 计算表达式的答案
     *
     * @param expression 表达式
     */
    public void getExpressionAnswer(Expression expression) {
        // 获得逆波兰表达式
        String[] rpn = calculateUtil.getRPN(expression);
        expression.setAnswer(calculateRPN(rpn));
    }

    /**
     * 计算逆波兰表达式
     *
     * @param rpn 逆波兰表达式
     * @return 结果
     */
    public String calculateRPN(String[] rpn) {
        Stack<String> stack = new Stack<>();
        // 获得逆波兰表达式字符串数组的长度
        int length = calculateUtil.getLength(rpn);
        for (int i = 0; i < length; i++) {
            // 长度大于1，操作数入栈
            if (rpn[i].length() > 1) {
                stack.push(rpn[i]);
            } else {
                String leftOp = calculateUtil.getImproperFraction(stack.pop());
                String rightOp = calculateUtil.getImproperFraction(stack.pop());
                String result = calculateExp(rpn[i], leftOp, rightOp);
                if (result == null) {
                    return null;
                }
                stack.push(result);
            }
        }
        String answer = stack.pop();
        Integer newNumerator = Integer.parseInt(calculateUtil.getNumerator(answer));
        Integer lcm = Integer.parseInt(calculateUtil.getDenominator(answer));

        return calculateUtil.convertAnswerFormat(newNumerator, lcm);
    }

    /**
     * 计算逆波兰表达式的子式
     *
     * @param operator         操作符
     * @param leftOperatorNum  左操作数
     * @param rightOperatorNum 右操作数
     * @return 计算结果
     */
    public String calculateExp(String operator, String leftOperatorNum, String rightOperatorNum) {
        switch (operator) {
            case "+":
                return calculateUtil.add(leftOperatorNum, rightOperatorNum);
            case "-":
                return calculateUtil.sub(leftOperatorNum, rightOperatorNum);
            case "*":
                return calculateUtil.multiply(leftOperatorNum, rightOperatorNum);
            case "÷":
                return calculateUtil.divide(leftOperatorNum, rightOperatorNum);
            default:
                return null;
        }
    }
}