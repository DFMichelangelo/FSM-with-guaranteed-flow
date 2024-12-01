package org.example.flow.builder;

import lombok.AllArgsConstructor;
import org.example.either.Either;
import org.example.flow.transition.*;

import java.util.function.Function;

@AllArgsConstructor
public class MidConditionalTransitionBuilder<SUPER, I extends SUPER, P, N1, N2> {
  private final MidConditionalTransition<P, N1, N2> transition;
  private final Function<I, P> getPreviousState;

  public <N3> MidConditionalTransitionBuilder<SUPER, I, Either<N1, N2>, N3, N2> onLeft(
      MidTransition<N1, N3> midTransition) {
    return new MidConditionalTransitionBuilder<>(
        new MidConditionalTransition<>() {
          @Override
          public Either<N3, N2> on(Either<N1, N2> n1N2Either) {
            return n1N2Either.mapLeft(midTransition::on);
          }
        },
        getPreviousState.andThen(transition::on));
  }

  public <N3> MidConditionalTransitionBuilder<SUPER, I, Either<N1, N2>, N1, N3> onRight(
      MidTransition<N2, N3> midTransition) {
    return new MidConditionalTransitionBuilder<>(
        new MidConditionalTransition<>() {
          @Override
          public Either<N1, N3> on(Either<N1, N2> n1N2Either) {
            return n1N2Either.map(midTransition::on);
          }
        },
        getPreviousState.andThen(transition::on));
  }

  public <N3> MidTransitionBuilder<SUPER, I, Either<N1, N2>, N3> merge(
      MidConditionalMergerTransition<N1, N2, N3> mergerTransition) {
    return new MidTransitionBuilder<>(
        new MidTransition<>() {
          @Override
          public N3 on(Either<N1, N2> n1N2Either) {
            return mergerTransition.on(n1N2Either);
          }
        },
        getPreviousState.andThen(transition::on));
  }

  public <N3> EndTransitionBuilder<SUPER, I, Either<N1, N2>, N3> merge(
      EndConditionalMergerTransition<N1, N2, N3> mergerTransition) {
    return new EndTransitionBuilder<>(
        new EndTransition<>() {
          @Override
          public N3 on(Either<N1, N2> n1N2Either) {
            return mergerTransition.on(n1N2Either);
          }
        },
        getPreviousState.andThen(transition::on));
  }
}
