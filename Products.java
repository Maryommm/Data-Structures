public class Products {
	    int productId;
	    String name;
	    double price;
	    int stock;
	    LinkedList <Integer> reviews = new LinkedList <Integer> (); //  list of each product with it's reviews

	    public Products() {
	        this.productId = 0;
	        this.name = "";
	        this.price = 0;
	        this.stock = 0;
	    }

	    public Products(int pid, String n, double p, int s) {
	        productId = pid;
	        name = n;
	        price = p;
	        stock = s;
	    }
        //setter & getter
	    public int getProductId() {
	        return productId;
	    }

	    public void setProductId(int productId) {
	        this.productId = productId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public int getStock() {
	        return stock;
	    }

	    public void setStock(int stock) {
	        this.stock = stock;
	    }

	    public void addStock ( int s)
	    {
	        stock += s; // +1  product if some customer cancel
	    }
	    
	    public void DeleteStock ( int s)
	    {
	        stock -= s; // -1 product if some customer ordered
	    }
	    
	    
	    public void addReview( Integer R) // to add review from customer
	    {
	        reviews.insert(R); 
	    }
	    
	    public boolean DeleteReview( Integer R) // to remove review
	    {
	        if ( ! reviews.empty()) // it should be not empty- at lest there is one review to delete
	        {
	            reviews.findFirst(); // start from the head of the list (first review)
	            while(!reviews.last()) 
	            {
	                if (reviews.retrieve() == R) // found the id of review we want to delete
	                {
	                    reviews.remove();
	                    return true;
	                }
	                else
	                    reviews.findNext();
	            }
	            if (reviews.retrieve() == R) // for the last one 
	            {
	                reviews.remove();
	                return true;
	            }
	        }
	        return false; // there is no such review id in the list
	    }
	    
	    public LinkedList<Integer> getReviews ()
	    {
	        return reviews;
	    }
	    
	    @Override
	    public String toString() {
	        String str =  "\nproductId=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock ;
	        if ( ! reviews.empty())
	        {
	            str += "(reviews List:" ;
	            reviews.findFirst();
	            while(! reviews.last())
	            {
	                str += reviews.retrieve() + " ";
	                reviews.findNext();
	            }
	            str += reviews.retrieve() + " )";
	        }
	        return str;        
	    }
}
