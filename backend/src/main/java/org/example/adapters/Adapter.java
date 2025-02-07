package org.example.adapters;

import java.util.Collection;
import java.util.List;

public interface Adapter<I, O> {
    O map(I in);

    default List<O> list(Collection<I> ins) {
        return ins.stream().map(this::map).toList();
    }
}
