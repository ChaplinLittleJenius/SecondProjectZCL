package calculator;

import model.Expression;

import java.util.Stack;

public class CalculateUtil {

    /**
     * 获取字符串数组的长度
     *
     * @param stringArray 字符串数组
     * @return 长度
     */
    public int getLength(String[] stringArray) {
        int length = 0;
        for (String s : stringArray) {
            if (s != null) {
                length++;
            }
        }
        return length;
    }

    /**
     * 将表达式转成逆波兰表达式的字符串数组形式
     *
     * @param expression 表达式
     * @return 逆波兰表达式的字符串数组
     */
    public String[] getRPN(Expression expression) {
        Stack<String> stack = new Stack<>();
        // 逆波兰表达式
        String[] RPN = new String[9];
        // 将expression转变为字符串数组
        String[] tempExpression = expression.getStringArrayExpression();
        // 获得字符串数组的长度
        int index = 0;
        int length = getLength(tempExpression);
        for (int i = 0; i < length; i++) {
            String temp;
            String string = tempExpression[i];
            switch (string) {
                case "(":
                    stack.push(string);
                    break;
                case "+":
                case "-":
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if ("(".equals(temp)) {
                            stack.push("(");
                            break;
                        }
                        RPN[index++] = temp;
                    }
                    stack.push(string);
                    break;
                case "*":
                case "÷":
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if ("(".equals(temp) || "+".equals(temp) || "-".equals(temp)) {
                            stack.push(temp);
                            break;
                        } else {
                            RPN[index++] = temp;
                        }
                    }
                    stack.push(string);
                    break;
                case ")":
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if ("(".equals(temp)) {
                            break;
                        } else {
                            RPN[index++] = temp;
                        }
                    }
                    break;
                default:
                    if (!string.contains("'") && !string.contains("/")) {
                        string += "/1";
                    }
                    RPN[index++] = string;
            }
        }

        while (stack.size() != 0) {
            String temp = stack.pop();
            RPN[index++] = temp;
        }
        return RPN;
    }

    /**
     * 获取假分数的整数部分
     *
     * @param fraction 假分数
     * @return 整数部分
     */
    public String getFractionInt(String fraction) {
        return fraction.substring(0, fraction.indexOf('\''));
    }

    /**
     * 获取分数的分母
     *
     * @param fraction 分数
     * @return 分母
     */
    public String getDenominator(String fraction) {
        return fraction.substring(fraction.indexOf('/') + 1);
    }

    /**
     * 获取分数的分子
     *
     * @param fraction 分数
     * @return 分子
     */
    public String getNumerator(String fraction) {
        return fraction.substring(fraction.indexOf('\'') + 1, fraction.indexOf('/'));
    }

    /**
     * 获取最小公倍数
     *
     * @param denominator1 分母1
     * @param denominator2 分母2
     * @return 最小公倍数
     */
    public Integer getLcm(Integer denominator1, Integer denominator2) {
        if (denominator1.equals(denominator2)) {
            return denominator1;
        }
        int temp = Math.max(denominator1, denominator2);
        int Lcm = temp;
        while ((Lcm % denominator1) != 0 || (Lcm % denominator2) != 0) {
            Lcm += temp;
        }
        return Lcm;
    }

    /**
     * 两个分数相加
     *
     * @param fraction1 分数1
     * @param fraction2 分数2
     * @return 结果
     */
    public String add(String fraction1, String fraction2) {
        Integer numerator1 = Integer.parseInt(getNumerator(fraction1));
        Integer denominator1 = Integer.parseInt(getDenominator(fraction1));
        Integer numerator2 = Integer.parseInt(getNumerator(fraction2));
        Integer denominator2 = Integer.parseInt(getDenominator(fraction2));
        return calculate(1, denominator1, numerator1, denominator2, numerator2);
    }

    /**
     * 两个分数相减
     *
     * @param fraction2 分数2
     * @param fraction1 分数1
     * @return 结果
     */
    public String sub(String fraction2, String fraction1) {
        Integer numerator1 = Integer.parseInt(getNumerator(fraction1));
        Integer denominator1 = Integer.parseInt(getDenominator(fraction1));
        Integer numerator2 = Integer.parseInt(getNumerator(fraction2));
        Integer denominator2 = Integer.parseInt(getDenominator(fraction2));
        return calculate(2, denominator1, numerator1, denominator2, numerator2);
    }

    /**
     * 两个分数相乘
     *
     * @param fraction1 分数1
     * @param fraction2 分数2
     * @return 结果
     */
    public String multiply(String fraction1, String fraction2) {
        Integer numerator1 = Integer.parseInt(getNumerator(fraction1));
        Integer denominator1 = Integer.parseInt(getDenominator(fraction1));
        Integer numerator2 = Integer.parseInt(getNumerator(fraction2));
        Integer denominator2 = Integer.parseInt(getDenominator(fraction2));
        return calculate(3, denominator1, numerator1, denominator2, numerator2);
    }

    /**
     * 两个分数相除
     *
     * @param fraction2 分数2
     * @param fraction1 分数1
     * @return 结果
     */
    public String divide(String fraction2, String fraction1) {
        Integer numerator1 = Integer.parseInt(getNumerator(fraction1));
        Integer denominator1 = Integer.parseInt(getDenominator(fraction1));
        Integer numerator2 = Integer.parseInt(getNumerator(fraction2));
        Integer denominator2 = Integer.parseInt(getDenominator(fraction2));
        return calculate(4, denominator1, numerator1, denominator2, numerator2);
    }

    // 子表达式计算

    /**
     * 根据flag值对分数进行不同的计算
     *
     * @param flag             1：加法 2：减法 3：乘法 4：除法
     * @param leftDenominator  左分母
     * @param leftNumerator    左分子
     * @param rightDenominator 右分母
     * @param rightNumerator   右分子
     * @return 结果
     */
    public String calculate(int flag, Integer leftDenominator, Integer leftNumerator, Integer rightDenominator, Integer rightNumerator) {
        // 获取左操作数分母和右操作数的最小公倍数
        Integer lcm = getLcm(leftDenominator, rightDenominator);
        // 结果分子
        Integer newNumerator = 0;
        // 根据flag进行不同的计算
        if (flag == 1) {
            // 加法
            newNumerator = leftNumerator * (lcm / leftDenominator) + rightNumerator * (lcm / rightDenominator);
            return newNumerator.toString() + "/" + lcm.toString();
        } else if (flag == 2) {
            // 减法
            newNumerator = leftNumerator * (lcm / leftDenominator) - rightNumerator * (lcm / rightDenominator);
            if (newNumerator < 0) {
                // 计算过程产生负数
                return null;
            }
            return newNumerator.toString() + "/" + lcm.toString();
        } else if (flag == 3) {
            // 乘法
            Integer multiSon = leftNumerator * rightNumerator;
            Integer multiMom = leftDenominator * rightDenominator;
            return multiSon.toString() + "/" + multiMom.toString();
        } else if (flag == 4) {
            // 除法
            if (rightNumerator == 0) {
                return null;
            }
            Integer diviSon = leftNumerator * rightDenominator;
            Integer diviMom = leftDenominator * rightNumerator;
            if (diviSon > diviMom) {
                // 假分数
                return null;
            }
            return diviSon.toString() + "/" + diviMom.toString();
        } else {
            return null;
        }
    }

    /**
     * 将假分数转换成带分数
     *
     * @param newNumerator 结果分子
     * @param lcm          最小公倍数
     * @return 指定格式的答案
     */
    public String convertAnswerFormat(Integer newNumerator, Integer lcm) {
        Integer integer = null;
        String answer = "";
        if (lcm != 0) {
            if (newNumerator % lcm == 0) {
                // 整数情况
                integer = newNumerator / lcm;
                return integer.toString();
            } else if (newNumerator / lcm != 0) {
                // 整数部分
                integer = newNumerator / lcm;
                // 新分子
                newNumerator = newNumerator - integer * lcm;
            }
        }
        // 最大公因数
        int hcf = 1;
        int min = newNumerator > lcm ? lcm : newNumerator;
        // 获取最大公因数
        for (int i = 2; i <= min; i++) {
            if (newNumerator % i == 0 && lcm % i == 0) {
                hcf = i;
            }
        }
        newNumerator = newNumerator / hcf;
        lcm = lcm / hcf;
        if (integer != null) {
            // 整数部分不为空
            answer = answer + integer.toString() + "'";
        }
        answer = answer + newNumerator.toString() + "/" + lcm.toString();
        return answer;
    }

    /**
     * 获取假分数
     *
     * @param operatorNum 操作数
     * @return 假分数
     */
    public String getImproperFraction(String operatorNum) {
        if (operatorNum.contains("'")) {
            // 假分数整数部分
            Integer intPart = Integer.parseInt(getFractionInt(operatorNum));
            // 假分数分母
            Integer mom = Integer.parseInt(getDenominator(operatorNum));
            // 假分数分子
            Integer son = Integer.parseInt(getNumerator(operatorNum));
            son = intPart * mom + son;
            return son.toString() + "/" + mom.toString();
        }
        return operatorNum;
    }
}