package org.example.flow.builder;

import org.example.flow.Flow;
import org.example.flow.abstraction.Transition;
import org.example.flow.transition.EndTransition;

import java.util.function.Function;

public class EndTransitionBuilder<SUPER, I extends SUPER, P, N> {
  private final Transition<P, N> transition;
  private final Function<I, P> getPreviousState;

  public EndTransitionBuilder(EndTransition<P, N> transition, Function<I, P> getPreviousState) {
    this.transition = transition;
    this.getPreviousState = getPreviousState;
  }

  public Flow<SUPER, I, N> build() {
    return new Flow<>(getPreviousState.andThen(transition::on));
  }
}
