package utils.services;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.internal.BottomNavigationItemView;

import java.io.File;
import java.util.List;

import utils.cloud.AccountType;
import utils.cloud.CloudResource;
import utils.tasks.CloudRequestTask;
import utils.tasks.MoveFilesTask;

public interface CloudService {
    interface GenericCallback {
        void onComplete();
        void onError(Exception e);
    }

    interface GetFilesCallback {
        void onComplete(List<CloudResource> files);
        void onError(Exception e);
    }

    interface DownloadFileCallback {
        void onComplete(File file);
        void onError(Exception e);
    }

    interface CreateFolderCallback {
        void onComplete(CloudResource createdFolder);
        void onError(Exception e);
    }

    interface MoveFilesCallback {
        void onComplete(MoveFilesTask.Statistics stats);
    }

    public class AccountDetails {
        public String name;
        public Bitmap photo;
        public long totalStorage;
        public long usedStorage;
    }

    interface GetAccountDetailsCallback {
        void onComplete(AccountDetails details);
        void onError(Exception e);
    }

    CloudRequestTask getFilesTask(ProgressDialog dialog, GetFilesCallback callback);
    CloudRequestTask createFolderTask(CreateFolderCallback callback);
    CloudRequestTask deleteFileTask(GenericCallback callback);
    CloudRequestTask uploadFileTask(File file, ProgressDialog dialog, GenericCallback callback);
    CloudRequestTask downloadFileTask(ProgressDialog dialog, boolean saveTmp, DownloadFileCallback callback);
    CloudRequestTask moveFilesTask(CloudResource sourceFile, Context context, boolean deleteOriginal, MoveFilesCallback callback);
    CloudRequestTask getAccountDetailsTask(GetAccountDetailsCallback callback);
}
