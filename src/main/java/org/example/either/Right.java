package org.example.either;

public final class Right<L, R> extends Either<L, R> {
  public Right(R right) {
    super(null, right);
  }
}
