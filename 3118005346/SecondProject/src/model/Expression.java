package model;

import java.util.Arrays;

public class Expression {
    // 记录操作数
    private String[] operatorNum;
    // 记录操作符
    private String[] operator;
    private String answer;
    // 用于查重
    private String[] flagExprssion;
    // 用于标识该表达式是否重复
    private int flag = 0;

    public String[] getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String[] operatorNum) {
        this.operatorNum = operatorNum;
    }

    public String[] getOperator() {
        return operator;
    }

    public void setOperator(String[] operator) {
        this.operator = operator;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public Expression() {
        // 为方便计算将所有数组初始化为空
        operatorNum = new String[5];
        Arrays.fill(operatorNum, null);
        operator = new String[6];
        Arrays.fill(operator, null);
        flagExprssion = new String[7];
        Arrays.fill(flagExprssion, null);
    }

    public String getExpression() {
        // 以字符串返回表达式
        StringBuilder expression = new StringBuilder();
        int i = 0;
        int j = 0;
        if (operator[j] != null) {
            expression.append(operator[j]);
        }
        j++;
        while (operatorNum[i] != null) {
            expression.append(operatorNum[i++]);
            if (operator[j] != null) {
                expression.append(operator[j]);
                j++;
            } else {
                break;
            }
        }
        if (operatorNum[i] != null) {
            expression.append(operatorNum[i]);
        }
        if (operator[j] != null) {
            expression.append(operator[j]);
        }
        return expression.toString();
    }

    public String[] getStringArrayExpression() {
        String[] expression = new String[9];
        int i = 0;
        int j = 0;
        int k = 0;
        if (operator[k] != null) {
            expression[j++] = operator[k];
        }
        k++;
        while (operatorNum[i] != null) {
            expression[j++] = operatorNum[i++];
            if (operator[k] != null) {
                if (operator[k].length() > 1) {
                    expression[j++] = operator[k].substring(0, 1);
                    expression[j++] = operator[k].substring(1, 2);
                } else {
                    expression[j++] = operator[k];
                }
                k++;
            } else {
                break;
            }
        }
        if (operatorNum[i] != null) {
            expression[j++] = operatorNum[i];
        }
        if (operator[k] != null) {
            expression[j] = operator[k];
        }
        return expression;
    }
}
