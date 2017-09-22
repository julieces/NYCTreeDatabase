/**
 * This class contains the implementation of the TreeCollection class which is a more specific MyBST
 * to be used specifically with Tree objects.
 * friendly database of trees.
 * @author Julie Cestaro
 * @version 23 April 2017
 * 
 */


package Project4;
import java.util.ArrayList;
import java.util.Collection;
import java.util.*;

public class TreeCollection extends MyBST<Tree> {
	
	protected ArrayList<String> boroArray;
	protected ArrayList<Integer> numBoroArray;
	protected HashMap<String,Integer> countsByBoro;
	protected HashMap<String,Integer> countsBySpecies;
	
	/**
	 * This is the constructor which constructs an empty treeCollection
	 */
	public TreeCollection(){
		super();
		boroArray = new ArrayList<String>();
		numBoroArray = new ArrayList<Integer>();
		countsByBoro = new HashMap<String,Integer>();
		countsBySpecies = new HashMap<String,Integer>();
		
	}

	/**
	 * This method overrides the add method from the MyBST class. In addition to calling
	 * a recursive add method, it also increments number of nodes by one
	 * @param takes in a tree object to be added
	 * @return true if the tree object is not already in the BST and can be added, false if otherwise
	 */
	@Override
	public boolean add(Tree t){
		//determine if the object can actually be added
		boolean canDo = super.add(t);
		//if it can, carry on
		if(canDo){
			int counts = 1;
			//determine if the borough has been seen before
			if(countsByBoro.containsKey(t.getBoroname().toLowerCase())){
				//if it has, increment the counter by 1
				counts = countsByBoro.get(t.getBoroname().toLowerCase()) + 1;
			}
			//if it hasn't, add a new counter
			countsByBoro.put(t.getBoroname().toLowerCase(), counts);
			
			int otherCounts = 1;
			String temp = t.getSpc_common().toLowerCase();
			//determine if the species has been seen before
			if(countsBySpecies.containsKey(temp)){
				//if it has, increment the counter by 1
				otherCounts = countsBySpecies.get(temp) + 1;
			}
			//if it hasn't, add a new counter
			countsBySpecies.put(temp, otherCounts);
		}
		return canDo;
	}
	

	/**
	 * This method is used to remove an object from the binary search tree. It makes sure that
	 * the data is already in the tree and then calls a recursive method to remove the data
	 * @param e
	 * @return true if the data is already in the tree and can be removed
	 * @return false if the data is not already in the tree and cannot be removed
	 */
	@Override
	public boolean remove(Object o){
		Tree t = (Tree) o;
		//determine if the object can actually be added
		boolean canDo = super.remove(t);
		//if it can, carry on
		if(canDo){
			int counts;
			//determine if the borough has been seen before
			if(countsByBoro.containsKey(t.getBoroname().toLowerCase())){
				//if it has, decrement the counter
				counts = countsByBoro.get(t.getBoroname().toLowerCase()) - 1;
				if(counts == 0){
					//if the counter is now 0, remove the borough entierely 
					countsByBoro.remove(t.getBoroname().toLowerCase());
				}else{
					countsByBoro.put(t.getBoroname().toLowerCase(), counts);
				}
			}
			
			int otherCounts;
			//determine if the species has been seen before
			if(countsBySpecies.containsKey(t.getSpc_common().toLowerCase())){
				otherCounts = countsBySpecies.get(t.getSpc_common().toLowerCase()) - 1;
				//if it has, decrement the counter
				if(otherCounts == 0){
					//if the counter is now 0, remove the species entierely 
					countsBySpecies.remove(t.getSpc_common().toLowerCase());
				}else{
					countsBySpecies.put(t.getSpc_common().toLowerCase(), otherCounts);
				}
			}
		}
		return canDo;
	}

	/**
	 * This method returns the total number of nodes contained in the tree
	 * @return numOfNodes
	 */
	public int getTotalNumberOfTrees(){
		return super.numOfNodes; 
	}
	
