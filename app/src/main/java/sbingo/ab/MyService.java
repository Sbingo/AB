package sbingo.ab;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Author: Sbingo
 * Date:   2017/03/13
 */

public class MyService extends AccessibilityService {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.i(TAG, "onServiceConnected");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(TAG, "onAccessibilityEvent");
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                Log.i(TAG, "TYPE_VIEW_CLICKED");
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                Log.i(TAG, "TYPE_VIEW_LONG_CLICKED");
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                Log.i(TAG, "TYPE_VIEW_SELECTED");
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                Log.i(TAG, "TYPE_VIEW_FOCUSED");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                Log.i(TAG, "TYPE_VIEW_TEXT_CHANGED");
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.i(TAG, "TYPE_WINDOW_STATE_CHANGED");
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                Log.i(TAG, "TYPE_NOTIFICATION_STATE_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                Log.i(TAG, "TYPE_VIEW_HOVER_ENTER");
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                Log.i(TAG, "TYPE_VIEW_HOVER_EXIT");
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                Log.i(TAG, "TYPE_TOUCH_EXPLORATION_GESTURE_START");
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                Log.i(TAG, "TYPE_WINDOW_CONTENT_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                Log.i(TAG, "TYPE_VIEW_SCROLLED");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                Log.i(TAG, "TYPE_VIEW_TEXT_SELECTION_CHANGED");
                break;
            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                Log.i(TAG, "TYPE_ANNOUNCEMENT");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY:
                Log.i(TAG, "TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY");
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                Log.i(TAG, "TYPE_GESTURE_DETECTION_START");
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                Log.i(TAG, "TYPE_GESTURE_DETECTION_END");
                break;
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_START:
                Log.i(TAG, "TYPE_TOUCH_INTERACTION_START");
                break;
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_END:
                Log.i(TAG, "TYPE_TOUCH_INTERACTION_END");
                break;
            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                Log.i(TAG, "TYPE_WINDOWS_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED:
                Log.i(TAG, "TYPE_VIEW_CONTEXT_CLICKED");
                break;
            default:
                Log.i(TAG, "default");
        }
        toRegister(event);
        doRegister(event);
    }

    private void toRegister(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            AccessibilityNodeInfo node = event.getSource();
            List<AccessibilityNodeInfo> nodeList = node.findAccessibilityNodeInfosByText(" CREATE ACCOUNT ");
            if (nodeList.size() > 0) {
                nodeList.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            node.recycle();
        }
    }

    private void doRegister(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {
        Log.i(TAG, "onInterrupt");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}
