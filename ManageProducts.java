import java.io.File;
import java.util.Scanner;

public class ManageProducts {
	
	public static Scanner scan = new Scanner (System.in); // to read from manager if he want to add new product
    public static LinkedList<Products> products = new LinkedList<Products> (); // new linked list for all products
    
    //to get all product
    public LinkedList<Products>getproductsData(){
        return products;
    }
    
    //here we read from the excel file (prodcuts)
    public ManageProducts(String fName){
       try{
                File data = new File(fName);
                Scanner read = new Scanner (data);
                String byline = read.nextLine(); // we read it here because we want to skip the first row from file(titles)
                
                while(read.hasNext()){
                    byline = read.nextLine();
                    String [] alldata = byline.split(","); //split every row to cells every cell have one index in array
                    
                    int proId = Integer.parseInt(alldata[0]);
                    String proName =  alldata[1].trim(); // لازم اراجعها
                    double proPrice = Double.parseDouble(alldata[2]);
                    int proStock = Integer.parseInt(alldata[3]);

                    Products prod = new Products(proId, proName, proPrice, proStock ); 
                    products.insert(prod); // insert one object in Products linked list
                }
                read.close();
            }
            catch (Exception ex) { // in case there is an exception while we read from the file
                System.out.println(ex.getMessage());
            }
    }
  
    public void addaNewProduct(){ // if manager want to add new product
        System.out.println("Hello There, Enter The Product ID That You Want To Add: ");
        int proId = scan.nextInt(); //Read new product info from manager
        
        while (validatProductId(proId)){ // if the product already in the list
            System.out.println("Sorry Add Failed, This ID Is Already Teken For Another Product! Try Another ID: ");
            proId = scan.nextInt();
        }
        System.out.println("Enter New Product Name: ");
        scan.next(); // clean garbage
        String proname =scan.nextLine();
        
        System.out.println("Enter Price: ");
        double proprice = scan.nextDouble();
        
        System.out.println("How Much stock Is Available?: ");
        int prostock = scan.nextInt();
        
        Products product = new Products(proId, proname, proprice, prostock);
        products.findFirst(); // to add in the first node available in the linked list
        products.insert(product);
        }
 
        public Products searchProductById(){ //searching for product ID
        if (products.empty())
        {
            System.out.println("There Is No Products Currently!"); // if the list of product empty
        }
        else{
            System.out.println("Please Enter Product ID To Search: ");
            int proId = scan.nextInt();
            
            boolean founded = false;
            
            products.findFirst(); // start from the first node
            while (!products.last())
            {
                if (products.retrieve().getProductId()== proId) // checking 
                {
                    founded = true; // found the product in the list
                    break;
                }
                products.findNext();
            }
            if (products.retrieve().getProductId()== proId) //checking the last node
                founded = true;
        
            if (founded)
                return products.retrieve();
        }
        System.out.println("Incorrect Product ID!");
        return null;
    }

