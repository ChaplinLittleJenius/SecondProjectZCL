import calculator.CalculateExpression;
import model.Expression;
import random.RandExpression;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {

    /**
     * main函数整合成一个静态方法，方便代码复用
     *
     * @param expressionNum    表达式个数
     * @param operatorNumRange 操作数范围
     */
    public static void doMain(int expressionNum, int operatorNumRange, String exercisesPath, String answersPath) throws IOException {
        RandExpression randExpression = new RandExpression();
        CalculateExpression calculateExpression = new CalculateExpression();
        // 习题数组
        String[] exercises = new String[expressionNum];
        // 答案数组
        String[] answers = new String[expressionNum];
        // 习题集合，用于判重
        Set<String> exercisesSet = new HashSet<String>();
        // 文件路径
        File exercisesFile = new File(exercisesPath);
        File answersFile = new File(answersPath);

        if (!exercisesFile.exists() || !answersFile.exists()) {
            throw new IOException("找不到指定的文件！");
        }
        BufferedWriter exercisesWriter = new BufferedWriter(new FileWriter(exercisesFile, true));
        BufferedWriter answerWriter = new BufferedWriter(new FileWriter(answersFile, true));
        // 生成指定数目的表达式
        for (int i = 0; i < expressionNum; i++) {
            Expression expression = randExpression.getExpression(operatorNumRange);
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
        for (int i = 0; i < expressionNum; i++) {
            System.out.print("第" + (i + 1) + "题：");
            System.out.println(exercises[i] + " = " + answers[i]);
            exercisesWriter.write((i + 1) + "." + exercises[i] + "\n");
            answerWriter.write((i + 1) + "." + answers[i] + "\n");
        }
        // 刷新并关闭IO流
        exercisesWriter.flush();
        answerWriter.flush();
        exercisesWriter.close();
        answerWriter.close();
    }

    /**
     * 判断答案对错
     *
     * @param userAnswerPath 用户答案路径
     * @param answersPath    正确答案路径
     * @param gradePath      成绩路径
     * @throws IOException IO异常
     */
    public static void checkAnswer(String userAnswerPath, String answersPath, String gradePath) throws IOException {
        File userAnswerFile = new File(userAnswerPath);
        File answersFile = new File(answersPath);
        File gradeFile = new File(gradePath);
        if (!userAnswerFile.exists() || !answersFile.exists() || !gradeFile.exists()) {
            throw new IOException("找不到指定的文件！");
        }
        List<String> correctList = new ArrayList<>();
        List<String> wrongList = new ArrayList<>();

        BufferedReader userAnswerReader = new BufferedReader(new FileReader(userAnswerFile));
        BufferedReader answerReader = new BufferedReader(new FileReader(answersFile));
        BufferedWriter gradeWriter = new BufferedWriter(new FileWriter(gradeFile, true));

        String userAnswer, answer;
        int count = 0;
        while ((answer = answerReader.readLine()) != null) {
            if ((userAnswer = userAnswerReader.readLine()) != null) {
                count++;
                String[] str1 = userAnswer.split("\\.");
                String[] str2 = answer.split("\\.");
                if ((str1[1].trim()).equals(str2[1].trim())) {
                    correctList.add(str1[0]);
                } else {
                    wrongList.add(str2[0]);
                }
            } else {
                count++;
                wrongList.add(count + "");
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Correct:");
        stringBuilder.append(correctList.size()).append("(");
        printList(stringBuilder, correctList);
        stringBuilder.append(")\n");
        stringBuilder.append("Wrong:").append(wrongList.size()).append("(");
        printList(stringBuilder, wrongList);
        stringBuilder.append(")\n");
        gradeWriter.write(stringBuilder.toString());
        System.out.println(stringBuilder.toString());

        // 刷新并关闭IO流
        userAnswerReader.close();
        answerReader.close();
        gradeWriter.flush();
        gradeWriter.close();
    }

    public static void printList(StringBuilder stringBuilder, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                stringBuilder.append(list.get(i));
            } else {
                stringBuilder.append(list.get(i)).append(",");
            }
        }
    }
}
