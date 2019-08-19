package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.exceptions.JsonParsingException;

import java.math.BigDecimal;

/**
 * The {@code JsonTokener} is used to parse {@link String Strings} of JSON data and return them as their respective Java
 * representation ({@link com.github.mavolin.maxon.jsonvalues.JsonValue JsonValue}).
 */
public class JsonTokener {


    private static final String UNEXPECTED_END_ERR_MSG = "The String ended unexpectedly";
    private static final String END_REACHED_ERR_MSG = "The end of the JSON is already reached";
    private static final String UNEXPECTED_CHAR_SET_ERR_TMPL = "Unexpected character set starting at index %d";
    private static final String UNEXPECTED_CHAR_ERR_TMPL = "Unexpected character '%s' at index %d";
    private static final String EXPECTED_CHAR_ERR_TMPL = "Expected %s but found '%s' at index %d";
    private char[] json;
    private int currentIndex;


    /**
     * Instantiates a new {@code JsonTokener}
     *
     * @param json
     *         the json
     */
    public JsonTokener(String json) {

        this.json = json.toCharArray();
        this.currentIndex = -1;
    }


    /**
     * Skips all comments and whitespaces until a non-whitespace character is reached.
     */
    public void skipCommentAndWhitespace() {

        while (this.hasNext()) {
            char next = this.next();

            if (next == '/' && this.hasNext()) { // expecting comment
                if (this.next() == '/') { // it's a comment
                    this.skipNewline(); // skipping to next line
                    this.skipCommentAndWhitespace(); // and skipping next lines whitespaces and comments
                } else { // not a comment
                    this.currentIndex -= 2;
                }

                break; // breaking since a non-whitespace character was found
            } else if (next != ' ' && next != '\t' && next != '\n' && next != '\r') { // if next is a non whitespace character
                this.back(); // go one back so current is the non-whitespace char
                break;
            }
        }
    }

    /**
     * Skis all whitespace characters
     */
    public void skipWhitespace() {

        while (this.hasNext()) {
            char next = this.next();

            if (next != ' ' && next != '\t' && next != '\n' && next != '\r') {
                this.back();
                break;
            }
        }
    }

    /**
     * Checks if the end of the JSON is reached or if there are still characters left, that haven't been parsed.
     *
     * @return {@code true} if the JSON's current char is not the last; {@code false} otherwise
     */
    public boolean hasNext() {

        return this.currentIndex < this.json.length - 1;
    }

    /**
     * Gets the next character and increments the index
     *
     * @return the next char
     */
    public char next() {

        this.currentIndex++;

        return this.json[this.currentIndex];
    }

    /**
     * Returns a String filled with length times the upcoming characters
     *
     * @param length
     *         the desired length of the String
     *
     * @return the string
     */
    public String next(int length) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (!this.hasNext()) {
                throw new JsonParsingException(UNEXPECTED_END_ERR_MSG);
            }

