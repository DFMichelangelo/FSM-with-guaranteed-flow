package org.example.flow.transition;


import org.example.either.Either;
import org.example.flow.abstraction.Transition;
import org.example.flow.abstraction.WithNext;
import org.example.flow.abstraction.WithPrevious;

public abstract class MidConditionalMergerTransition<P1, P2, N>
    implements Transition<Either<P1, P2>, N>, WithNext<N>, WithPrevious<Either<P1, P2>> {

  @Override
  public N on(Either<P1, P2> p1P2Either) {
    return p1P2Either.fold(this::onLeft, this::onRight);
  }

  public abstract N onLeft(P1 p1);

  public abstract N onRight(P2 p2);
}
