package se.g.ida.aoc.common.spatial;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractMatrixItem<E> implements MatrixItem<E>{

    protected E content;
}