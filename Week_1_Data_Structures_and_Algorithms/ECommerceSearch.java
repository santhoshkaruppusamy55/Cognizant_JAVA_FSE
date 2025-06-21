package Week_1_Data_Structures_and_Algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ECommerceSearch {
    public static void main(String[] args) {
        Product[] products = {
                new Product(101, "Lap", "Electronics"),
                new Product(102, "Shirt", "Clothing"),
                new Product(103, "Mobile", "Electronics"),
                new Product(104, "Shoes", "Footwear"),
                new Product(105, "Book", "Stationery")
        };

        Product target = products[0];
        int linearIndex = Search.linearSearch(products, target.getProductId());
        System.out.println("liner_Index : " + linearIndex);
        System.out.println("found : "+ products[linearIndex].toString());

        Arrays.sort(products, Comparator.comparingInt(Product::getProductId));
        int binaryIndex = Search.binarySearch(products, target.getProductId());
        System.out.println("Binary_Index: " + binaryIndex);
        System.out.println("found : "+ products[binaryIndex].toString());

        Product tar = products[4];
        int Index = Search.linearSearch(products, tar.getProductId());
        System.out.println("liner_Index : " + Index);
        System.out.println("found : "+ products[Index].toString());

        Arrays.sort(products, Comparator.comparingInt(Product::getProductId));
        int bin_Index = Search.binarySearch(products, tar.getProductId());
        System.out.println("Binary_Index: " + bin_Index);
        System.out.println("found : "+ products[bin_Index].toString());
    }
}

class Product {
    private final int productId;
    private final String productName;
    private final String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String toString() {
        return productId + " - " + productName + " - " + category;
    }
}

class Search {
    public static int linearSearch(Product[] products, int productId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId() == productId) return i;
        }
        return -1;
    }

    public static int binarySearch(Product[] products, int productId) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].getProductId() == productId) return mid;
            if (products[mid].getProductId() < productId) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
