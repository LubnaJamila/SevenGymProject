package com.febri.sevengymproject;

import java.util.List;

public interface PermissionListener extends com.gun0912.tedpermission.PermissionListener {
    void onPermissionGranted();
    void onPermissionDenied(List<String> deniedPermission);
}
