package se.g.ida.aoc.common.spatial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractMatrixItem<E> implements MatrixItem<E>{

    protected E content;


}