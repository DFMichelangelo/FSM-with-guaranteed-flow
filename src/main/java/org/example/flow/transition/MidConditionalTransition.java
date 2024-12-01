package org.example.flow.transition;

import org.example.either.Either;

// implements WithPrevious<P>, WithNext<Either<N1,N2>>, Transition<P,Either<N1,N2>>
public abstract class MidConditionalTransition<P, N1, N2>
    extends MidTransition<P, Either<N1, N2>> {}
