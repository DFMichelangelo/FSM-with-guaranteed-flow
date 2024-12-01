package org.example.either;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.function.Function;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public abstract sealed class Either<L, R> permits Left, Right {
  private final L left;
  private final R right;

  public <T> Either<L, T> map(Function<R, T> mapper) {
    Objects.requireNonNull(mapper);
    return switch (this) {
      case Left<L, R> either -> new Left<>(either.getLeft());
      case Right<L, R> either -> new Right<>(mapper.apply(either.getRight()));
    };
  }

  public <T> Either<T, R> mapLeft(Function<L, T> mapper) {
    Objects.requireNonNull(mapper);
    return switch (this) {
      case Left<L, R> either -> new Left<>(mapper.apply(either.getLeft()));
      case Right<L, R> either -> new Right<>(either.getRight());
    };
  }

  public <T> T fold(Function<L, T> leftMapper, Function<R, T> rightMapper) {
    Objects.requireNonNull(leftMapper);
    Objects.requireNonNull(rightMapper);
    if (right != null) return map(rightMapper).right;
    return mapLeft(leftMapper).left;
  }

  public static <L, R> Either<L, R> right(R right) {
    return new Right<>(right);
  }

  public static <L, R> Either<L, R> left(L left) {
    return new Left<>(left);
  }
}
