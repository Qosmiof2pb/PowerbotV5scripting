package org.qosmiof2.fighter.nodes;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Item;

public class Eat extends Node {

	public static int[] foodID = { 1999, 2000, 2001 }; // Just test ids

	public Eat(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {

		return ctx.players.local().getHealthPercent() < 30
				&& !ctx.backpack.select().first().id(foodID).isEmpty();
	}

	@Override
	public void execute() {
		for (Item food : ctx.backpack.select().id(foodID).first()) {
			food.interact("Eat");
			Timer eating = new Timer(5000);
			while (eating.isRunning()
					&& ctx.players.local().getAnimation() != -1);
			sleep(500);
		}
	}

}
