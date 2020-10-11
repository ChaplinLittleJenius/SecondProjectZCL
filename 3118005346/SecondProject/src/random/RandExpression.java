package random;

import model.Expression;

public class RandExpression {
    RandElement randElement = new RandElement();

    /**
     * 随机生成表达式
     *
     * @param range 随机数范围
     * @return 表达式
     */
    public Expression getExpression(int range) {
        // 操作符数目 0:一个 1:两个 2:三个 3:四个
        int operatorNum = (int) (Math.random() * 3);
        if (operatorNum == 0) {
            return getOneOperatorExpression(range, 0, 1);
        } else if (operatorNum == 1) {
            // sign：0:两个操作符不带括号 1:两个操作符带左括号 2:两个操作符带右括号
            int sign = (int) (Math.random() * 3);
            if (sign == 0) {
                return getTwoOperatorExpression(range, 0, 1);
            } else if (sign == 1) {
                return getTwoOperatorExpressionWithLeftBrackets(range, 0, 1);
            } else {
                return getTwoOperatorExpressionWithRightBrackets(range, 0, 1);
            }
        } else {
            // sign：0:三个操作符不带括号 1:三个操作符带左括号 2:三个操作符带右括号
            int sign = (int) (Math.random() * 3);
            if (sign == 0) {
                return getThreeOperatorExpression(range, 0, 1);
            } else if (sign == 1) {
                return getThreeOperatorExpressionWithLeftBrackets(range, 0, 1);
            } else {
                return getThreeOperatorExpressionWithRightBrackets(range, 0, 1);
            }
        }
    }

    /**
     * 生成单操作符表达式
     *
     * @param range         随机数范围
     * @param numIndex      操作数在表达式字符串数组中的位置
     * @param operatorIndex 运算符在表达式字符串数组中的位置
     * @return 表达式
     */
    public Expression getOneOperatorExpression(int range, int numIndex, int operatorIndex) {
        Expression expression = new Expression();
        String operator = randElement.getOperatorChar();
        String leftOperatorNumber = randElement.getOperatorNum(range);
        String rightOperatorNumber = randElement.getCorrectOperatorNum(operator, randElement.getOperatorNum(range), range);
        expression.getOperatorNum()[numIndex] = leftOperatorNumber;
        expression.getOperatorNum()[numIndex + 1] = rightOperatorNumber;
        expression.getOperator()[operatorIndex] = operator;
        return expression;
    }

    /**
     * 生成一个带括号的单操作符表达式
     *
     * @param range         随机数范围
     * @param numIndex      操作数在表达式字符串数组中的位置
     * @param operatorIndex 运算符在表达式字符串数组中的位置
     * @return 表达式
     */
    public Expression getOneOperatorExpressionWithBrackets(int range, int numIndex, int operatorIndex) {
        Expression expression = getOneOperatorExpression(range, numIndex, operatorIndex);
        expression.getOperator()[operatorIndex - 1] = "(";
        expression.getOperator()[operatorIndex + 1] = ")";
        return expression;
    }

    /**
     * 生成带两个操作符表达式
     *
     * @param range         随机数范围
     * @param numIndex      操作数在表达式字符串数组中的位置
     * @param operatorIndex 运算符在表达式字符串数组中的位置
     * @return 表达式
     */
    public Expression getTwoOperatorExpression(int range, int numIndex, int operatorIndex) {
        Expression expression = getOneOperatorExpression(range, numIndex, operatorIndex);
        // 生成一个随机的操作符
        String operator = randElement.getOperatorChar();
        // 生成一个符合操作要求的随机的操作数
        String thirdOperatorNumber = randElement.getCorrectOperatorNum(operator, randElement.getOperatorNum(range), range);
        expression.getOperatorNum()[numIndex + 2] = thirdOperatorNumber;
        expression.getOperator()[operatorIndex + 1] = operator;
        return expression;
    }

