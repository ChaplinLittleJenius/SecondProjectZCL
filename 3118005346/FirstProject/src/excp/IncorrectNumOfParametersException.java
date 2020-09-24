package excp;

/**
 * 参数个数不正确
 *
 * @author 码代码的小天才
 */
public class IncorrectNumOfParametersException extends Exception{
    public IncorrectNumOfParametersException() {
        super();
    }

    public IncorrectNumOfParametersException(String message) {
        super(message);
    }
}
