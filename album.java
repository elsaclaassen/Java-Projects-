public class Album implements IAlbum{ //create the album class that implements the interface provided 

	private String albumName; //name of album 
	private Photo first; //photo that is marked as first in album for comparison purposes later on 
	private boolean open; //determine if the album is open or closed 
	private Photo at; //current variable that tracks what photo we are looking at or we are on
	
	private int size; //increase size of album each time a photo is added and decrease if photo removed 
	



	public Album(String albumName){ //constrcutor takes in the album name
		this.albumName = albumName; //assign name 
		
		this.first = null; //first starts as null
		this.at = null; //currect starts at null
		//this.first = new Photo(null,null);
		//this.at = first;
		this.size = 0; //size starts at zero 
		
		this.open = false; //open starts as false (album starts closed)

	}

	public String getAlbumName(){ //get album name method
		return this.albumName; //return the name of the album

	}

	

	 
	public boolean hasPhoto(Photo photo){ //method to determine if a photo exists within the album
		if(size == 0){ //first check if the album is empty 
			return false;
		}
		at = first; //look at the "first" photo in album and iterate through every photo until we get back to first 
		do{
			if(at.equals(photo)){ //look at each photo and return true if equals the desired photo 
				return true; //return true if found
			}
			at = at.getNext(); //if not keep iterating
		}while(at != first); //if we reach first again return false 

		return false;
	}

	public void addPhoto(Photo photo){ //add photo method adds the photo as previous to the "first"
		if(this.hasPhoto(photo)){ //if the photo already exists within the album
			System.out.println("Attempted to add duplicate photo."); //dont add it is a duplicate 
			return; //break 
		}
		if (size == 0){ //if this is first photo in album
			first = photo; //first and at are looking at the new photo added 
			at = photo;
			photo.setNext(photo); //next points to itself 
			photo.setPrev(photo); //previoud points to itself 
			size ++; //increase by one
		}
		else{
			Photo temp = at.getPrev(); //set temperary variable to photo before current 
			at.setPrev(photo); //add photo as pervious to current
			photo.setNext(at); //at should be the next for new photo to link them 
			photo.setPrev(temp); //the previous should be whatever currents previous used to be 
			temp.setNext(photo); //link the previous previous to new 
			size ++; //increase list by one 

		}
		
		

	}

	public void deletePhoto(Photo photo){ //create method to delete photo from list 

		if(size == 0){ //if the list is empty cant remove a photo 
			System.out.println("empty cant remove");
			return;
		}

		if(photo.equals(first)){ //special case if trying to delete first element 
			if(size == 1){
				first = null; //if only elements now empty 
			}
			else{ //otherwise assign accordingly 
				Photo temp = first.getPrev();
				Photo other = first.getNext();
				temp.setNext(other);
				other.setPrev(temp);
				first = other;

			}
			size --; //subtract one from list size after removing 

		}
		else{
			at = first.getNext(); //if not the first element look at the next after first 
			while(at != first){ //iterate through all photos to find until one full iteration has occured 
				if(at.equals(photo)){
					//assign photos accordingly without the one we want to delete 
					Photo temp = at.getPrev();
					Photo other = at.getNext();
					temp.setNext(other);
					other.setPrev(temp);
					size--; //subtract one from size of list 
					return;

					
				}
				at = at.getNext();
			
			}
			System.out.println("Attempted to delete a photo that is not in the album."); //if photo not in almbum 

		}
		
		return;


		
		
		
	}

	public boolean allPhotosViewed(){ //determing if all photos in the album have been viewed 
		if(size == 0){ //if empty all have been viewed 
			return true;
		}
		at = first; //starting at the first element 

		do{
			if(at.isViewed() == false){ //iterate through and determine if the photo has been viewed or not 
				return false; //return false if one photo hasnt been viewed 
			}
			at = at.getNext(); //iterate to next 
		}while(at != first); //iterate until one full cirlce and we return to first 
		return true; //return true all photos have been viewed because didnt return false 

	}

	public boolean equals(IAlbum other){ //determine if two albums contain all the same elements 
		if(this.getCount() == other.getCount()){ //determine if they have the same size 
			//int iterations = 0;
			int counter = 0; //counter to keep track of photos found in both 
			at = this.first; //start at first 
			//Photo temp = other.first;
			
			while(counter<this.getCount()){ //while not all photos have been found 
				
				if(!other.hasPhoto(at)){ //if the other album doesnt have photo return false 
					return false;
				}
				at = at.getNext(); //otherwise keep iterating to next photo 
				counter ++; //increase by one because one photo has been found

				

			}
			return true; //return true when all photos have been found and we exit while loop

		}
		else{
			return false; //if size are not same return false 
		}


	}

	public void openAlbum(){ //opens the albums 
		if(size ==0){
			System.out.println("Album " + albumName + " opened");
			return; //tell user album has been opened 
		}
		open = true; //open is true 
		System.out.println("Album " + albumName + " opened");
		first.viewPhoto(); //start viewing from first photo 
		//photosViewed ++;
		

	}

	public void closeAlbum(){ //close album 
		open = false; //set open to false 
		System.out.println("Album " + albumName + " closed");

	}

	public void viewNextPhoto(){ //scroll to the next photo in the list 
		if(size==0){ //if empty cant scroll 
			System.out.println("Tried to view next photo, but album has no photos.");
		}
		else if(open == true){ //album has to be open to scroll 
			at = at.getNext(); //change current
			at.viewPhoto(); //look at current 
		}
		else{
			System.out.println("Tried to view next photo, but album is closed."); //album is closed cant scroll 
		}

	}

	public void viewPreviousPhoto(){ //scroll backwards in the album 
		if(size == 0){ //if empty cant scroll 
			System.out.println("Tried to view previous photo, but album has no photos.");
		}
		else if(open == true){ 
			at = at.getPrev(); //current is now previous photo 
			at.viewPhoto(); //view the previous photo 
		}
		else{
			System.out.println("Tried to view previous photo, but album is closed."); //cant scroll through closed album 
		}

	}

	public int getCount(){ //return amount of photos 
		return size;
	}

	public boolean isEmpty(){ //determine if there are photos in album 
		if(size == 0){
			return true;
		}
		return false;
	}
	
	public void printContent(){ //method for testing 
		if(size == 0){
			System.out.println("empty");
		}
		at = first;
		do{
			System.out.println(at.getName());
			at = at.getNext();

		}while(at.equals(first) ==false );
		return;
	}
	

	public static void main(String[]args){ //main for test cases
		
		/*
		System.out.println("/|\\ /|\\ /|\\ /|\\ /|\\ /|\\");
		System.out.println("\nSample output 1\n");
		System.out.println("Testing Photo class");
		Photo photo1 = new Photo("Sunset", "abc123");
		Photo photo2 = new Photo("Sunrise", "def456");
		photo1.viewPhoto();
		System.out.println("Has photo1 been viewed? " + photo1.isViewed());
		System.out.println("Has photo2 been viewed? " + photo2.isViewed());
		System.out.println("Are photo1 and photo2 equal? " + photo1.equals(photo2));
		Photo photo3 = new Photo("Same sunset photo", "abc123");
		System.out.println("Are photo1 and photo3 equal? " + photo1.equals(photo3));
		System.out.println("\n\\|/ \\|/ \\|/ \\|/ \\|/ \\|/");
		
		System.out.println("/|\\ /|\\ /|\\ /|\\ /|\\ /|\\");
		System.out.println("\nSample output 2\n");
		System.out.println("Testing adding photos to albums");
		Album album = new Album("My album");
		System.out.println("Is album empty before adding photos? " + album.isEmpty());
		Photo photo1 = new Photo("Sunset", "abc123");
		Photo photo2 = new Photo("Sunrise", "def456");
		Photo photo1Copy = new Photo("Same sunset", "abc123");
		Photo unusedPhoto = new Photo("Can of beans", "ghi789");
		album.addPhoto(photo1);
		album.addPhoto(photo2);
		album.addPhoto(photo1Copy);
		System.out.println("Is album empty after adding photos? " + album.isEmpty());
		System.out.println("Photo count in " + album.getAlbumName() + ": " + album.getCount());
		System.out.println("Is photo1 in album? " + album.hasPhoto(photo1));
		System.out.println("Is photo2 in album? " + album.hasPhoto(photo2));
		// Note that line below is true because photo1 and photo1Copy have the same photoDigest
		System.out.println("Is photo1Copy in album? " + album.hasPhoto(photo1Copy));
		System.out.println("Is unusedPhoto in album? " + album.hasPhoto(unusedPhoto));
		System.out.println("\n\\|/ \\|/ \\|/ \\|/ \\|/ \\|/");
		
		
		System.out.println("/|\\ /|\\ /|\\ /|\\ /|\\ /|\\");
		System.out.println("\nSample output 3\n");
		System.out.println("Testing viewing albums");
		Album album = new Album("My Cool Album");
		Photo photo1 = new Photo("Sunset", "abc123");
		Photo photo2 = new Photo("Sunrise", "def456");
		Photo photo3 = new Photo("Can of Beans", "ghi789");
		album.addPhoto(photo1);
		album.addPhoto(photo2);
		album.addPhoto(photo3);
		System.out.println("Try to view next or previous photo in album before open:");
		album.viewNextPhoto();
		album.viewPreviousPhoto();
		album.openAlbum();
		System.out.println("Cycling forward through photos:");
		album.viewNextPhoto();
		System.out.println("Have all photos been viewed before viewing Can of Beans? " + album.allPhotosViewed());
		album.viewNextPhoto();
		System.out.println("Have all photos been viewed after viewing Can of Beans? " + album.allPhotosViewed());
		album.viewNextPhoto();
		System.out.println("Cycling backwards through photos:");
		album.viewPreviousPhoto();
		album.viewPreviousPhoto();
		album.viewPreviousPhoto();
		album.viewPreviousPhoto();
		album.closeAlbum();
		System.out.println("Try to view next photo in album after close:");
		album.viewNextPhoto();
		album.openAlbum();
		System.out.println("\n\\|/ \\|/ \\|/ \\|/ \\|/ \\|/");

		
		
		
		System.out.println("/|\\ /|\\ /|\\ /|\\ /|\\ /|\\");
		System.out.println("\nSample output 4\n");
		System.out.println("Testing deleting from albums");
		Album album = new Album("My Cool Album");
		Photo photo1 = new Photo("Sunset", "abc123");
		Photo photo2 = new Photo("Sunrise", "def456");
		Photo photo3 = new Photo("Can of Beans", "ghi789");
		Photo unusedPhoto = new Photo("My deepest darkest secret", "jkl012");
		album.addPhoto(photo1);
		album.addPhoto(photo2);
		album.addPhoto(photo3);
		System.out.println("Album photo count after adding photos: " + album.getCount());
		album.deletePhoto(unusedPhoto);
		album.deletePhoto(photo2);
		System.out.println("Album photo count after deleting photo2: " + album.getCount());
		System.out.println("Is photo2 in album? " + album.hasPhoto(photo2));
		album.deletePhoto(photo1);
		System.out.println("Album photo count after deleting photo1: " + album.getCount());
		System.out.println("Is photo1 in album? " + album.hasPhoto(photo1));
		System.out.println("Opening to album to see if photos still cycle correctly:");
		album.openAlbum();
		album.viewNextPhoto();
		album.viewPreviousPhoto();
		album.deletePhoto(photo3);
		System.out.println("Album photo count after deleting photo3: " + album.getCount());
		System.out.println("Is photo3 in album? " + album.hasPhoto(photo1));
		album.viewNextPhoto();
		System.out.println("\n\\|/ \\|/ \\|/ \\|/ \\|/ \\|/");
		
		System.out.println("/|\\ /|\\ /|\\ /|\\ /|\\ /|\\");
		System.out.println("\nSample output 5\n");
		System.out.println("Testing album equality");
		System.out.println("Two empty albums are equal:");
		Album album1 = new Album("Album1");
		Album album2 = new Album("Album2");
		System.out.println("Are Album1 and Album2 equal? " + album1.equals(album2));
		System.out.println("Two albums with same photos in same order are equal:");
		Photo photo1 = new Photo("Sunrise", "abc123");
		Photo photo2 = new Photo("Sunset", "def456");
		album1.addPhoto(photo1);
		album1.addPhoto(photo2);
		album2.addPhoto(photo1);
		album2.addPhoto(photo2);
		
		System.out.println("Are Album1 and Album2 equal? " + album1.equals(album2));
		System.out.println("Two albums with same photos in different order are equal:");
		Photo photo3 = new Photo("Can of Beans", "ghi789");
		album1.addPhoto(photo3);
		Album album3 = new Album("Album3");
		album3.addPhoto(photo3);
		album3.addPhoto(photo1);
		album3.addPhoto(photo2);
		
		System.out.println("Are Album1 and Album3 equal? " + album1.equals(album3));
		System.out.println("Two albums with different numbers of photos are not equal:");
		album1.deletePhoto(photo1);
		
		System.out.println("Are Album1 and Album3 equal? " + album1.equals(album3));
		System.out.println("Are Album3 and Album1 equal? " + album3.equals(album1));
		System.out.println("Two albums with different photos are not equal:");
		
		
		System.out.println("Are Album1 and Album2 equal? " + album1.equals(album2));
		System.out.println("\n\\|/ \\|/ \\|/ \\|/ \\|/ \\|/");
		
		System.out.println("/|\\ /|\\ /|\\ /|\\ /|\\ /|\\");
		System.out.println("\nSample output 6\n");
		System.out.println("Testing edge cases");
		Album album = new Album("My Cool Album");
		Photo photo1 = new Photo("Sunset", "abc123");
		Photo photo2 = new Photo("Sunrise", "def456");
		Photo photo3 = new Photo("Can of Beans", "ghi789");
		album.addPhoto(photo1);
		album.addPhoto(photo2);
		album.addPhoto(photo3);
		System.out.println("\nDeleting current photo should make next photo current:");
		album.openAlbum();
		album.viewNextPhoto();
		album.closeAlbum();
		album.deletePhoto(photo2);
		album.openAlbum();
		album.closeAlbum();
		album.deletePhoto(photo3);
		album.openAlbum();
		album.closeAlbum();
		System.out.println("\nAlbum with no photos should be considered viewed:");
		album.deletePhoto(photo1);
		System.out.println("Is album empty? " + album.isEmpty());
		System.out.println("Are all photos in album viewed? " + album.allPhotosViewed());
		System.out.println("\nAlbum that is viewed should become unviewed once new photo is added:");
		album.addPhoto(new Photo("Shredding the Gnar", "012345"));
		album.openAlbum();
		System.out.println("Are all photos in album viewed? " + album.allPhotosViewed());
		album.addPhoto(new Photo("Hanging Ten", "678901"));
		System.out.println("Are all photos in album viewed? " + album.allPhotosViewed());
		System.out.println("\n\\|/ \\|/ \\|/ \\|/ \\|/ \\|/");
		*/
	}
	
}