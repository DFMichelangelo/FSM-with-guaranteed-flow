package org.example.flow.transition;


import org.example.flow.abstraction.Transition;
import org.example.flow.abstraction.WithPrevious;

public abstract class EndTransition<P, E> implements WithPrevious<P>, Transition<P, E> {}
