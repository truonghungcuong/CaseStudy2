package com.codegym.view;

import com.codegym.controller.leaderlogin.LeaderLogin;
import com.codegym.controller.userlogin.UserLogin;
import com.codegym.model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {
    public Scanner scanner = new Scanner(System.in);
    private UserLogin userLogin = new UserLogin();
    private LeaderLogin leaderManagement = new LeaderLogin();
    private CustomerMenu customerMenu = new CustomerMenu();
    private LeaderMenu leaderMenu = new LeaderMenu();

    private void menu() {
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("0. Quay lại");
    }

    public void run() {
        int choice = -1;
        do {
            System.out.println("---Ứng dụng quản lý---");
            menu();
            System.out.println("Nhập lựa chọn");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    menuLogin();
                    int choiceLogin = -1;
                    choiceLogin = scanner.nextInt();
                    scanner.nextLine();
                    do {
                        switch (choiceLogin) {
                            case 1: {
                                System.out.println("Tài khoản quản lý");
                                doLoginLeader();

                                break;
                            }
                            case 2: {
                                System.out.println("Tài khoản khách");
                                doLoginUser();
                                break;
                            }
                        }
                        break;
                    } while (choiceLogin != 0);
                    break;
                }
                case 2: {
                    doRegister();
                    break;
                }
            }
        } while (choice != 0);
    }

    private void menuLogin() {
        System.out.println("1. Tài khoản quản lý");
        System.out.println("2. Tài khoản khách");
        System.out.println("0. quay lại");
    }

    private void doLoginLeader() {
        System.out.println("Nhập Username:");
        String username = scanner.nextLine();
        System.out.println("Nhập Password:");
        String psssword = scanner.nextLine();
        boolean isLogin = leaderManagement.checkLoginLeader(username, psssword);
        if (isLogin) {
            System.out.println("Đăng nhập thành công!");
            leaderMenu.run();
        } else {
            System.out.println("Username hoặc Password không đúng!");
            doLoginLeader();
        }
    }

    private void doLoginUser() {
        System.out.println("Nhập Username:");
        String username = scanner.nextLine();
        System.out.println("Nhập Password:");
        String psssword = scanner.nextLine();
        boolean isLogin = userLogin.checkUserLogin(username, psssword);
        if (isLogin) {
            System.out.println("Đăng nhập thành công!");
            customerMenu.run();
        } else {
            System.out.println("Username hoặc Password không đúng!");
            doLoginUser();
        }
    }

    private void doRegister() {
        System.out.println("Đăng ký tài khoản mới");
        User user = createUser();
        userLogin.register(user);
    }

    private User createUser() {
        String username = inputUsername(userLogin);
        String password = inputPassword();
        User user = new User(username, password);
        return user;
    }

    private boolean check(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private String inputUsername(UserLogin userLogin) {

        while (true) {
            System.out.println("Nhập ten tài khoản(6-12 ký tự, không có ký tự đặc biệt, không có ký tự in hoa)");
            String username = scanner.nextLine();
            final String REGEX_USERNAME = "^[_a-z0-9]{6,12}$";
            boolean isCheckUserName = check(REGEX_USERNAME, username);
            boolean isCheckUserNameExist = userLogin.checkUsernameExist(username);
            if (isCheckUserName && isCheckUserNameExist == false) {
                System.out.println("Tên tài khoản hợp lệ");
                return username;
            } else if (isCheckUserName == false) {
                System.err.println("Tên tài khoản(6-12 ký tự, không có ký tự đặc biệt, không có ký tự in hoa)");
            } else if (isCheckUserNameExist) {
                System.err.println("Tài khoản đã tồn tại");
            }
        }

    }

    private String inputPassword() {

        while (true) {
            System.out.println("Nhập mật khẩu(6-12 ký tự, ít nhất một chữ in hoa)");
            String password = scanner.nextLine();
            final String REGEX_PASSWORD = "^.*[A-Z].*$";
            boolean isCheckPassword = check(REGEX_PASSWORD, password);
            if (isCheckPassword) {
                System.out.println("Mật khẩu hợp lệ");
                return password;
            } else {
                System.err.println("Nhập mật khẩu(6-12 ký tự, ít nhất một chữ in hoa)");

            }
        }
    }
}