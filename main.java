import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    
    public static Scanner scan = new Scanner (System.in);
    
    public static ManageProducts prodata = new ManageProducts("prodcuts.csv");
    public static LinkedList<Products> products;
    
    public static ManageReviews revdata = new ManageReviews("reviews.csv");
    public static LinkedList<Reviews> reviews;
    
    public static void main(String[] args) {
    CollData();
    int ch;
    do {
    	 System.out.println("---------------------------");
         System.out.println("Welcom in The System!");
         System.out.println("What Do You Whant To Do Today?, Select A Choice:");
         System.out.println("1- Manage The Products");
         System.out.println("2- Manage The Customers");
         System.out.println("3- Manage The Orders");
         System.out.println("4- Manage The Reviews");
         System.out.println("0-Exist");
         System.out.println("----------------------------");
        ch = scan.nextInt();
        switch (ch)
        {
        case 1:
        productsMenu();
        break;
        case 2:
        // CustomersMenu();
        break;
        case 3:
        //  OrdersMenu();
        break;
        case 4:
        ReviewsMenu();
        break;
        case 0:
        break;
        default:
        System.out.println("Invalid Choice!, Please Select Number From The Menu: ");
                }
        }while (ch!= 0);
    }
    public static void CollData()
    {// until we done!!!!!!!
    }
    
    public static void productsMenu()
    {
        int ch;
        do {
        System.out.println("-------------Products Menu--------------");
        System.out.println("1- Add New Product");
        System.out.println("2- Delete Product");
        System.out.println("3- Update Product");
        System.out.println("4- Search For Product By ID");
        System.out.println("5- Search For Product by Name");
        System.out.println("6- Veiw All Out Of Stock Products");
        System.out.println("0- Back To The Main Menu");
        System.out.println("Select One Choice Plese:");
        System.out.println("----------------------------------------");
        ch = scan.nextInt();
        switch (ch)
        {
            case 1:
                prodata.addaNewProduct();
                break;
            case 2:
           /* {
                System.out.println("");
                Products pro = prodata.deleteProduct();
                boolean canDelete = true;       
                if(pro!=null){
                    if (pro.getReviews().size() == 0){
                        boolean founded = false;
                        orders.findFirst();
                        for ( int x = 0 ; x < orders.size() ; x++){
                            if (!orders.retrieve().getProducts().empty()){
                                orders.retrieve().getProducts().findFirst();
                                for ( int counter = 0 ; counter < orders.retrieve().getProducts().size() ; counter ++){
                                    if (orders.retrieve().getProducts().retrieve() == pro.getProductId()){
                                         founded = true;   
                                         break;
                                    }
                                    orders.retrieve().getProducts().findNext();
                                }
                            }
                            if (founded)
                                break;
                            orders.findNext();
                        }
                        if(founded) //has order
                        {
                            products.insert(pro);
                            System.out.println("");
                            canDelete = false;
                        }
                    }
                    else // has review
                    {
                        products.insert(pro);
                        System.out.println("");
                        canDelete = false;
                    }
                }
                if (canDelete)
                System.out.println("product deleted");
            }
            break; */
            case 3:
                prodata.EditProduct();
                break;
            case 4:
            {
                Products pro = prodata.searchProductById();
                if (pro != null) //the product found on the list
                    System.out.println(pro);
            }
            break;
            case 5:
            {
                Products pro = prodata.searchProducByName();
                if (pro != null) //the product found on the list
                    System.out.println(pro);
            }
            break;
            case 6:
                prodata.OutOfStockProducts();
                break;
            case 0:
            	break;
            default:
                System.out.println("Invalid Choice!, Try Again");
        }
        }while(ch!=0);
        
    }
    public static void ReviewsMenu()
    {
    	System.out.println("---------------Reviews Menu-------------");
        System.out.println("1- Add A New Review");
        System.out.println("2- Edit Review ");
        System.out.println("3- Veiw Product With Average Rating");
        System.out.println("4- Veiw The Top 3 Products");
        System.out.println("5- Veiw Products With Average Rating > 4 Between 2 Cusomers");
        System.out.println("0- Return To The Main Menu");
        System.out.println("Select One Choice Plese:");
        System.out.println("----------------------------------------");
        int ch = scan.nextInt();;
        switch (ch)
        {
            case 1:
                AddNewReview();
                break;
            case 2:
                revdata.EditReview();
                break;
            case 3:
            {
                System.out.println("Enter The Product ID:");
                int proid = scan.nextInt();

                while (!prodata.validatProductId(proid))
                {
                    System.out.println(" The ID Is Wrong , Try Again: ");
                    proid = scan.nextInt();
                }
                float Avr = avgRating(proid);
                System.out.println("The Average Rating for (" + proid + ") = " + Avr);
            }
            break;
            case 4:
                top3Products();
                break;
           /* case 5:
            {
                Customer cid1 =cdata.getCustomerID();
                Customer cid2 =cdata.getCustomerID();
                commonProducts(cid1.getCustomerId(), cid2.getCustomerId());
            }
            break;*/
            case 0:
                break;
            default:
            	System.out.println("Invalid Choice!, Try Again");
        }
    }
    
    public static void AddNewReview()
    {
        System.out.println("Enter Cutomer ID:");
        int cID =scan.nextInt();
        //while ( ! cdata.checkCustomerID(cID))
        {
            System.out.println("Customer ID Is Not Correct, Try Another One:");
            cID =scan.nextInt();
        }
        
        System.out.println("Enter The Product ID:");
        int proID =scan.nextInt();
        while ( ! prodata.isAvailableProductId(proID))
        {
            System.out.println("Sorry Can't Add Review Somthing Wrong With Product ID , Try Another One:");
            proID =scan.nextInt();
        }
        
        Reviews rev = revdata.addNewReview(cID, proID);
        System.out.println("---------Review Added Successfully----------");
        System.out.println("--- Review With ID (" + rev.getReviewId()+ ") Has Been Added For " + rev.getProduct()+ "By Customer With ID (" + rev.getCustomer() 
        +") With Rate (" + rev.getRating() + ") And Comment: " + rev.getComment() +" ---" );
        System.out.println("--------------------------------------------");
    }
    
    //Get an average rating for product.
    public static float avgRating(int proid)
    {
        int count=0;
        float rate = 0;
        
        reviews.findFirst();
        while (! reviews.last())
        {
            if (reviews.retrieve().getProduct() == proid)
            {
                count++;
                rate += reviews.retrieve().getRating();
            }
            reviews.findNext();
        }
        if (reviews.retrieve().getProduct() == proid)
        {
            count++;
            rate += reviews.retrieve().getRating();
        }
        
        return (rate/count);
    }
    public static void top3Products()
    {
        LinkedPQ<Products> top3 = new LinkedPQ<Products> ();
        
        if (!products.empty())
        {
            products.findFirst();
            for (int i = 0 ; i < products.size() ; i++)
            {
                
                Products pro = products.retrieve();
                float AVGrating = avgRating (pro.productId);
                top3.enqueue(pro, AVGrating);
                
                products.findNext();
            }
            
        }
        
        System.out.println("The Top 3 Products By Average Rating (DESC Order)");
        for ( int j = 1 ; j <= 3 && top3.length() > 0 ; j++)
        {
            PQElement<Products> top = top3.serve();
            System.out.println("Product " + j + "  " + top.data.getProductId() 
                    + " " + top.data.getName() + " Average Rate (" + top.priority + ")" );
        }
    }
    public static void commonProducts( int cid1 , int cid2)
    {
        LinkedList<Integer> procust1 = new LinkedList<Integer> ();
        LinkedList <Integer> procust2 = new LinkedList <Integer> ();
        reviews = revdata.getreviewsData();
        
        if (! reviews.empty())
        {
            reviews.findFirst();
            for (int i =1 ;i <= reviews.size() ; i++)
            {
                if (reviews.retrieve().getCustomer() == cid1 )
                {
                    procust1.findFirst();
                    boolean found1 = false;
                    for (int x = 1; x <= procust1.size() ; x++)
                    {
                        if (procust1.retrieve() == reviews.retrieve().getProduct())
                        {
                            found1 = true;
                            break;
                        }
                        procust1.findNext();
                    }
                    procust1.findFirst();
                    if (! found1 )
                        procust1.insert(reviews.retrieve().getProduct());
                }
                
                if (reviews.retrieve().getCustomer() == cid2 )
                {
                    procust2.findFirst();
                    boolean found2 = false;
                    for (int x = 1; x <= procust2.size() ; x++)
                    {
                        if (procust2.retrieve() == reviews.retrieve().getProduct())
                        {
                            found2 = true;
                            break;
                        }
                        procust2.findNext();
                    }
                    
                    procust2.findFirst();
                    if (! found2 )
                        procust2.insert(reviews.retrieve().getProduct());
                }
                reviews.findNext();
            }
            
            procust1.print();
            procust2.print();
            LinkedPQ<Products> avrg4out5 = new LinkedPQ<Products> ();
            if (! procust1.empty() && ! procust2.empty())
            {
                procust1.findFirst();
                for ( int m =1; m <= procust1.size() ; m++)
                {
                    int proID = procust1.retrieve();
                    procust2.findFirst();
                    for (int n = 1 ; n <= procust2.size() ; n++)
                    {
                        if ( proID == procust2.retrieve())
                        {
                            float AVGrating = avgRating (proID);
                            if ( AVGrating >= 4)
                            {
                                Products pro = prodata.ProductInfo(proID);
                                avrg4out5.enqueue(pro, AVGrating);
                            }
                        }
                        procust2.findNext();
                    }
                    procust1.findNext();
                }                
                System.out.println("Common Products With Rate Above 4 : ");
                while (avrg4out5.length() > 0)
                {
                    PQElement<Products> product_rate = avrg4out5.serve();
                    System.out.println(" Product (" + product_rate.data.productId + ") " + product_rate.data.getName() + " with Rate " + product_rate.priority );
                    System.out.println(product_rate.data);
                    System.out.println("\n");
                }
            }
            else
                System.out.println("There Is No Common Products Between these Two Customers!");
        }
        else
            System.out.println("Something Went Wrong!");
    }
    
}