	/**
	 * This method takes in a speciesname and calls a recursive method to determine
	 * the number of tree objects with that species
	 * @param speciesName
	 * @return int number of tree objects with the given species
	 */
	public int getCountByTreeSpecies(String speciesName){
		//make a collection containing all of the species that have the current species name as a substring
		Collection<String> allSpecies = getMatchingSpecies(speciesName);
		int count = 0;
		//add up all the counts using a recursive method
		for(String s : allSpecies){
			count += recCount(s, root);		
		}
		return count;
	}
	
	/**
	 * This is the private recursive method that determines the number of tree objects that have
	 * the given target, in this case species name, using a binary search like implementation
	 * @param target
	 * @param root
	 * @return number of tree objects having the target
	 */
	private int recCount(String target, BSTNode<Tree> root){
		if(root == null){
			return 0;
		}else if(target.compareToIgnoreCase(root.getData().getSpc_common()) > 0){
			//if the target is larger, search the right subtree
			return recCount(target, root.getRight());
		}else if(target.compareToIgnoreCase(root.getData().getSpc_common()) < 0){
			//if the target is smaller, search the left subtree
			return recCount(target, root.getLeft());
		}else{
			//found it! increase the counter and search both subtrees
			return (1 + recCount(target, root.getLeft()) + recCount(target, root.getRight()));
		}
	}

	
	/**
	 * This method takes in a boroname and returns the number of tree objects in that borough
	 * @param boroName
	 * @return int number of tree objects in the given borough
	 */
	public int getCountByBorough(String boroName){
		if(countsByBoro.containsKey(boroName.toLowerCase())){
			int numOfTrees = countsByBoro.get(boroName.toLowerCase());
			return numOfTrees;
		}else{
			return 0;
		}
		
	}

	/**
	 * This method functions similarly to getCountBySpecies. It calls a private recursive method to
	 * determine the number of trees that match the given species and borough
	 * @param speciesName
	 * @param boroName
	 * @return the number of trees with the matching species and borough
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName){
		//make a collection containing all of the species that have the current species name as a substring
		Collection<String> allSpecies = getMatchingSpecies(speciesName);
		int count = 0;
		//add up all the counts using a recursive method
		for(String s : allSpecies){
			count += recCountSB(s, boroName, root);
		}
		return count;
	}

	/**
	 * This is the private recursive method that determines the number of tree objects that have
	 * the given target species and target borough using a binary search like implementation
	 * @param target
	 * @param root
	 * @return number of tree objects having the target species and target borough
	 */
	private int recCountSB(String targetSpecies, String targetBoro, BSTNode<Tree> root){
		if(root == null){
			return 0;
		}else if(targetSpecies.compareToIgnoreCase(root.getData().getSpc_common()) > 0){
			//if the target is larger, search the right subtree
			return recCountSB(targetSpecies, targetBoro, root.getRight());
		}else if(targetSpecies.compareToIgnoreCase(root.getData().getSpc_common()) < 0){
			//if the target is smaller, search the left subtree
			return recCountSB(targetSpecies, targetBoro, root.getLeft());
		}else{
			//found it! only increase the counter if the borough is correct
			if(targetBoro.equals(root.getData().getBoroname())){
				return (1 + recCountSB(targetSpecies, targetBoro, root.getLeft()) + recCountSB(targetSpecies, targetBoro, root.getRight()));
			}else{
				return (recCountSB(targetSpecies, targetBoro, root.getLeft()) + recCountSB(targetSpecies, targetBoro, root.getRight()));
			}
		}
	}
	
	/**
	 * This method determines all species that contain the parameter species name as a substring
	 * @param speciesName
	 * @return a string collection containing species name as a substring
	 */
	public Collection<String> getMatchingSpecies(String speciesName){
		ArrayList<String> uniqueSpecies = new ArrayList<String>();
		//countsBySpecies contains all unique species as keys
		//if the key contains speciesname as a substring, add it to the list of species to be returned
		for(String key : countsBySpecies.keySet()){
			if(key.contains(speciesName)){
				uniqueSpecies.add(key);
			}
		}
		return uniqueSpecies;
	}
	
	
}








