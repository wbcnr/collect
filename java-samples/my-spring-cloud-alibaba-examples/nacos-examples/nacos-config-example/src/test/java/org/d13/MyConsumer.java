package org.d13;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface MyConsumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default java.util.function.Consumer<T> andThen( java.util.function.Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return new Consumer<T> () {
            @Override
            public void accept( T t ) {
                MyConsumer.this.accept ( t );
                after.accept ( t );
            }
        };
    }
}
