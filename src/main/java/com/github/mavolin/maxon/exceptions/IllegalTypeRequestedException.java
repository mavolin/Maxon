package com.github.mavolin.maxon.exceptions;

/**
 * A {@code IllegalTypeRequestedException} is thrown whenever a getter method is called for an {@link Object Object},
 * that is not of the return type of the called getter and it would not be reasonable to convert to the desired {@link
 * Class Class}. For example if {@code getAsString()} would be called, but the {@link Object Object} this is being
 * called on is of the type {@code Boolean Boolean} a {@code IllegalTypeRequestedException} would be thrown.
 */
public class IllegalTypeRequestedException extends RuntimeException {


    /**
     * Constructs a new {@code IllegalTypeRequestedException} with {@code null} as its detail message.  The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public IllegalTypeRequestedException() {

    }

    /**
     * Constructs a new {@code IllegalTypeRequestedException} with the specified detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message
     *         the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public IllegalTypeRequestedException(String message) {

        super(message);
    }

    /**
     * Constructs a new {@code IllegalTypeRequestedException} with the specified detail message and cause.  <p>Note that
     * the detail message associated with {@code cause} is <i>not</i> automatically incorporated in this runtime
     * exception's detail message.
     *
     * @param message
     *         the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method).  (A {@code null} value
     *         is permitted, and indicates that the cause is nonexistent or unknown.)
     *
     * @since 1.4
     */
    public IllegalTypeRequestedException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Constructs a new {@code IllegalTypeRequestedException} with the specified cause and a detail message of {@code
     * (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code
     * cause}).  This constructor is useful for runtime exceptions that are little more than wrappers for other
     * throwables.
     *
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method).  (A {@code null} value
     *         is permitted, and indicates that the cause is nonexistent or unknown.)
     *
     * @since 1.4
     */
    public IllegalTypeRequestedException(Throwable cause) {

        super(cause);
    }

    /**
     * Constructs a new {@code IllegalTypeRequestedException} with the specified detail message, cause, suppression
     * enabled or disabled, and writable stack trace enabled or disabled.
     *
     * @param message
     *         the detail message.
     * @param cause
     *         the cause.  (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression
     *         whether or not suppression is enabled or disabled
     * @param writableStackTrace
     *         whether or not the stack trace should be writable
     *
     * @since 1.7
     */
    public IllegalTypeRequestedException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }


}
