package shuttlegui.system;

import java.util.function.UnaryOperator;

import javax.swing.JFrame;

public final class WindowManager {
	private WindowManager() {
		// NO-OP
	}

	public static void dispatch(UnaryOperator<Window> windowSetup) {
		Window window = new Window();
		windowSetup.apply(window);
		JFrame frame = window.build();
		frame.setVisible(true);
	}
}
