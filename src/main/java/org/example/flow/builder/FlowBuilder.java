package org.example.flow.builder;


import org.example.flow.transition.StartTransition;

public class FlowBuilder {

  public static <SUPER, I extends SUPER, N> StartTransitionBuilder<SUPER, I, N> newBuilder(
          StartTransition<I, N> startTransition, Class<SUPER> SUPER) {
    return new StartTransitionBuilder<>(startTransition);
  }
}
