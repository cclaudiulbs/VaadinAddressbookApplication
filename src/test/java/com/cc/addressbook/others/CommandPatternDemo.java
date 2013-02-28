package com.cc.addressbook.others;

/** Command Pattern entry point -- must declare a single execute() method */
interface Command {
	void execute();
}

/** Command Concrete Implementation -- creates a "binding" between the Receiver and the action */
class StartCommand implements Command {
	ReceiverOfCommand receiver;
	public StartCommand(ReceiverOfCommand receiver) {
		this.receiver = receiver;
	}
	
	@Override public void execute() {
		receiver.start();
	}
}

class StopCommand implements Command {
	ReceiverOfCommand receiver;
	public StopCommand(ReceiverOfCommand receiver) {
		this.receiver = receiver;
	}
	
	@Override public void execute() {
		receiver.stop();
	}
}

/** 
 * Receiver can be any Object that simply receives the command -- declares the functionalities the Receiver can accomplish 
 * Receiver knows the steps to perform the action
 * */
interface ReceiverOfCommand {
	void start();
	void stop();
}

/** Can be any Receiver Concrete Implementation that actually implements the actions received via the command */
class FirstReceiver implements ReceiverOfCommand {
	@Override public void start() {
		System.out.println("FirstReceiver received the start command");
	}
	@Override public void stop() {
		System.out.println("FirstReceiver received the stop command");
	}
}

/** Invoker does not know how to perform the action -- It honors the contract of invoking the Command */
class InvokerOfCommand {
	Command command;
	
	public InvokerOfCommand(Command command) {
		this.command = command;
	}
	
	public void launchCommand() {
		command.execute();
	}
}

/** This represents the Client of the COmmand Pattern -- creates an instance of the Command implementation */
public class CommandPatternDemo {
	public static void main(String[] args) {
			ReceiverOfCommand firstReceiver = new FirstReceiver();
			
			Command startCommand = new StartCommand(firstReceiver);
			Command stopCommand = new StopCommand(firstReceiver);
			
			InvokerOfCommand startInvoker = new InvokerOfCommand(startCommand);
			InvokerOfCommand stopInvoker = new InvokerOfCommand(stopCommand);
			
			startInvoker.launchCommand();
			stopInvoker.launchCommand();
	}
}

// Command Pattern helps to decouple the Invoker(which can be any Button Object which triggers the Command) from the Receiver
// Receiver is the actual Object which knows how to perform the command, and react to this action
// Command Pattern helps to honor the OCP(open closed principle) adding a new Command without changing the existing code
// Command Object defines the binding between the Receiver and the action