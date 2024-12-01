package org.example.flow.transition;


import org.example.flow.abstraction.Transition;
import org.example.flow.abstraction.WithNext;
import org.example.flow.abstraction.WithPrevious;

public abstract class MidTransition<P, N>
    implements WithPrevious<P>, WithNext<N>, Transition<P, N> {}
