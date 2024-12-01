package org.example;


import org.example.either.Either;
import org.example.either.Left;
import org.example.either.Right;
import org.example.flow.Flow;
import org.example.flow.builder.FlowBuilder;
import org.example.flow.transition.EndTransition;
import org.example.flow.transition.MidConditionalTransition;
import org.example.flow.transition.StartTransition;

public class Main {
  interface MyFlow {}

  record StringContainer(String s) implements MyFlow {}

  record I(Integer s) implements MyFlow {}

  record FloatContainer(Float f) implements MyFlow {}

  public static void main(String[] args) {
    StartTransition<I, StringContainer> startTransition =
        new StartTransition<>() {
          @Override
          public StringContainer on(I intContainer) {
            return new StringContainer(Integer.toString(intContainer.s()));
          }
        };

    EndTransition<StringContainer, FloatContainer> midTransition =
        new EndTransition<>() {
          @Override
          public FloatContainer on(StringContainer stringContainer) {
            return new FloatContainer(Float.valueOf(stringContainer.s()));
          }
        };

    MidConditionalTransition<FloatContainer, I, StringContainer> midConditionalTransition =
        new MidConditionalTransition<>() {
          @Override
          public Either<I, StringContainer> on(FloatContainer intContainer) {
            if (intContainer.f() > 0) return new Left<>(new I(Math.round(intContainer.f())));
            return new Right<>(new StringContainer(intContainer.f().toString()));
          }
        };

    Flow<MyFlow, I, FloatContainer> flow =
        FlowBuilder.newBuilder(startTransition, MyFlow.class).andThen(midTransition).build();
    flow.get(new StringContainer("dfs"));
    FloatContainer result = flow.run(new I(12));
  }
}
