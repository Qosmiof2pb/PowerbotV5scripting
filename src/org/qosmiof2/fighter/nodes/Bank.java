package org.qosmiof2.fighter.nodes;

import org.powerbot.script.methods.MethodContext;

public class Bank extends Node{

	public Bank(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(Eat.foodID).isEmpty()
				&& !ctx.players.local().isInCombat()
				&& ctx.players.local().getHealthPercent() <= 50;
	}

	@Override
	public void execute() {
		
	}

}
