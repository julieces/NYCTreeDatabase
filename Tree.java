package Project4;


/**
 * This class describes the tree object and the methods used on it.
 * It is a place to store some of the information of the trees of NYC
 * @author Julie Cestaro
 * @version 23 April 2017 
 *
 */


//FIX THIS CLASS USING THE TREE TEST THAT SHE FREAKING GAVE TO YOU, YOU IDIOT

public class Tree implements Comparable<Tree>{
	
	private int tree_id;
	private int tree_dbh;
	public String status;
	private String health;
	private String spc_common;
	private int zipcode;
	private String boroname;
	private double x_sp;
	private double y_sp;
	
	/**
	 * method to return a tree object's tree id
	 * @return: tree id
	 */
	public int getTree_id(){
		return tree_id;
	}
	
	/**
	 * method to return a tree object's tree dbh
	 * @return: tree dbh
	 */
	public int getTree_dbh(){
		return tree_dbh;
	}
	
	/**
	 * method to return a tree object's status
	 * @return: status
	 */
	public String getStatus(){
		return status;
	}
	
	/**
	 * method to return a tree object's health
	 * @return: health
	 */
	public String getHealth(){
		return health;
	}
	
	/**
	 * method to return a tree object's common species name
	 * @return: species name
	 */
	public String getSpc_common(){
		return spc_common;
	}
	
	/**
	 * method to return a tree object's zipcode
	 * @return: zipcode
	 */
	public int getZipcode(){
		return zipcode;
	}
	
	/**
	 * method to return the name of the borough in which
	 * the tree object is located
	 * @return: borough name
	 */
	public String getBoroname(){
		return boroname;
	}
	
	/**
	 * method to return the x_sp of a tree object
	 * @return: x_sp
	 */
	public double getX_sp(){
		return x_sp;
	}
	
	/**
	 * method to return the y_sp of a tree object
	 * @return: y_sp
	 */
	public double getY_sp(){
		return y_sp;
	}
	
	/**
	 * Assigns a value to tree_id as long as x is greater than zero
	 * @param x
	 * @throws IllegalArgumentException if specifications are not met
	 */
	public void setTree_id (int x) throws IllegalArgumentException{
		//check specification
		if (x >= 0){
			this.tree_id = x;
		}else{
			throw new IllegalArgumentException("Tree ID must be greater than or equal to 0.");
		}
	}
	
	/**
	 * Assigns a value to tree_dbh as long as x is greater than zero
	 * @param x
	 * @throws IllegalArgumentException if specifications are not met
	 */
	public void setTree_dbh(int x) throws IllegalArgumentException{
		//check specification
		if (x >= 0){
			this.tree_dbh = x;
		}else{
			throw new IllegalArgumentException("Tree DBH must be greater than or equal to 0.");
		}
	}
	
	/**
	 * Assigns a value to status 
	 * as long as x is "alive", "dead", "stump", empty, or null.
	 * Case does not have any effect
	 * @param x
	 * @throws IllegalArgumentException if specifications are not met
	 */
	public void setStatus(String x) throws IllegalArgumentException{
		//check specification
		if (x.equalsIgnoreCase("alive") || x.equalsIgnoreCase("dead") || x.equalsIgnoreCase("stump") || x.equalsIgnoreCase("") || x.equalsIgnoreCase(null)){
			this.status = x;
		}else{
			throw new IllegalArgumentException("Status can only be \"alive\", \"dead\", \"stump\", empty string or null.");
		}
	}
	
	/**
	 * Assigns a value to health 
	 * as long as x is "good", "fair", "poor", empty, or null.
	 * Case does not have any effect.
	 * @param x
	 * @throws IllegalArgumentException if specifications are not met
	 */
	public void setHealth(String x) throws IllegalArgumentException{
		//check specification
		if (x.equalsIgnoreCase("good") || x.equalsIgnoreCase("fair") || x.equalsIgnoreCase("poor") || x.equalsIgnoreCase("") || x.equalsIgnoreCase(null)){
			this.health = x;
		}else{
			throw new IllegalArgumentException("Health can only be \"good\", \"fair\", \"poor\", empty string or null.");
		}
	}
	
	/**
	 * Assigns a value to spc_common as long as x is not null
	 * @param x
	 * @throws IllegalArgumentException if specifications are not met
	 */
	public void setSpc_common(String x) throws IllegalArgumentException{
		//check specification
		if (!(x.equalsIgnoreCase(null))){
			this.spc_common = x.trim();
		}else{
			throw new IllegalArgumentException("SPC Common cannot equal null.");
		}
	}
	
	/**
	 * Assigns a value to zipcode
	 * @param x
	 */
	public void setZipcode(int x){
		if(x < 100000){
			this.zipcode = x;
		}else{
			throw new IllegalArgumentException("Invalid zipcode");
		}
	}
	
	/**
	 * Assigns a value to health 
	 * as long as x is "manhattan", "bronx", "brooklyn", "queens", "staten island."
	 * Case does not have any effect.
	 * @param x
	 * @throws IllegalArgumentException if specifications are not met
	 */
	public void setBoroname(String x) throws IllegalArgumentException{
		//check specification
		if (x.equalsIgnoreCase("manhattan") || x.equalsIgnoreCase("bronx") || x.equalsIgnoreCase("brooklyn") || x.equalsIgnoreCase("queens") || x.equalsIgnoreCase("staten island")){
			this.boroname = x;
		}else{
			throw new IllegalArgumentException("Boroname must be the name of one of the boroughs of New York City");
		}
	}
	
