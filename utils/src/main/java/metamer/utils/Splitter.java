/*
 * MIT License
 *
 * Copyright (c) 2019-present Denis Verkhoturov, Aleksandra Klimina,
 * Sophia Shalgueva, Irina Shapovalova, Anna Brusnitsyna
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package metamer.utils;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A namespace for "lazy parsing" realisation.
 *
 * @param <T> Type of Record: {@link metamer.fasta.Record} or {@link metamer.fastq.Record}.
 */
public interface Splitter<T> extends Function<Iterator<T>, Iterator<List<T>>> {
    /**
     * Function to get realization of {@link SplitBefore}.
     *
     * @param delimiter Type of delimiter between records (> or @).
     * @param <T>       Type of Record: {@link metamer.fasta.Record} or {@link metamer.fastq.Record}.
     * @return anonymous realization  of {@link SplitBefore}.
     */
    static <T> Splitter<T> splitBefore(final Predicate<T> delimiter) {
        return iterator -> new SplitBefore<>(iterator, delimiter);
    }
}