    public Products deleteProduct(){
            if (products.empty()){
                System.out.println("There Is No Products To Delete!");
            }
            else{
                System.out.println("Please Enter product ID: ");
                int proID = scan.nextInt();
                
                boolean founded = false;
                
                products.findFirst();
                while (!products.last()){
                    if (products.retrieve().getProductId()== proID) {
                        founded = true;
                        break;
                    }
                    products.findNext();
                }
                if (products.retrieve().getProductId()== proID)
                    founded = true;
            
                if (founded){
                    Products p = products.retrieve();
                    products.remove();
                    p.setStock(-1);
                    return p;
                }
            }
            System.out.println("Incorrect Product ID!");
            return null;
        }

  
    public Products EditProduct(){
        if (products.empty())
        {
            System.out.println("There is No Product To Update!"); //empty list
        }
        else{
            System.out.println("Please Enter Product ID: ");
            int proId = scan.nextInt(); // read from user
            
            boolean founded = false;
            
            products.findFirst(); // to start searching from the first node
            while (!products.last()){
                if (products.retrieve().getProductId()== proId)
                {
                    founded = true;
                    break;
                }
                products.findNext();
            }
            if (products.retrieve().getProductId()== proId)
                founded = true;
        
            if (founded){
                Products pro = products.retrieve();
                products.remove(); // delete the node to update on it then inserted 
                
                System.out.println("What Do You Want To Update?, Select A Choice Please: ");
                System.out.println("1-Update Name");
                System.out.println("2-Update price");
                System.out.println("3-Update stock");
                System.out.println("0-Exit");
                int ch = scan.nextInt();
                
                switch (ch){
                    case 1: //update product name
                    {
                        System.out.println("Enter The New Product Name:");
                        scan.next();//clean garbage
                        pro.setName(scan.nextLine());
                        products.insert(pro); //insert again
                        System.out.println("Product (" + pro.getProductId() + ") Name Updated Successfully!");
                    }
                    break;
                    case 2: //update product price
                    {
                        System.out.println("Enter The New Product Price:");
                        pro.setPrice(scan.nextDouble());
                        products.insert(pro); //insert again
                        System.out.println("Product (" + pro.getProductId() + ") Price Updated Successfully!");
                    }
                    break;
                    case 3: //update product stock
                    {
                        System.out.println("Enter The New Product Stock:");
                        pro.setStock(scan.nextInt());
                        products.insert(pro);//insert again
                        System.out.println("Product (" + pro.getProductId() + " )Stock Updated Successfully!");
                    }
                    break;
                    case 0: //exit
                    {
                    	break;
                    }
                    default:
                        System.out.println("Invalide Choise!");
                }
                return pro;
            }
        }
        System.out.println("Incorrect Product ID (Not Found)!");
        return null;
    }
  
    public Products searchProducByName(){
        if (products.empty())
        {
            System.out.println("There Is No Products Currently!"); // if the list of product empty
        }
        else{
            System.out.println("Please Enter Product Name To Search: ");
            String proName = scan.nextLine();
            
            boolean founded = false;
            
            products.findFirst(); // start from the first node
            while (!products.last())
            {
                if (products.retrieve().getName().compareToIgnoreCase(proName)== 0) // checking 
                {
                    founded = true; // found the product in the list
                    break;
                }
                products.findNext();
            }
            if (products.retrieve().getName().compareToIgnoreCase(proName)== 0) //checking the last node
                founded = true;
        
            if (founded)
                return products.retrieve();
        }
        System.out.println("Incorrect Product Name!");
        return null;
    }
 
    public void OutOfStockProducts(){
        if (products.empty())
        {
            System.out.println("There Is No Products Currently!"); //empty list
        }
        else
        {
            products.findFirst(); //start from the first node
            for ( int i = 0 ; i < products.size() ; i++){ // find all product that is out of stock!
                if (products.retrieve().getStock() == 0)
                    System.out.println(products.retrieve());    
                products.findNext();
            }
        } 
    }
    
    public boolean isAvailableProductId(int proID)
    {
        if (! products.empty())
        {
            products.findFirst();
            for ( int i = 0 ; i< products.size() ; i++)
            {
                if (products.retrieve().getProductId() == proID && products.retrieve().getStock() != -1)
                    return true;
                products.findNext();
            }
        }
        return false ;
    }

    public boolean validatProductId(int proID){ //to check is the product in the list?(here we are checking by ID)
        if (! products.empty()){
            products.findFirst();
            for ( int i = 0 ; i< products.size() ; i++)
            {
                if (products.retrieve().getProductId() == proID) //checking
                    return true;
                products.findNext();
            }
        }
        return false ;
    }

    public Products ProductInfo(int proID){ //return All Info of Product by ID
        if (! products.empty())
        {
            products.findFirst();
            while (!products.last())
            {
                if (products.retrieve().getProductId() == proID)
                    return products.retrieve();
                products.findNext();
            }
            if (products.retrieve().getProductId()== proID)
                return products.retrieve();
        }
        return null;
    }

}