	/**
	 * Assigns a value to x_sp
	 * @param x
	 * 
	 */
	public void setX_sp(double x){
		x_sp = x;
	}
	
	/**
	 * Assigns a value to x_sp
	 * @param x
	 * 
	 */
	public void setY_sp(double x){
		y_sp = x;
	}
	
	
	
	
	
	
	/**
	 * Constructs a tree object using the given parameters
	 * @param id
	 * @param diam
	 * @param status
	 * @param health
	 * @param spc
	 * @param zip
	 * @param boro
	 * @param x
	 * @param y
	 * @throws IllegalArgumentException
	 */
	public Tree(int id, int diam, String status, String health, String spc, int zip, String boro, double x, double y) throws IllegalArgumentException{
		setTree_id(id);
		setTree_dbh(diam);
		setStatus(status);
		setHealth(health);
		setSpc_common(spc);
		setZipcode(zip);
		setBoroname(boro);
		setX_sp(x);
		setY_sp(y);
	}
	
	
	/**
	 * Checks first to see if two tree objects have the same ID
	 * If they do, it checks to see if those two tree objects have the same species name
	 * @param o
	 * @throws IllegalArgumentException if trees have the same ID and different species
	 * 		or if the parameter is not a tree object
	 */
	@Override
	public boolean equals(Object o) throws IllegalArgumentException{
		//if/else ensures that parameter is tree object
		if (o instanceof Tree){
			Tree t = (Tree) o;
			//before anything, check that it is plausible that species are the same
			if (t.getSpc_common().length() == t.getSpc_common().length()){
				//compare tree ids
				if(this.getTree_id() == t.getTree_id()){
					//compare species names
					for (int i = 0; i < t.getSpc_common().length(); i++){
						//check that trees don't have the same id and different species
						if ((int) t.getSpc_common().toLowerCase().charAt(i) == (int) this.getSpc_common().toLowerCase().charAt(i)){
							continue;
						}else{
							throw new IllegalArgumentException("Tree cannot have the same ID and different species.");
						}
					}
					return true;
				}
			}
			return false;
			
		}else{
			throw new IllegalArgumentException("Argument must be a Tree object");	
		}
	}
	
	//pretty sure this works but double check just to be sure
	public boolean sameName(Object o) throws IllegalArgumentException{
		if(o instanceof Tree){
			Tree t = (Tree) o;
			if(this.getSpc_common().equals(t.getSpc_common())){
				return true;
			}else{
				return false;
			}
		}
		throw new IllegalArgumentException("Argument must be a tree object");
	}
	
	
	
	
	/**
	 * Checks to see if the two tree objects have the same species.
	 * Then checks to see if they have the same ID.
	 * @param o
	 * @return 1 if the parameter is smaller than the caller
	 * @return 0 if the parameter is the same as the caller
	 * @return -1 if the parameter is larger than the caller
	 * @throws IllegalArgumentException if the parameter is not a tree object
	 */
	@Override
	public int compareTo(Tree t) throws IllegalArgumentException{
		for (int i = 0; i < t.getSpc_common().length(); ++i){
			//if/else determines proper return value
			if ((int)this.getSpc_common().toLowerCase().charAt(i) < (int)t.getSpc_common().toLowerCase().charAt(i)){
				return -1;
			}else if ((int)this.getSpc_common().toLowerCase().charAt(i) > (int)t.getSpc_common().toLowerCase().charAt(i)){
				return 1;
			}else{
				continue;
			}
		}
		//if species are the same, this if/else determines proper return value
		if (t.getTree_id() < this.getTree_id()){
			return 1;
		}else if (t.getTree_id() > this.getTree_id()){
			return -1;
		}else{
			return 0;
		}
	}
	
	//make this case insensitive
	public int compareName(Tree t){
		if(this.getSpc_common().toLowerCase().compareTo(t.getSpc_common().toLowerCase()) > 0){
			return 1;
		}else if(this.getSpc_common().toLowerCase().compareTo(t.getSpc_common().toLowerCase()) < 0){
			return -1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * Provides a string representation of the tree object
	 * @return returnString
	 */
	@Override
	public String toString(){
		//each block adds a different variable to the return string
		String returnString = "";
		
		returnString += "Tree ID: ";
		returnString += Integer.toString(this.tree_id);
		returnString += "\n";
		
		returnString += "Tree DBH: ";
		returnString += Integer.toString(this.tree_dbh);
		returnString += "\n";
			
		returnString += "Status: ";
		returnString += this.getStatus();
		returnString += "\n";
		
		returnString += "Health: ";
		returnString += this.getHealth();
		returnString += "\n";
		
		returnString += "Species: ";
		returnString += spc_common;
		returnString += "\n";
		
		returnString += "Zipcode: ";
		returnString += Integer.toString(this.zipcode);
		returnString += "\n";
	
		returnString += "Borough: ";
		returnString += this.getBoroname();
		returnString += "\n";
	
		returnString += "X: ";
		returnString += Double.toString(this.x_sp);
		returnString += "\n";
		
		returnString += "Y: ";
		returnString += Double.toString(this.y_sp);
		returnString += "\n";
		
		return returnString;
		
	}
}
