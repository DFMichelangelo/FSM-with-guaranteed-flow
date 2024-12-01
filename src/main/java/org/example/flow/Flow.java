package org.example.flow;

import lombok.AllArgsConstructor;
import org.example.flow.abstraction.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
public class Flow<SUPER, I extends SUPER, N> {
  private final Function<I, N> inFunction;

  public record Entry<T>(Transition<T, ?> transition, Class<T> tClass) {}

  private final List<Entry<SUPER>> transitions = new ArrayList<>();

  public N run(I i) {
    return inFunction.apply(i);
  }

  public <Q extends SUPER> Optional<?> get(Q q) {
    return transitions.stream()
        .filter(e -> e.tClass.isAssignableFrom(q.getClass()))
        .findFirst()
        .map(e -> e.transition.on(q));
  }
}
