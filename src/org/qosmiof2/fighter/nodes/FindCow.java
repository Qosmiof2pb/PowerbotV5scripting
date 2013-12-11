package org.qosmiof2.fighter.nodes;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Npcs;
import org.powerbot.script.methods.Players;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Player;

public class FindCow extends Node {
	
	private int[] cowID = {1, 2, 3, 4, 5};
	private Player player;
	
	public FindCow(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		player = ctx.players.local();
		return player.getAnimation() == -1
				&& player.getHealthPercent() >= 30
				&& !ctx.npcs.select().first().id(cowID).isEmpty();
	}

	@Override
	public void execute() {
		
		for(Npc cow : ctx.npcs.select().first().id(cowID)){
			if(player.getLocation().distanceTo(cow) <= 10){
				if(cow.isOnScreen() && !cow.isInCombat()){
					cow.interact("Attack");
					for(int i = 0; i < 100 && !player.isInCombat(); i++){
						sleep(500);
					}
				} else {
					ctx.camera.turnTo(cow.getLocation());
					return;
				}
				
				/* end of cow on Screen && cow in combat*/
				
				
			} else {
				ctx.movement.stepTowards(cow.getLocation());
				
				for(int j = 0; j < 100 && player.isInMotion(); j++){
					sleep(500);
				} 
				
				/* end of if(player distance to cow)*/ 
				
			}
			
			
		} 
		
		/*End of execute*/
		
		
	}

}
