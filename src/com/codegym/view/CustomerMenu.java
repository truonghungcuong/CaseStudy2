package com.codegym.view;

import com.codegym.controller.product.ProductManagement;


import java.io.IOException;
import java.util.Scanner;

public class CustomerMenu {
    public static Scanner scanner = new Scanner(System.in);

    public void run() {
        ProductManagement productManagement = ProductManagement.getInstance();
        int choice = -1;
        try {
            productManagement.ReadFileTotalPaymoney("totalpaymoney.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            productManagement.ReadFileLeader("productleader.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
        try {
            productManagement.ReadFileCustomer("productcustomer.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
        do {
            menu();
            System.out.println("Nhập lựa chọn");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    showAllProduct(productManagement);
                    break;
                }
                case 2: {
                    productManagement.displayAll();
                    productManagement.clearCustomerProduct();
                    productManagement.clearTotalPaymoney();
                    int choicePrice = -1;
                    do {
                        menuPrice();
                        System.out.println("Nhập lựa chọn");
                        choicePrice = scanner.nextInt();
                        if (choicePrice == 1) {
                            System.out.println("Nhập mã sản phẩm muốn mua");
                            scanner.nextLine();
                            String id = scanner.nextLine();
                            System.out.println("Nhập số lượng muốn mua");
                            int quanlity = scanner.nextInt();
                            int index = productManagement.findProductById(id);

                            if (index == -1) {
                                System.out.println("Mã sản phẩm không đúng");
                            } else {
                                if (quanlity > productManagement.getProducts().get(index).getQuanlity()) {
                                    System.out.println("Không đủ sản phẩm");
                                } else {
                                    productManagement.getProductSold().put(productManagement.getProducts().get(index), quanlity);
                                    productManagement.getCustomerProduct().put(productManagement.getProducts().get(index), quanlity);
                                    productManagement.displayByProductCustomer();
                                    productManagement.PaymentProduct(index, quanlity);
                                    System.out.println("Tổng số tiền phải trả: " + productManagement.totalPaymoney());
                                    productManagement.getProducts().get(index).setQuanlity(productManagement.getProducts().get(index).getQuanlity() - quanlity);
                                    try {
                                        productManagement.WriteFileProductSold("productsold.txt");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        productManagement.WriteFileLeader("productleader.txt");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        productManagement.WriteFileCustomer("productcustomer.txt");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        productManagement.WriteFileTotalPaymoney("totalpaymoney.txt");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }
                        }
                    } while (choicePrice != 0);

                    break;
                }
                case 3: {
                    System.out.println("---Lịch sử mua hàng---");
                    productManagement.displayByProductCustomer();
                    break;
                }
                case 4: {
                    productManagement.clearCustomerProduct();
                    System.out.println("Đã xóa");
                    break;
                }
                case 5: {
                    scanner.nextLine();
                    System.out.println("Nhập tên sản phẩm muốn mua: ");
                    String name = scanner.nextLine();
                    int index = productManagement.findProductByName(name);
                    if (index == -1) {
                        System.out.println("Không có sản phẩm!");
                    } else {
                        System.out.println(productManagement.getById(index));
                    }
                    break;
                }
            }
            try {
                productManagement.WriteFileCustomer("productcustomer.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (choice != 0);
    }

    private void showAllProduct(ProductManagement productManagement) {
        int size = productManagement.size();
        if (size == 0) {
            System.out.println("Danh sách rỗng");
        } else {
            System.out.println("Danh sách sản phẩm");
            productManagement.displayAll();
        }
    }

    private void menuPrice() {
        System.out.println("1. Mua ");
        System.out.println("0. Thoát");
    }

    private void menu() {
        System.out.println("1. Hiển thị danh sách sản phẩm");
        System.out.println("2. Mua sản phẩm");
        System.out.println("3. Lịch sử mua hàng");
        System.out.println("4. Xóa lịch sử mua hàng");
        System.out.println("5. Tìm kiếm sản phẩm");
        System.out.println("0. Quay lại");
    }
}