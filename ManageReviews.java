import java.io.File;
import java.util.Scanner;

public class ManageReviews {
	public static Scanner scan = new Scanner (System.in); // to read from user
    public static LinkedList<Reviews> reviews = new LinkedList<Reviews>(); //linked list to store All reviews

    public LinkedList<Reviews>getreviewsData(){
        return reviews;
    }

    public ManageReviews(String fName){ 
        try{ //here we read from the excel file (reviews)
        	    File data = new File(fName);
                Scanner read = new Scanner (data);
                String Byline = read.nextLine(); // we read it here because we want to skip the first row from file(titles)
                
                while(read.hasNext()){
                    Byline = read.nextLine();
                    String [] alldata = Byline.split(","); //split every row to cells every cell have one index in array
                    int reId = Integer.parseInt(alldata[0]);
                    int proId = Integer.parseInt(alldata[1]);
                    int cId = Integer.parseInt(alldata[2]);
                    int Rating =  Integer.parseInt(alldata[3]);
                    String Comment =  alldata[4];
                    
                    Reviews rev = new Reviews(reId,proId,cId,Rating,Comment);
                    reviews.insert(rev);
                }
                read.close();
            }
            catch (Exception ex) { // in case there is an error while reading file
                System.out.println(ex.getMessage());
            }
    }
    
    public Reviews addNewReview(int cusID,int proID){ // if user want to add new Review
        System.out.println("Hello There, Enter The Review ID That You Want To Add: ");
        int revId = scan.nextInt();
        
        while (validateRevId(revId)) //make sure that there is no Review with the same id
        {
            System.out.println("Sorry Add Failed, This ID Is Already Teken For Another Review! Try Another ID: ");
            revId = scan.nextInt();
        }        
        
        System.out.println("Enter Rating From 1 To 5: ");
        int rating = scan.nextInt();
        while (rating>5 || rating<1){  //Make sure that the rate value is valid
            System.out.println("Invalid Rating Value, Try From 1 To 5: ");
            rating = scan.nextInt();
        }

        System.out.println("Enter Comment You Want To Add To the Review: ");
        scan.next(); //clean garbage
        String comment = scan.nextLine();

        Reviews review = new Reviews (revId,proID,cusID,rating,comment); // make new review 
        reviews.findFirst();
        reviews.insert(review); // store the review in the linked list
        return review;
    }

    public void EditReview(){
       if(reviews.empty()) { // the list is empty
           System.out.println("There is No Review To Update!");
           return;
       }
       
       Reviews rev = new Reviews();
       System.out.println("Please Enter Review ID to You Want To Update: ");
        int revId = scan.nextInt();
        
        while (!validateRevId(revId)){ // the review id not in the list
            System.out.println("There Is No Review To Update, Try Another ID: ");
            revId = scan.nextInt();
        }        
       
        reviews.findFirst();
        while (!reviews.last()){
            if (reviews.retrieve().getReviewId()==revId) //checking
                break;
           reviews.findNext();
        }
        if (reviews.retrieve().getReviewId() == revId){ // for the last one
            rev = reviews.retrieve();
            reviews.remove(); // delete it from the list to update it first then insert it
            System.out.println("What Do You Want To Update?, Select A Choice Please: ");
            System.out.println("1-Rate");
            System.out.println("2-Comment");
            int ch = scan.nextInt();

            switch(ch){
                case 1:
                {
                    System.out.println("Enter New Rating From 1 To 5: ");
                    int rating = scan.nextInt();
                    while (rating>5 || rating<1)
                    {
                    System.out.println("Invalid Rating Value, Try From 1 To 5: ");
                        rating = scan.nextInt();
                    }
                    rev.setRating(rating);
                    System.out.println("Review (" + rev.getReviewId() + ") Rate Updated Successfully!");
                }
                break;

                case 2:
                {
                    System.out.println("Enter New Comment You Want To Add To the Review: ");
                    scan.next(); //clean garbage
                    String comment = scan.nextLine();
                    rev.setComment(comment);
                    System.out.println("Review (" + rev.getReviewId() + ") Comment Updated Successfully!");
                }
                break;
            }
            reviews.insert(rev); // insert the review after updated
            System.out.println(reviews.retrieve());
        }
   }
   
    public boolean validateRevId(int revId){ //make sure that the id in the list
        if (! reviews.empty()){
            reviews.findFirst();
            for (int i = 0 ; i< reviews.size() ; i++)
            {
                if (reviews.retrieve().getReviewId()== revId)
                    return true;
                reviews.findNext();
            }
        }
        return false ;
    }
}
