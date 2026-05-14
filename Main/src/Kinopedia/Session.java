package Kinopedia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Kinopedia.DataUser;
/**
 *
 * @author Victus
 */
public class Session {
    private static Session instance;
    private DataUser currentUser;

    // Private constructor - tidak bisa di-new dari luar
    private Session() {}

    // Ambil instance (selalu sama)
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void login(DataUser user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public DataUser getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
