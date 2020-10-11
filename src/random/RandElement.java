package random;

public class RandElement {

    // 随机数
    public int randomNum;
    // 操作符
    public char operatorChar;
    // 加减乘除
    public String[] operator = {"+", "-", "*", "÷"};

    /**
     * 随机生成操作数
     *
     * @param range 随机数范围
     * @return 操作数
     */
    public String getOperatorNum(int range) {
        if (range <= 0) {
            // range范围有误
            return null;
        } else if (range == 1) {
            return getIntegerOperatorNum(range);
        }
        int isInteger = (int) (Math.random() * 2);
        // 生成整数
        if (isInteger == 1) {
            return getIntegerOperatorNum(range);
        } else {
            int isProperFraction = (int) (Math.random() * 2);
            // 生成真分数
            if (isProperFraction == 1) {
                return getProperFraction(range);
            } else {
                // 生成假分数
                return getImproperFraction(range);
            }
        }
    }

    /**
     * 随机生成整数类型的操作数
     *
     * @param range 随机数范围
     * @return 操作数
     */
    public String getIntegerOperatorNum(int range) {
        Integer num = (Integer) getRandomNum(range);
        return num.toString();
    }

    /**
     * 生成随机数
     *
     * @param range 随机数范围
     * @return 随机数
     */
    public int getRandomNum(int range) {
        return (int) (Math.random() * range) + 1;
    }

    /**
     * 随机生成运算符
     *
     * @return 运算符
     */
    public String getOperatorChar() {
        int randomNum = (int) (Math.random() * 4);
        return operator[randomNum];
    }

    /**
     * 随机生成真分数
     *
     * @param range 随机数范围
     * @return 真分数
     */
    public String getProperFraction(int range) {
        String properFraction = "";
        // 生成分母
        Integer denominator = getRandomNum(range);
        while (true) {
            if (denominator <= 1) {
                denominator = getRandomNum(range);
            } else {
                break;
            }
        }
        // 生成分子
        Integer numerator = getRandomNum(denominator);
        while (true) {
            if (numerator == 0) {
                numerator = getRandomNum(denominator);
            } else {
                break;
            }
        }
        // 拼接
        properFraction += numerator.toString();
        properFraction += "/";
        properFraction += denominator.toString();
        return properFraction;
    }

    /**
     * 随机生成假分数
     *
     * @param range 随机数范围
     * @return 假分数
     */
    public String getImproperFraction(int range) {
        // 假分数
        String improperFraction = "";
        int temp = getRandomNum(range);
        String part; //假分数整数部分
        while (true) {
            if (temp == 0) {
                temp = getRandomNum(range);
            } else {
                break;
            }
        }
        // 生成真分数部分
        part = getProperFraction(range);
        improperFraction += ((Integer) temp).toString();
        improperFraction += "'";
        improperFraction += part;
        return improperFraction;
    }

    /**
     * 获取合法的操作数
     *
     * @param operator            运算符
     * @param originalOperatorNum 原操作数
     * @param range               随机数范围
     * @return 合法的操作数
     */
    public String getCorrectOperatorNum(String operator, String originalOperatorNum, int range) {
        String correctOperatorNum = null;
        if ("".equals(operator) || ("0".equals(originalOperatorNum))) {
            correctOperatorNum = getIntegerOperatorNum(range);
        } else {
            return originalOperatorNum;
        }
        return correctOperatorNum;
    }
}