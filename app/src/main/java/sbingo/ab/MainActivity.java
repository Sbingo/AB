package sbingo.ab;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AccessibilityManager.AccessibilityStateChangeListener {

    private Button serviceSwitch, start;
    private TextView path;

    private AccessibilityManager accessibilityManager;

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceSwitch = (Button) findViewById(R.id.service_switch);
        start = (Button) findViewById(R.id.start);
        path = (TextView) findViewById(R.id.path);

        accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        accessibilityManager.addAccessibilityStateChangeListener(this);

        serviceSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "请点击老司机并开启", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isFileReady()) {
                    startPackage("com.contextlogic.wish");
//                }
            }
        });

        updateStatus(isServiceEnabled() ? true : false);
    }

    private boolean isFileReady() {
        File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "www");
        File file = new File(dir, "rrr.txt");
        if (file.exists() && file.length() > 0) {
            if (!isFileUsed()) {
                return true;
            } else {
                Toast.makeText(MainActivity.this, "目标文件已使用过，作业取消！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "目标文件不存在或为空文件，无法作业！", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean isFileUsed() {
        // TODO: 2017/3/14 判断文件是否已使用过
        return false;
    }

    @Override
    public void onAccessibilityStateChanged(boolean enabled) {
        Log.i(TAG, "onAccessibilityStateChanged ：" + enabled);
        updateStatus(enabled);
    }

    private void updateStatus(boolean serviceEnabled) {
        serviceSwitch.setEnabled(!serviceEnabled);
        start.setEnabled(serviceEnabled);
    }

    private boolean isServiceEnabled() {
        List<AccessibilityServiceInfo> accessibilityServices =
                accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
        for (AccessibilityServiceInfo info : accessibilityServices) {
            if (info.getId().equals(getPackageName() + "/.MyService")) {
                return true;
            }
        }
        return false;
    }

    private void startPackage(String packageName) {
        PackageManager packageManager = getPackageManager();
        if (packageExist(packageName)) {
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "没有安装" + packageName, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 检查包是否存在
     *
     * @param packageName
     * @return
     */
    private boolean packageExist(String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessibilityManager.removeAccessibilityStateChangeListener(this);
    }
}
