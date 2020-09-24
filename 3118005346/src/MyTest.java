import excp.InvalidTextException;
import excp.NullTextException;
import org.junit.Test;

import java.io.IOException;

/**
 * @author 码代码的小天才
 */
public class MyTest {
    @Test
    public void testInvalidTextException() throws NullTextException, InvalidTextException, IOException {
        String str = CompareText.loadFile("src/text/invalid_text.txt");
    }

    @Test
    public void testNullTextException() throws NullTextException, InvalidTextException, IOException {
        String str = CompareText.loadFile("src/text/null_text.txt");
    }

    @Test
    public void testFileNotFoundException() throws NullTextException, InvalidTextException, IOException {
        String str = CompareText.loadFile("src/text/nofile.txt");
    }

    @Test
    public void testWrongFilePath() throws NullTextException, InvalidTextException, IOException {
        String str = CompareText.loadFile("null");
    }

    @Test
    public void testAdd() throws NullTextException, InvalidTextException, IOException {
        CompareText.doCompare("src/text/orig.txt", "src/text/orig_0.8_add.txt", "src/text/result.txt");
    }

    @Test
    public void testDel() throws NullTextException, InvalidTextException, IOException {
        CompareText.doCompare("src/text/orig.txt", "src/text/orig_0.8_del.txt", "src/text/result.txt");
    }

    @Test
    public void testDis_1() throws NullTextException, InvalidTextException, IOException {
        CompareText.doCompare("src/text/orig.txt", "src/text/orig_0.8_dis_1.txt", "src/text/result.txt");
    }

    @Test
    public void testDis_10() throws NullTextException, InvalidTextException, IOException {
        CompareText.doCompare("src/text/orig.txt", "src/text/orig_0.8_dis_10.txt", "src/text/result.txt");
    }

    @Test
    public void testDis_15() throws NullTextException, InvalidTextException, IOException {
        CompareText.doCompare("src/text/orig.txt", "src/text/orig_0.8_dis_15.txt", "src/text/result.txt");
    }
}