            stringBuilder.append(this.next());
        }

        return stringBuilder.toString();
    }

    /**
     * Checks if {@link #hasNext()} returns {@code true}. Should that be not the case, a {@link JsonParsingException
     * JsonParsingException}* is thrown. If {@link #hasNext()} returns {@code true}, the {@code char} returned by {@link
     * #next()} is returned.
     *
     * @return the char
     */
    public char checkAndNext() {

        if (!this.hasNext()) {
            throw new JsonParsingException(END_REACHED_ERR_MSG);
        }

        return this.next();
    }

    /**
     * Returns the next char without incrementing the index
     *
     * @return the next char
     */
    public char nextNoIncrement() {

        return this.json[this.currentIndex + 1];
    }

    /**
     * Same as {@link #checkAndNext()} but for {@link #nextNoIncrement()}.
     *
     * @return the char
     */
    public char checkAndNextNoIncrement() {

        if (!this.hasNext()) {
            throw new JsonParsingException(END_REACHED_ERR_MSG);
        }

        return this.nextNoIncrement();
    }

    /**
     * Extracts the {@link Boolean Boolean} starting at the current character.
     *
     * @return the extracted {@link Boolean Boolean}
     */
    public Boolean nextBoolean() {

        if (this.isNull())
            return null;

        char next = this.checkAndNext();

        if (next == 't') { // expecting true
            if (!this.next(3).equals("rue")) {
                throw new JsonParsingException(String.format(UNEXPECTED_CHAR_SET_ERR_TMPL,
                                                               (this.currentIndex - 3)));
            } else {
                return true;
            }
        } else if (next == 'f') { // expecting false
            if (!this.next(4).equals("alse")) {
                throw new JsonParsingException(String.format(UNEXPECTED_CHAR_SET_ERR_TMPL,
                                                               (this.currentIndex - 3)));
            } else {
                return false;
            }
        } else {
            throw new JsonParsingException(String.format(UNEXPECTED_CHAR_ERR_TMPL, next, this.currentIndex));
        }
    }

    public Character nextCharacter() {

        if (this.isNull())
            return null;

        char next = this.checkAndNext();

        if (next != '"') {
            throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, "'\"'", next, this.currentIndex));
        }

        next = this.checkAndNext();

        char c;

        switch (next) {
            case '\n':
            case '\r':
                throw new JsonParsingException(String.format(UNEXPECTED_CHAR_ERR_TMPL, next, this.currentIndex));
            case '\\':
                switch (this.checkAndNext()) {
                    case '"':
                        c = '\"';
                        break;
                    case '\\':
                        c = '\\';
                        break;
                    case '/':
                        c = '/';
                        break;
                    case 'b':
                        c = '\b';
                        break;
                    case 'f':
                        c = '\f';
                        break;
                    case 'n':
                        c = '\n';
                        break;
                    case 'r':
                        c = '\r';
                        break;
                    case 't':
                        c = '\t';
                        break;
                    case 'u':
                        String uniHex = this.next(4);

                        int uniHexInDec = Integer.parseInt(uniHex, 16);

                        c = Character.toChars(uniHexInDec)[0];
                        break;
                    default:
                        throw new JsonParsingException("Unknown control character \\" + this.json[this.currentIndex] + "at index " + this.currentIndex);
                }
                break;
            case '"':
                throw new JsonParsingException("Expected a one character long JSON String but found empty String " +
                                                       "instead at index " + this.currentIndex);
            default:
                c = next;
        }

        if (this.checkAndNext() != '\"') {
            throw new JsonParsingException("Expected one character long String, but the String seems to continue " +
                                                     "at index " + this.currentIndex);
        }

        return c;
    }

    public Number nextNumber() {

        if (this.isNull())
            return null;

        char next;

        StringBuilder stringBuilder = new StringBuilder();

        // check for decimal sign
        if (this.checkAndNextNoIncrement() == '-') {
            stringBuilder.append(this.next());
        }

        next = this.checkAndNext();

        // checking if at least one digit is present
        if (next == '0' || next == '1' || next == '2' || next == '3' || next == '4' || next == '5' || next == '6' || next == '7' || next == '8' || next == '9') {
            stringBuilder.append(next);
        } else {
            throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, "number", next, this.currentIndex));
        }
        
        while (true) {
            next = this.checkAndNext();

            if (next == '0' || next == '1' || next == '2' || next == '3' || next == '4' || next == '5' || next == '6' || next == '7' || next == '8' || next == '9') {
                stringBuilder.append(next);
            } else {
                break;
            }
        }
        
        // checking if decimal places exist
        if (next == '.') {
            stringBuilder.append(".");

            next = this.checkAndNext();

            if (next == '0' || next == '1' || next == '2' || next == '3' || next == '4' || next == '5' || next == '6' || next == '7' || next == '8' || next == '9') {
                stringBuilder.append(next);
            } else {
                throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, "number", next, this.currentIndex));
            }

            while (true) {
                next = this.checkAndNext();

                if (next == '0' || next == '1' || next == '2' || next == '3' || next == '4' || next == '5' || next == '6' || next == '7' || next == '8' || next == '9') {
                    stringBuilder.append(next);
                } else {
                    break;
                }
            }
        }
        
        // checking for exponent
        if (next == 'e' || next == 'E') {
            stringBuilder.append(next);
            
            next = this.checkAndNext();
            
            if (next == '-' || next == '+') { // if decimal sign is present add it
                stringBuilder.append(next);
                next = this.checkAndNext();
            }

            if (next == '0' || next == '1' || next == '2' || next == '3' || next == '4' || next == '5' || next == '6' || next == '7' || next == '8' || next == '9') {
                stringBuilder.append(next);
            } else {
                throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, "number", next, this.currentIndex));
            }

            while (true) {
                next = this.checkAndNext();

                if (next == '0' || next == '1' || next == '2' || next == '3' || next == '4' || next == '5' || next == '6' || next == '7' || next == '8' || next == '9') {
                    stringBuilder.append(next);
                } else {
                    break;
                }
            }

        }

        this.back();

        return new BigDecimal(stringBuilder.toString());
    }

    /**
     * Extracts the {@link String String} starting at the current character.
     *
     * @return the extracted {@link String String}
     */
    public String nextString() {

        if (this.isNull())
            return null;

        char next = this.checkAndNext();
        StringBuilder stringBuilder = new StringBuilder();

        if (next != '"') {
            throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, "'\"'", next, this.currentIndex));
        }

        while (this.hasNext()) {
            next = this.next();

            switch (next) {
                case '\n':
                case '\r':
                    throw new JsonParsingException(String.format(UNEXPECTED_CHAR_ERR_TMPL, next, this.currentIndex));
                case '\\':
                    switch (this.checkAndNext()) {
                        case '"':
                            stringBuilder.append("\"");
                            break;
                        case '\\':
                            stringBuilder.append("\\");
                            break;
                        case '/':
                            stringBuilder.append("/");
                            break;
                        case 'b':
                            stringBuilder.append("\b");
                            break;
                        case 'f':
                            stringBuilder.append("\f");
                            break;
                        case 'n':
                            stringBuilder.append("\n");
                            break;
                        case 'r':
                            stringBuilder.append("\r");
                            break;
                        case 't':
                            stringBuilder.append("\t");
                            break;
                        case 'u':
                            String uniHex = this.next(4);

                            int uniHexInDec = Integer.parseInt(uniHex, 16);

                            char c = Character.toChars(uniHexInDec)[0];

                            stringBuilder.append(c);
                            break;
                        default:
                            throw new JsonParsingException("Unknown control character \\" + this.json[this.currentIndex] + "at index " + this.currentIndex);
                    }
                    break;
                case '"':
                    return stringBuilder.toString();
                default:
                    stringBuilder.append(next);
            }
        }

        throw new JsonParsingException(UNEXPECTED_END_ERR_MSG);
    }

    /**
     * Decrements the index by one until the beginning of the JSON is reached.
     */
    public void back() {

        if (this.currentIndex == -1) {
            throw new IndexOutOfBoundsException("The index cannot be further decreased");
        }

        this.currentIndex--;
    }

    /**
     * Gets the index of the current character. Returns {@code -1} if no character has been retrieved yet.
     *
     * @return the current index
     */
    public int getIndex() {

        return this.currentIndex;
    }

    /**
     * Skips until a new line is reached
     */
    private void skipNewline() {

        while (this.hasNext()) {

            char c = this.next();

            if (c == '\n') {
                break;
            }
        }
    }

    private boolean isNull() {

        if (this.checkAndNext() == 'n') { // expecting null
            String str = "n";

            str += this.next(3); // getting the 3 remaining characters that would form null

            if (!str.equals("null")) {
                throw new JsonParsingException(String.format(UNEXPECTED_CHAR_SET_ERR_TMPL,
                                                               (this.currentIndex - 3)));
            } else {
                return true;
            }
        }

        this.back();

        return false;
    }


}
