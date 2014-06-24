package com.hominhchung.vocabulary.qoutations;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.hominhchung.R;

public class QoutationMainActivity extends FragmentActivity {

	private ViewPager pager;
	private TabsAdapter mTabsAdapter;
	private BroadcastReceiver updateReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		pager = new ViewPager(this);
		pager.setId(R.id.pager);
		setContentView(pager);

		// Lấy dữ liệu từ values>strings.xml
		Resources res = getResources();
		String[] menu_nav = res.getStringArray(R.array.category_apophthegm);

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mTabsAdapter = new TabsAdapter(this, pager);
		for (int i = 0; i < menu_nav.length; i++) {

			// Dua du lieu rieng le vao bundle
			Bundle bundle = new Bundle();
			int num = i + 1;
			String item = "item" + num;
			bundle.putString("message", item);
			bundle.putInt("type_bundle", num);
			mTabsAdapter.addTab(bar.newTab().setText(menu_nav[i]),
					List_View.class, bundle);
		}
	}
}
