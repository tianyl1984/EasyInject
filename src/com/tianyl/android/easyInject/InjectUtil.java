package com.tianyl.android.easyInject;

import java.lang.reflect.Field;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

public class InjectUtil {

	public static void inject(Activity activity) {
		Field[] fields = activity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				InjectView injectView = field.getAnnotation(InjectView.class);
				if (injectView != null) {
					int viewId = injectView.id();
					try {
						field.setAccessible(true);
						field.set(activity, activity.findViewById(viewId));
					} catch (Exception e) {
						e.printStackTrace();
						throw new InjectException("inject view fail!");
					}

					View view = activity.findViewById(viewId);

					String clickMethod = injectView.click();
					if (!TextUtils.isEmpty(clickMethod)) {
						bindClick(activity, view, clickMethod);
					}

					String longClickMethod = injectView.longClick();
					if (!TextUtils.isEmpty(longClickMethod)) {
						bindLongClick(activity, view, longClickMethod);
					}

					String itemClickMethod = injectView.itemClick();
					if (!TextUtils.isEmpty(itemClickMethod)) {
						bindItemClick(activity, view, itemClickMethod);
					}

					String itemLongClickMethod = injectView.itemLongClick();
					if (!TextUtils.isEmpty(itemLongClickMethod)) {
						bindItemLongClick(activity, view, itemLongClickMethod);
					}
				}
			}
		}
	}

	public static void bindClick(Object handler, View view, String clickMethod) {
		view.setOnClickListener(new EventListener(handler).click(clickMethod));
	}

	public static void bindLongClick(Object handler, View view, String clickMethod) {
		view.setOnLongClickListener(new EventListener(handler).longClick(clickMethod));
	}

	@SuppressWarnings("rawtypes")
	public static void bindItemClick(Object handler, View view, String itemClickMethod) {
		if (view instanceof AdapterView) {
			((AdapterView) view).setOnItemClickListener(new EventListener(handler).itemClick(itemClickMethod));
		} else {
			throw new InjectException("View Has Not OnItemClickListener. View:" + view.getClass().getSimpleName());
		}
	}

	@SuppressWarnings("rawtypes")
	public static void bindItemLongClick(Object handler, View view, String itemLongClickMethod) {
		if (view instanceof AdapterView) {
			((AdapterView) view).setOnItemLongClickListener(new EventListener(handler).itemLongClick(itemLongClickMethod));
		} else {
			throw new InjectException("View Has Not OnItemLongClickListener. View:" + view.getClass().getSimpleName());
		}
	}
}
