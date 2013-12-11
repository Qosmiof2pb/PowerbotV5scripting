package org.qosmiof2.fighter;

import java.util.ArrayList;
import java.util.Collections;

import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;
import org.qosmiof2.fighter.nodes.Node;
import org.qosmiof2.fighter.nodes.*;


public class QFighter extends PollingScript {
	
	private final ArrayList<Node> nodes = new ArrayList<>();

	public QFighter(){
		Collections.addAll(nodes, new FindCow(ctx), new Bank(ctx), new Eat(ctx));
	}
	@Override
	public int poll() {
		for (final Node node : nodes) {
			if (node.activate()) {
				node.execute();
				return Random.nextInt(500, 1500);
			}
		}
		return 0;
	}

}
