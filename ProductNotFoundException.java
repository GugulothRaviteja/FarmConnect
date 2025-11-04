package exceptions;

//public class ProductNotFoundException extends RuntimeException {
//    public ProductNotFoundException(String message) {
//        super(message);
//    }
//}

//service import exception
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}