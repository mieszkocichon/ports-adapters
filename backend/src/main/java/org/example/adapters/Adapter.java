package org.example.adapters;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Adapter<I, O> {
    O map(I in);

    default List<O> list(Collection<I> ins) {
        return ins.stream().map(this::map).toList();
    }

    default Set<O> set(Collection<I> ins) {
        return ins.stream().map(this::map).collect(Collectors.toSet());
    }
}
