package shuttlegui;

import shuttlegui.system.WindowManager;

public class Main {
	public static void main(String[] args) {
		WindowManager.dispatch(window -> window);
	}
}
