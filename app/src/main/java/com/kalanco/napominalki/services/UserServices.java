package com.kalanco.napominalki.services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.kalanco.napominalki.moduls.User;

public class UserServices {

    public static Task<Void> storeUser(User user, String id) {
        return DatabaseServices.addUser(user, id);
    }

    public static Task<Void> delUser(String id) {
        return DatabaseServices.remove(id);
    }

    public static Task<Void> editName(User user) {
        return DatabaseServices.setString(String.format("users/%s/name", user.id), user.name);
    }
    public static void editEmail(User user) {
        Task<Void> base = DatabaseServices.getDatabase().getReference("users").setValue(user.toString());
    }
}
