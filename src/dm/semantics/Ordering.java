/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm.semantics;

import dm.syntax.Term;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author asj
 */
public class Ordering {
	
	protected final List<Group> groups = new ArrayList<>();

	public Ordering(Set<Integer> worlds) {
		Group g = new Group();
		g.worlds = new HashSet<>(worlds);
		groups.add(g);
	}
	
	public void moveToTop(Set<Integer> worlds) {
		for (Group g : groups) {
			g.worlds.removeAll(worlds);
		}
		
		for (Iterator<Group> it = groups.iterator(); it.hasNext();) {
			Group g = it.next();
			if (g.worlds.isEmpty()) {
				it.remove();
			}
		}
		
		Group g = new Group();
		g.worlds = new HashSet<>(worlds);
		groups.add(g);
		
	}
	
	void remove(int world) {
		for (Iterator<Group> it = groups.iterator(); it.hasNext();) {
			Group g = it.next();
			if (g.worlds.contains(world)) {
				g.worlds.remove(world);
				
				if (g.worlds.isEmpty()) {
					it.remove();
				}
			}
		}
	}
	
	public Iterator<Integer> getWorse(int world) {
		Set<Integer> worlds = new HashSet<>();
		boolean found = false;
		for (int i = groups.size() - 1; i >= 0; i--) {
			Group g = groups.get(i);
			if (found) {
				worlds.addAll(g.worlds);
			}
			
			if (g.worlds.contains(world)) {
				found = true;
			}
		}
		return worlds.iterator();
	}
	
	public Iterator<Integer> getBetter(int world) {
		Set<Integer> worlds = new HashSet<>();
		for (int i = groups.size() - 1; i >= 0; i--) {
			Group g = groups.get(i);
			worlds.addAll(g.worlds);
			if (g.worlds.contains(world)) {
				break;
			}
		}
		return worlds.iterator();
	}
	
	protected class Group {
		Set<Integer> worlds = new HashSet<>();
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < groups.size(); i++) {
			result += "\t" + i + ": " + groups.get(i).worlds + "\n";
		}
		return result;
	}
	
	
}
