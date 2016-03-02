package po;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Created by 宋益明 on 16-3-2.
 */
public class SimpleTokenizer extends StringTokenizer implements Serializable {

    /**
     * Constructs a string tokenizer for the specified string. The
     * tokenizer uses the default delimiter set, which is
     * <code>"&nbsp;&#92;t&#92;n&#92;r&#92;f"</code>: the space character,
     * the tab character, the newline character, the carriage-return character,
     * and the form-feed character. Delimiter characters themselves will
     * not be treated as tokens.
     *
     * @param str a string to be parsed.
     * @throws NullPointerException if str is <CODE>null</CODE>
     */
    public SimpleTokenizer(String str) {
        super(str);
    }
}
