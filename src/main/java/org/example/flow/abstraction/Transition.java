package org.example.flow.abstraction;

public interface Transition<P, N> {
  N on(P p);
}
