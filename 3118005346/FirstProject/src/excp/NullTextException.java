package excp;

/**
 * 文本内容为空
 *
 * @author 码代码的小天才
 */
public class NullTextException extends Exception{
    public NullTextException() {
        super();
    }

    public NullTextException(String message) {
        super(message);
    }
}
