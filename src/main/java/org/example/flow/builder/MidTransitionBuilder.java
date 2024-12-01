package org.example.flow.builder;

import lombok.AllArgsConstructor;
import org.example.flow.transition.EndTransition;
import org.example.flow.transition.MidConditionalTransition;
import org.example.flow.transition.MidTransition;

import java.util.function.Function;

@AllArgsConstructor
public class MidTransitionBuilder<SUPER, I extends SUPER, P, N> {
  private final MidTransition<P, N> transition;
  private final Function<I, P> getPreviousState;

  public <N2> MidTransitionBuilder<SUPER, I, N, N2> andThen(MidTransition<N, N2> midTransition) {
    return new MidTransitionBuilder<>(midTransition, getPreviousState.andThen(transition::on));
  }

  public <N2> EndTransitionBuilder<SUPER, I, N, N2> andThen(EndTransition<N, N2> endTransition) {
    return new EndTransitionBuilder<>(endTransition, getPreviousState.andThen(transition::on));
  }

  public <N1, N2> MidConditionalTransitionBuilder<SUPER, I, N, N1, N2> andEither(
      MidConditionalTransition<N, N1, N2> midConditionalTransition) {
    return new MidConditionalTransitionBuilder<>(
        midConditionalTransition, getPreviousState.andThen(transition::on));
  }
}
