package com.codegym.controller.product;

import com.codegym.model.Product;

import java.io.*;
import java.util.*;

public class ProductManagement implements IProductManagement {
    private List<Product> products = new ArrayList<>();
    private Map<Product, Integer> customerProduct = new HashMap<>();
    private List<Integer> turnover = new ArrayList<>();
    private Map<Product, Integer> productSold = new HashMap<>();
    private List<Integer> totalPaymoney = new ArrayList<>();
    private static final ProductManagement INSTANCE = new ProductManagement();

    public List<Product> getByProduct() {
        return (List<Product>) customerProduct;
    }

    public Map<Product, Integer> getCustomerProduct() {
        return customerProduct;
    }

    public Map<Product, Integer> getProductSold() {
        return productSold;
    }

    public void setProductSold(Map<Product, Integer> productSold) {
        this.productSold = productSold;
    }

    public void setCustomerProduct(Map<Product, Integer> customerProduct) {
        this.customerProduct = customerProduct;
    }

    public void setByProduct(List<Product> byProduct) {
        this.customerProduct = (Map<Product, Integer>) byProduct;
    }

    public int size() {
        return products.size();
    }

    public void ReadFileCustomer(String s) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream("productcustomer.txt");
        ObjectInputStream ois = new ObjectInputStream(is);
        this.customerProduct = (Map<Product, Integer>) ois.readObject();
    }

    public void WriteFileCustomer(String s) throws IOException {
        OutputStream os = new FileOutputStream("productcustomer.txt");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.customerProduct);
    }

    public void ReadFileProductsSold(String s) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream("productsold.txt");
        ObjectInputStream ois = new ObjectInputStream(is);
        this.productSold = (Map<Product, Integer>) ois.readObject();
    }

    public void WriteFileProductSold(String s) throws IOException {
        OutputStream os = new FileOutputStream("productsold.txt");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.productSold);
    }

    public void ReadFileLeader(String s) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream("productleader.txt");
        ObjectInputStream ois = new ObjectInputStream(is);
        this.products = (List<Product>) ois.readObject();
    }

    public void WriteFileLeader(String s) throws IOException {
        OutputStream os = new FileOutputStream("productleader.txt");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.products);
    }

    public void ReadFileTurnover(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.turnover = (List<Integer>) ois.readObject();
    }

    public void WriteFileTurnover(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.turnover);
    }
    public void ReadFileTotalPaymoney(String path) throws IOException, ClassNotFoundException {
        InputStream is =new FileInputStream(path);
        ObjectInputStream ois =new ObjectInputStream(is);
        this.totalPaymoney= (List<Integer>) ois.readObject();
    }
    public void WriteFileTotalPaymoney(String path) throws IOException {
        OutputStream os=new FileOutputStream(path);
        ObjectOutputStream oos =new ObjectOutputStream(os);
        oos.writeObject(this.totalPaymoney);
    }

    private ProductManagement() {
    }

    public static ProductManagement getInstance() {
        return INSTANCE;
    }

    public int findProductById(String id) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void displayProductSold() {
        for (Map.Entry<Product, Integer> entry : productSold.entrySet()) {
            System.out.println(entry.getKey() + " -Số lượng đã bán: " + entry.getValue());
        }
    }

    public void displayByProductCustomer() {
        for (Map.Entry<Product, Integer> entry : customerProduct.entrySet()) {
            System.out.println(entry.getKey() + " -Số lượng mua: " + entry.getValue());
        }
    }

    public void clearProductSold() {
        this.productSold.clear();
    }

    public void clearLeaderTunrover() {
        this.turnover.clear();
    }
    public void clearTotalPaymoney(){
        this.totalPaymoney.clear();
    }

    public void clearCustomerProduct() {
        customerProduct.clear();
    }

    public int PaymentProduct(int index, int quanlity) {
        int payMoney = products.get(index).getPrice() * quanlity;
        this.totalPaymoney.add(payMoney);
        this.turnover.add(payMoney);
        try {
            WriteFileTurnover("turnover.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payMoney;

    }

    public int findProductByName(String name) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    public int totalPaymoney() {
        int total = 0;
        for (int i = 0; i < totalPaymoney.size(); i++) {
            total = total + totalPaymoney.get(i);
        }
        return total;
    }

    public int totalTurnover() {
        int total = 0;
        for (int i = 0; i < turnover.size(); i++) {
            total = total + turnover.get(i);
        }
        return total;
    }

    @Override
    public void displayAll() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Override
    public void addNew(Product product) {
        this.products.add(product);
    }

    @Override
    public void update(int index, Product product) {
        this.products.set(index, product);
    }

    @Override
    public void delete(int index) {
        this.products.remove(index);
    }


    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product getById(int index) {
        return products.get(index);

    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream inputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        this.products = (List<Product>) objectInputStream.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(this.products);

    }
}