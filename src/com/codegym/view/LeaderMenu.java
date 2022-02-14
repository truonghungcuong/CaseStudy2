package com.codegym.view;

import com.codegym.controller.product.ProductManagement;
import com.codegym.model.Product;

import java.io.IOException;
import java.util.Scanner;

public class LeaderMenu {
    private Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        ProductManagement productManagement = ProductManagement.getInstance();
        int choice = -1;

        try {
            productManagement.ReadFileProductsSold("productsold.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            productManagement.ReadFileTurnover("turnover.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            productManagement.readFile("productleader.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
        do {
            menu();
            System.out.println("Nhập lựa chọn");
            choice = inputNumber.nextInt();
            switch (choice) {
                case 1: {
                    showAllProduct(productManagement);
                    break;
                }
                case 2: {
                    showCreateProduct(productManagement);
                    break;
                }
                case 3: {
                    System.out.println("Nhập mã sản phẩm cần sửa");
                    String id = inputString.nextLine();
                    int index = productManagement.findProductById(id);
                    if (index == -1) {
                        System.out.println("Mã sản phẩm không chính xác!");
                    } else {
                        Product product = inputProductInfo();
                        productManagement.update(index, product);
                        System.out.println("Cập nhật thành công!");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Nhập mã sản phẩm cần xóa");
                    String id = inputString.nextLine();
                    int index = productManagement.findProductById(id);
                    if (index == -1) {
                        System.out.println("Mã sản phẩm không chính xác!");
                    } else {
                        productManagement.delete(index);
                        System.out.println("Xóa thành công!");
                    }
                    break;
                }
                case 5: {
                    int total = productManagement.totalTurnover();
                    System.out.println("Tổng doanh thu trong ngày: " + total + "VNĐ");
                    break;
                }
                case 6: {
                    System.out.println("Danh sách sản phẩm đã bán trong ngày");
                    int size = productManagement.getProductSold().size();
                    if (size==0){
                        System.out.println("Chưa bán được sản phẩm nào!");
                    }else {
                        productManagement.displayProductSold();
                    }
                    break;
                }
                case 7: {
                    productManagement.clearLeaderTunrover();
                    productManagement.clearProductSold();
                    System.out.println("---Đã xóa---");
                }

            }
            try {
                productManagement.WriteFileProductSold("productsold.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                productManagement.writeFile("productleader.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (choice != 0);
    }

    private void showCreateProduct(ProductManagement productManagement) {
        System.out.println("Thêm sản phẩm");
        Product product = inputProductInfo();
        productManagement.addNew(product);
    }

    private Product inputProductInfo() {
        System.out.println("Nhập mã sản phẩm");
        String id = inputString.nextLine();
        System.out.println("Nhập tên sản phẩm");
        String name = inputString.nextLine();
        System.out.println("Nhập giá sản phẩm");
        int price = inputNumber.nextInt();
        System.out.println("Nhập số lượng sản phẩm");
        int quanlyti = inputNumber.nextInt();
        Product product = new Product(id, name, price, quanlyti);
        return product;
    }

    private void showAllProduct(ProductManagement productManagement) {
        int size = productManagement.size();
        if (size == 0) {
            System.out.println("---Danh sách rỗng---");
        } else {
            System.out.println("---Danh sách sản phẩm---");
            productManagement.displayAll();
        }
    }

    private void menu() {
        System.out.println("1. Hiển thị danh sách sản phẩm");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Sửa thông tin sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Tổng doanh thu trong ngày");
        System.out.println("6. Sản phẩm đã bán trong ngày");
        System.out.println("7. Xóa hết giao dịch trong ngày");
        System.out.println("0. Quay lại");
    }
}
