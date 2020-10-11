import excp.InvalidTextException;
import excp.NullTextException;

import java.io.*;
import java.text.DecimalFormat;

/**
 * @author 码代码的小天才
 */
public class CompareText {
    /**
     * 比较文本相似度
     *
     * @param originalText 原文本
     * @param copyText     抄袭本
     * @return 相似值
     */
    public static double compareText(String originalText, String copyText) {
        //用较大的字符串长度作为分母，相似子串作为分子计算出字串相似度
        int maxLength = Math.max(originalText.length(), copyText.length());
        int maxCommonSubstr = getLongestCommonSubstring(originalText, copyText);
        return maxCommonSubstr * 1.0 / maxLength;
    }

    /**
     * 去除文本中的标点符号及空格
     *
     * @param str 文本内容
     * @return 不含标点符号的文本
     */
    public static String getPlainText(String str) {
        return str.replaceAll("[ ，。、？！；“‘’”《》【】{}（）<>…·~-—,./;@'+=*!#$%^&()_?:\"|a-zA-Z0-9`-]", "");
    }

    /**
     * 获取最长公共子串的长度
     *
     * @param originalText 字符串1
     * @param copyText     字符串2
     * @return 返回最长公共子串的长度
     */
    public static int getLongestCommonSubstring(String originalText, String copyText) {
        int length1 = originalText.length();
        int length2 = copyText.length();
        // 滚动数组来代替普通的dp写法
        int[][] result = new int[2][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (originalText.charAt(i - 1) == copyText.charAt(j - 1)) {
                    result[i % 2][j] = result[(i - 1) % 2][j - 1] + 1;
                } else {
                    result[i % 2][j] = Math.max(result[(i - 1) % 2][j], result[i % 2][j - 1]);
                }
            }
        }
        return result[length1 % 2][length2];
    }

    /**
     * 加载指定目录下的文件
     *
     * @param file 文件名
     * @return 文本值
     * @throws NullTextException 文本内容空
     * @throws IOException       IO流发生异常
     */
    public static String loadFile(String file) throws NullTextException, IOException, InvalidTextException {
        System.out.println("开始读取指定文件：" + file);
        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file));
        StringBuffer stringBuffer = new StringBuffer();
        String readLine;
        while ((readLine = bufferedReader1.readLine()) != null) {
            stringBuffer.append(readLine);
        }
        bufferedReader1.close();
        String str = stringBuffer.toString();
        if ("".equals(str)) {
            throw new NullTextException("文件" + file + "为空文本！");
        }
        str = CompareText.getPlainText(str);
        if ("".equals(str)) {
            throw new InvalidTextException("文件" + file + "为无效文本！");
        }
        System.out.println("文件" + file + "读取完毕！");
        return str;
    }

    /**
     * 将main方法中执行操作整合成方法，便于代码复用
     *
     * @param originalText 原文路径
     * @param copyText     抄袭文路径
     * @param resultText   保存结果文件路径
     * @throws NullTextException    文本内容为空
     * @throws InvalidTextException 不含有效文本内容
     * @throws IOException          IO流操作异常
     */
    public static void doCompare(String originalText, String copyText, String resultText) throws NullTextException, InvalidTextException, IOException {
        // 定义变量
        String str1 = null;
        String str2 = null;
        File file = null;
        BufferedWriter bufferedWriter = null;

        // 判断答案文件路径是否正确
        file = new File(resultText);
        if (!file.exists()) {
            throw new IOException("找不到指定的文件！");
        }
        // 读取原文文件和抄袭文件（均为纯文本内容）
        str1 = CompareText.loadFile(originalText);
        str2 = CompareText.loadFile(copyText);

        bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        System.out.println("正在计算文章相似度....");
        // 定义输出格式
        String result = new DecimalFormat("######0.00").format(CompareText.compareText(str1, str2));
        System.out.println("文章相似度：" + result);
        System.out.println("正在保存计算结果...");
        // 结果输出在答案文件中
        bufferedWriter.write("原文路径：" + originalText + "\n");
        bufferedWriter.write("抄袭文路径：" + copyText + "\n");
        bufferedWriter.write("文章相似度：" + result + "\n\n");
        System.out.println("保存计算结果成功！");

        // 将IO流关闭
        bufferedWriter.close();
    }
}
