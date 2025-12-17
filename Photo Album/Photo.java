public class Photo{ //create the Photo/Node class 
	private String name; //has name 
	private Photo next; //has a next node
	private Photo prev; //has a previous node 
	public static int photosViewed; //how many photos have we viewed in the album
	private boolean isViewed; //has a specific photo been viewed?

	private String photoDigest; //special code for image 

	public Photo(String name, String photoDigest){ //constructor creates a new photo in the album 
		this.name = name; //name 
		this.prev = null; //previous and next images are set to null until assigned otherwise 
		this.next = null;
		this.photoDigest = photoDigest; //photo code 
		this.photosViewed =0; //no photos have been viewed 
		this.isViewed = false; //photo has not been viewed 
	}

	public boolean equals(Photo other){ //determine if two photos are equal to one another 

		if(other == null){ //cant compare to a none photo 
			return false;
		}
		if(this.photoDigest.equals(other.photoDigest)){ //if equal 
			return true;
		}
		else{
			return false;
		}

	}
	public void viewPhoto(){ //view a specific photo 

		System.out.println("Now viewing " + name); //print is viewing 
		this.isViewed = true; //image has been viewed equals true
		photosViewed ++; //photos viewed increases by one 

	}

	public boolean isViewed(){ //determine if specific photo has been viewed 
		if(this.isViewed == true){ // if true it has been viewed 
			return true;
		}
		else{
			return false;
		}

	}

	public int getPhotosViewed(){ //how many photos viewed 
		return photosViewed; //return amount 

	}

	public Photo getPrev(){ //determine what previous photo to this one is 
		return prev; 
	}

	public Photo getNext(){ //determine what next photo to this one is 

		return next;
	}

	public void setNext(Photo photo){ //set a new next photo to this one
		next = photo;


	}

	public void setPrev(Photo photo){ //set a new previous photo to this one 
		prev = photo;

	}

	public String getName(){ //get the name of the photo 
		return this.name;
	}



}