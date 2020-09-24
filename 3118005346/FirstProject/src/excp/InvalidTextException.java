package excp;

/**
 * 不含有效文本内容
 *
 * @author 码代码的小天才
 */
public class InvalidTextException extends Exception{
    public InvalidTextException() {
        super();
    }

    public InvalidTextException(String message) {
        super(message);
    }
}
