package com.cc.addressbook.gofpatterns;

/**
 * @author cclaudiu
 * 
 *         Allows for Objects to behave differently depending on their internal State, when for eg. we want our class to
 *         behave differently and perform different computations depending on the arguments passed via Constructor
 *
 * Known as a "Behavioral" Design pattern along with the "Strategy" to quote from GoF:
 * "allows an Object to alter its behavior when its internal state changes"
 * State Design Pattern UML is very similar to the Strategy Design Pattern in that:
 * -- we have a "Context" ----> Interface "State" |
 *                                                +----> Concrete State 1
 *                                                +----> Concrete State 2
 *
 * "State" is similar to "Strategy", except that changes happen at Runtime, rather than letting the client decide which
 * strategy to call; States save us from lots of conditional statements;
 *
 * We should use this pattern when the behavior of an Object is influenced by its state
 * In short: the Context HAS-A composition to the "State" Interface, which defines a method which acccepts as argument
 * a "Context"; Now each concrete implementor of the "State" is calling on the "Context" the "State" Implementer
 */

interface PatternState {
    void doProcess(PatternContext context);
}

final class PatternContext {
    private PatternState state;

    public PatternContext(PatternState state) {
        this.state = state;
    }

    public void callState() {
        state.doProcess(this);
    }

    public void setState(PatternState newState) {
        this.state = newState;
    }
}

final class PatternStateImpl implements PatternState {

    @Override public void doProcess(PatternContext context) {
        context.setState(new PatternStateImpl2());
    }
}
final class PatternStateImpl2 implements PatternState {

    @Override public void doProcess(PatternContext context) {
        context.setState(new PatternStateImpl());
    }
}

public class StateDesignPatternClientDemo {

}
