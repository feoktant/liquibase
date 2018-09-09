package liquibase.util.csv;

import com.opencsv.CSVParser;

import java.io.Reader;

public class CSVReader extends com.opencsv.CSVReader {

    public static final char DEFAULT_SEPARATOR = CSVParser.DEFAULT_SEPARATOR;

    public static final char DEFAULT_QUOTE_CHARACTER = CSVParser.DEFAULT_QUOTE_CHARACTER;

    /**
     * The default leading whitespace behavior to use if none is supplied to the
     * constructor.
     */
    private static final boolean LIQUIBASE_IGNORE_LEADING_WHITESPACE = false;

    public CSVReader(Reader reader) {
        this(reader, DEFAULT_SEPARATOR);
    }

    public CSVReader(Reader reader, char separator) {
        this(reader, separator, DEFAULT_QUOTE_CHARACTER);
    }

    public CSVReader(Reader reader, char separator, char quotechar) {
        this(reader, separator, quotechar, CSVReader.DEFAULT_SKIP_LINES);
    }

    public CSVReader(Reader reader, char separator, char quotechar, int line) {
        super(reader,
                separator,
                quotechar,
                CSVParser.DEFAULT_ESCAPE_CHARACTER,
                line,
                CSVParser.DEFAULT_STRICT_QUOTES,
                LIQUIBASE_IGNORE_LEADING_WHITESPACE);
    }
}
