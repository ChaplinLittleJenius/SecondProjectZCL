import calculator.CalculateExpression;
import model.Expression;
import org.junit.Test;
import random.RandExpression;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MyTest {
    @Test
    public void testGenerateExpression(){
        RandExpression randExpression = new RandExpression();
        CalculateExpression calculateExpression = new CalculateExpression();
        // 习题数组
        String[] exercises = new String[10];
        // 答案数组
        String[] answers = new String[10];
        // 习题集合，用于判重
        Set<String> exercisesSet = new HashSet<String>();
        // 生成指定数目的表达式
        for (int i = 0; i < 10; i++) {
            Expression expression = randExpression.getExpression(10);
            calculateExpression.getExpressionAnswer(expression);
            if (expression.getAnswer() == null) {
                i--;
            } else {
                String exp = expression.getExpression();
                if (exercisesSet.contains(exp)) {
                    i--;
                } else {
                    exercisesSet.add(exp);
                    exercises[i] = exp;
                    answers[i] = expression.getAnswer();
                }
            }
        }
        // 输出结果
        for (int i = 0; i < 10; i++) {
            System.out.print("第" + (i + 1) + "题：");
            System.out.println(exercises[i] + " = " + answers[i]);
        }
    }

    @Test
    public void testCheckAnswer() throws IOException {
        Util.checkAnswer("src\\txt\\Exercises.txt","src\\txt\\Answers.txt","src\\txt\\Grade.txt");
    }
}
