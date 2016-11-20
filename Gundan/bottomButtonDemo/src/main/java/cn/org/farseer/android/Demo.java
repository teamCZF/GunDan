package cn.org.farseer.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import cn.org.farseer.android.base.BaseBottomMenuActivity;
import cn.org.farseer.android.bottombutton.BottomButton;

public class Demo extends BaseBottomMenuActivity {

	public List<BottomButton> getButtonList() {
		Map<String,String> buttonMaps =  new HashMap<String,String>();
		buttonMaps.put("Menu1", String.valueOf(R.drawable.home));
    	buttonMaps.put("Menu2", String.valueOf(R.drawable.home));
    	buttonMaps.put("Menu3", String.valueOf(R.drawable.home));
    	buttonMaps.put("Menu4", String.valueOf(R.drawable.home));

    	List<BottomButton> buttons = new ArrayList<BottomButton>();
        Iterator<String> itKey = buttonMaps.keySet().iterator();
        while(itKey.hasNext())
        {
        	String key = itKey.next();
        	String valueRes = buttonMaps.get(key);
        	BottomButton oneBottomButton = new BottomButton();
        	oneBottomButton.setText(key);
        	oneBottomButton.setBackgroundResource(Integer.parseInt(valueRes));
        	buttons.add(oneBottomButton);
        }
        return buttons;
	}

	public int getContentViewLayoutResId() { return R.layout.main; }

	protected void onCreatOverride(Bundle savedInstanceState) { }
}