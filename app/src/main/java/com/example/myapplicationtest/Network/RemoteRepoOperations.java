package com.example.myapplicationtest.Network;



import com.example.myapplicationtest.AppSession;
import com.example.myapplicationtest.Models.Users;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RemoteRepoOperations {

    private static RemoteRepoOperations RemoteRepoOperations;

    public static RemoteRepoOperations RemoteRepoInst() {
        if (RemoteRepoOperations == null) {
            RemoteRepoOperations = new RemoteRepoOperations();
        }
        return RemoteRepoOperations;
    }

    public Observable<Users> users(long page, long perPage) {
        AppSession.operations.show();
        return RemoteRepo.RemoteRepoInst().endpoints.users(page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