    /**
     * 生成带左括号的两个操作符表达式
     *
     * @param range         随机数范围
     * @param numIndex      操作数在表达式字符串数组中的位置
     * @param operatorIndex 运算符在表达式字符串数组中的位置
     * @return 表达式
     */
    public Expression getTwoOperatorExpressionWithLeftBrackets(int range, int numIndex, int operatorIndex) {
        Expression expression = getOneOperatorExpressionWithBrackets(range, numIndex, operatorIndex);
        String operator = randElement.getOperatorChar();
        expression.getOperator()[operatorIndex + 1] += operator;
        String thirdNum = randElement.getCorrectOperatorNum(operator, randElement.getOperatorNum(range), range);
        expression.getOperatorNum()[numIndex + 2] = thirdNum;
        return expression;
    }

    public Expression getTwoOperatorExpressionWithRightBrackets(int range, int numIndex, int operatorIndex) {
        Expression expression = getOneOperatorExpressionWithBrackets(range, numIndex + 1, operatorIndex + 1);
        String operator = randElement.getOperatorChar();
        expression.getOperator()[operatorIndex] = (operator += expression.getOperator()[operatorIndex]);
        String thirdNumb = randElement.getCorrectOperatorNum(operator, randElement.getOperatorNum(range), range);
        expression.getOperatorNum()[numIndex] = thirdNumb;
        return expression;
    }

    /**
     * 生成带大括号两个操作符表达式
     *
     * @param range         随机数范围
     * @param numIndex      操作数在表达式字符串数组中的位置
     * @param operatorIndex 运算符在表达式字符串数组中的位置
     * @return 表达式
     */
    public Expression getTwoOperatorExpressionWithMidBrackets(int range, int numIndex, int operatorIndex) {
        Expression expression = getTwoOperatorExpression(range, numIndex, operatorIndex);
        expression.getOperator()[operatorIndex - 1] = "(";
        expression.getOperator()[operatorIndex + 2] = ")";
        return expression;
    }

    /**
     * 生成带三个操作符表达式
     *
     * @param range         随机数范围
     * @param numIndex      操作数在表达式字符串数组中的位置
     * @param operatorIndex 运算符在表达式字符串数组中的位置
     * @return 表达式
     */
    public Expression getThreeOperatorExpression(int range, int numIndex, int operatorIndex) {
        Expression expression = getTwoOperatorExpression(range, numIndex, operatorIndex);
        String operator = randElement.getOperatorChar();
        String thirdOperatorNumber = randElement.getCorrectOperatorNum(operator, randElement.getOperatorNum(range), range);
        expression.getOperatorNum()[numIndex + 3] = thirdOperatorNumber;
        expression.getOperator()[operatorIndex + 2] = operator;
        return expression;
    }

    /**
     * 生成带左括号的三个操作符的表达式
     *
     * @param range         随机数范围
     * @param numIndex      操作数在表达式字符串数组中的位置
     * @param operatorIndex 运算符在表达式字符串数组中的位置
     * @return 表达式
     */
    public Expression getThreeOperatorExpressionWithLeftBrackets(int range, int numIndex, int operatorIndex) {
        Expression expression = getTwoOperatorExpressionWithMidBrackets(range, numIndex, operatorIndex);
        String operator = randElement.getOperatorChar();
        expression.getOperator()[operatorIndex + 2] += operator;
        String fourthNum = randElement.getCorrectOperatorNum(operator, randElement.getOperatorNum(range), range);
        expression.getOperatorNum()[numIndex + 3] = fourthNum;
        return expression;
    }

    /**
     * 生成带右括号的三个操作符表达式
     *
     * @param range         随机数范围
     * @param numIndex      操作数在表达式字符串数组中的位置
     * @param operatorIndex 运算符在表达式字符串数组中的位置
     * @return 表达式
     */
    public Expression getThreeOperatorExpressionWithRightBrackets(int range, int numIndex, int operatorIndex) {
        Expression expression = getTwoOperatorExpressionWithMidBrackets(range, numIndex + 1, operatorIndex + 1);
        String operator = randElement.getOperatorChar();
        expression.getOperator()[operatorIndex] = (operator += expression.getOperator()[operatorIndex]);
        String thirdOperatorNumber = randElement.getCorrectOperatorNum(operator, randElement.getOperatorNum(range), range);
        expression.getOperatorNum()[numIndex] = thirdOperatorNumber;
        return expression;
    }
}