import excp.IncorrectNumOfParametersException;
import excp.InvalidTextException;
import excp.NullTextException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class doMain {
    public static void main(String[] args) throws IncorrectNumOfParametersException, IOException {
        // 判断参数个数是否正确
        if(args.length != 3){
            throw new IncorrectNumOfParametersException("参数个数异常！");
        }
        // 定义变量
        String str1 = null;
        String str2 = null;
        File file = null;
        BufferedWriter bufferedWriter = null;

        // 判断答案文件路径是否正确
        file = new File(args[2]);
        if(!file.exists()){
            throw new IOException("找不到指定的文件！");
        }

        try {
            // 读取原文文件和抄袭文件（均为纯文本内容）
            str1 = CompareText.loadFile(args[0]);
            str2 = CompareText.loadFile(args[1]);

            bufferedWriter = new BufferedWriter(new FileWriter(file,true));

            System.out.println("正在计算文章相似度....");
            // 定义输出格式
            String result = new DecimalFormat("######0.00").format(CompareText.compareText(str1,str2));
            System.out.println("文章相似度：" + result);
            System.out.println("正在保存计算结果...");
            // 结果输出在答案文件中
            bufferedWriter.write("原文路径：" + args[0] + "\n");
            bufferedWriter.write("抄袭文路径：" + args[1] + "\n");
            bufferedWriter.write("文章相似度：" + result + "\n\n");
            System.out.println("保存计算结果成功！");
        } catch (NullTextException | IOException | InvalidTextException e) {
            e.printStackTrace();
        } finally {
            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

