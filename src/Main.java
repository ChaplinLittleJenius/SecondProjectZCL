import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int expresionNum = 0;
        int operatorNumRange = 0;
        String exercisesPath = "";
        String answerPath = "";
        for(int i = 0; i < args.length; i++){
            if("-n".equals(args[i])){
                expresionNum = Integer.parseInt(args[i+1]);
            }else if("-r".equals(args[i])){
                operatorNumRange = Integer.parseInt(args[i+1]);
            }else if("-e".equals(args[i])){
                exercisesPath = args[i+1];
            }else if("-a".equals(args[i])){
                answerPath = args[i+1];
            }
        }
        Util.doMain(expresionNum, operatorNumRange, exercisesPath, answerPath);
    }



}
