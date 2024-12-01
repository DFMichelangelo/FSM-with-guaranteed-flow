package org.example.flow.builder;

import lombok.AllArgsConstructor;
import org.example.flow.transition.EndTransition;
import org.example.flow.transition.MidConditionalTransition;
import org.example.flow.transition.MidTransition;
import org.example.flow.transition.StartTransition;

@AllArgsConstructor
public class StartTransitionBuilder<SUPER, I extends SUPER, N> {
  private final StartTransition<I, N> startTransition;

  public <N2> MidTransitionBuilder<SUPER, I, N, N2> andThen(MidTransition<N, N2> midTransition) {
    return new MidTransitionBuilder<>(midTransition, startTransition::on);
  }

  public <N1, N2> MidConditionalTransitionBuilder<SUPER, I, N, N1, N2> andThen(
      MidConditionalTransition<N, N1, N2> midConditionalTransition) {
    return new MidConditionalTransitionBuilder<>(midConditionalTransition, startTransition::on);
  }

  public <N2> EndTransitionBuilder<SUPER, I, N, N2> andThen(EndTransition<N, N2> endTransition) {
    return new EndTransitionBuilder<>(endTransition, startTransition::on);
  }
}
