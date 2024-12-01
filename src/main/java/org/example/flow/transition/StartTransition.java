package org.example.flow.transition;


import org.example.flow.abstraction.Transition;
import org.example.flow.abstraction.WithNext;

public abstract class StartTransition<I, N> implements WithNext<N>, Transition<I, N> {}
