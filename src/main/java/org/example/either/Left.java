package org.example.either;

public final class Left<L, R> extends Either<L, R> {
  public Left(L left) {
    super(left, null);
  }
}
