import calculator.CalculateExpression;
import model.Expression;
import random.RandExpression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        doMain(10, 5);
        //IoFile.writeExpression(expressionArray);//将表达式写入文件
        // IoFile.writeAnswer(answerArray); //将表达式的答案写入文件.
  /*      System.out.println("已生成题目，请开始答题：\n");
        for (int i = 0; i < num; i++) {
            System.out.println("第" + (i + 1) + "题答案：");
            usersAnswer[i] = scanner.next();
        }*/
        //String contrast= UsersAnswer.getUsersAnswer(usersAnswer,answerArray);
        // System.out.println(contrast);
    }

    /**
     * main函数整合成一个静态方法，方便代码复用
     *
     * @param expressionNum    表达式个数
     * @param operatorNumRange 操作数范围
     */
    public static void doMain(int expressionNum, int operatorNumRange) {
        RandExpression randExpression = new RandExpression();
        CalculateExpression calculateExpression = new CalculateExpression();
        // 习题数组
        String[] exercises = new String[expressionNum];
        // 答案数组
        String[] answers = new String[expressionNum];
        for (int i = 0; i < expressionNum; i++) {
            Expression expression = randExpression.getExpression(operatorNumRange);
            calculateExpression.getExpValue(expression);
            if (expression.getAnswer() == null) {
                i--;
            } else {
                String exp = expression.getExpression();
                exercises[i] = exp;
                answers[i] = expression.getAnswer();
            }
        }
        for (int i = 0; i < expressionNum; i++) {
            System.out.print("第" + (i + 1) + "题：");
            System.out.println(exercises[i] + " = " + answers[i]);
        }
    }
}
