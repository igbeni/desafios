package br.com.igbeni.strings.model;

import br.com.igbeni.strings.exception.NoLinesException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class for a text model.
 */
public class Text {
    private List<String> lines;

    /**
     * Empty constructor of {@link Text}.
     */
    private Text() {
    }

    /**
     * Function to get lines of text.
     *
     * @return Lines of text.
     */
    List<String> getLines() {
        return lines;
    }

    /**
     * Function to get lines of text as {@link String}.
     *
     * @return Lines of text as {@link String}.
     */
    @NotNull
    public String linesAsString() {
        if (lines == null) {
            throw new NoLinesException();
        }

        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    /**
     * Class to construct an instance of {@link Text}.
     */
    public static final class TextBuilder {
        private final int defaultMaximumCharactersNumber = 40;
        private List<String> lines;
        private int max;
        private boolean justify;

        /**
         * Constructor of {@link TextBuilder} the default maximum number of characters.
         */
        private TextBuilder() {
            this.max = defaultMaximumCharactersNumber;
        }

        /**
         * Function to instantiate a {@link TextBuilder}.
         *
         * @return An instance of {@link TextBuilder}.
         */
        public static TextBuilder create() {
            return new TextBuilder();
        }

        /**
         * Function to assign lines to an instance.
         *
         * @param lines List of lines to be assigned.
         * @return An instance of {@link TextBuilder}.
         */
        @NotNull
        public TextBuilder withLines(List<String> lines) {
            this.lines = lines;
            return this;
        }

        /**
         * Function to assign if text lines will be justified.
         *
         * @param justify Boolean value indicating whether there will be justification or not.
         * @return An instance of {@link TextBuilder}.
         */
        @NotNull
        public TextBuilder withJustification(boolean justify) {
            this.justify = justify;
            return this;
        }

        /**
         * Function to assign the maximum characters number by line.
         *
         * @param max Maximum characters number by line.
         * @return An instance of {@link TextBuilder}.
         */
        @NotNull
        public TextBuilder withMaximumCharactersNumber(int max) {
            this.max = max;
            return this;
        }

        /**
         * Function to construct lines of text from a {@link String}.
         *
         * @param string Text as {@link String}.
         * @return An instance of {@link TextBuilder}.
         */
        @NotNull
        public TextBuilder fromString(@NotNull String string) {
            lines = new ArrayList<String>();

            String[] paragraphs = string.split("\n");

            for (String paragraph : paragraphs) {
                StringBuilder sb = new StringBuilder();
                StringTokenizer st = new StringTokenizer(paragraph);

                while (st.hasMoreElements()) {
                    String word = st.nextElement().toString();

                    if (sb.length() + word.length() > max) {
                        if (justify) {
                            lines.add(justifyString(sb));
                        } else {
                            lines.add(sb.toString().trim());
                        }
                        sb.setLength(0);
                    }
                    sb.append(word).append(" ");
                }

                if (sb.length() > 0) {
                    if (justify) {
                        lines.add(justifyString(sb));
                    } else {
                        lines.add(sb.toString().trim());
                    }
                }

            }
            return this;
        }

        /**
         * Function to justify a line.
         *
         * @param sb An instance of {@link StringBuilder} containing a line.
         * @return Justified line.
         */
        private String justifyString(@NotNull StringBuilder sb) {
            StringBuilder resultSb = new StringBuilder();

            String line = sb.toString().trim();

            StringTokenizer st = new StringTokenizer(line);

            int numberOfTokens = st.countTokens();

            if (numberOfTokens - 1 != 0) {
                int currentLength = 0;

                while (st.hasMoreTokens()) {
                    currentLength += st.nextToken().length();
                }

                int q = (max - currentLength) / (numberOfTokens - 1);
                int r = (max - currentLength) % (numberOfTokens - 1);

                st = new StringTokenizer(line);
                while (st.hasMoreElements()) {
                    resultSb.append(st.nextElement());

                    if (st.hasMoreElements()) {
                        for (int j = 0; j < q; j++) {
                            resultSb.append(" ");
                        }

                        if (r > 0) {
                            resultSb.append(" ");
                            r--;
                        }
                    }
                }
            }

            return resultSb.toString();
        }

        /**
         * Function to create a {@link Text} from a {@link TextBuilder}.
         *
         * @return An instance of {@link Text}.
         */
        @NotNull
        public Text build() {
            Text text = new Text();
            text.lines = this.lines;
            return text;
        }
    }
}
