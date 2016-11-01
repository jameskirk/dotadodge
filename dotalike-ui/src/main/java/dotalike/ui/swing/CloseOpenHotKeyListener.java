package dotalike.ui.swing;

import javax.swing.JFrame;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;

public class CloseOpenHotKeyListener implements HotKeyListener{
	
	private DotaLike dotaLike;
	
	public CloseOpenHotKeyListener(DotaLike dotaLike) {
		this.dotaLike = dotaLike;
	}

	@Override
	public void onHotKey(HotKey hotKey) {
		if (dotaLike.getState() != JFrame.ICONIFIED) {
			dotaLike.setState(JFrame.ICONIFIED);
		} else {
			dotaLike.setExtendedState(JFrame.NORMAL); 
		}
	}

}
