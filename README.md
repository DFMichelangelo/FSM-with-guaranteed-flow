# FSM with guaranteed flow

Finite state machines are a set of transitions from a state A to a state B.
When dealing with sequential transitions that have a start and an end (i.e. workflows), there is no way to guarantee that an FSM arrives to the end (i.e. running multiple transitions might lead to a dead end).

The proposed solution is to create a Flow that guarantees start and end at compile time and convert it into an FSM.
By doing so, it's possible to "answer"/"handles" to states in the "middle" of the flow and have the guarantee at compile time that the set of transitions will end in an End State. 
