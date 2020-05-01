package shuttlegui;

import java.awt.Font;

import shuttlegui.system.ButtonNode;
import shuttlegui.system.DropdownBoxNode;
import shuttlegui.system.EditorPaneNode;
import shuttlegui.system.PanelNode;
import shuttlegui.system.TextFieldNode;
import shuttlegui.system.WindowManager;

public class Main {
	public static void main(String[] args) {
		WindowManager.dispatch(window -> window
				.size(720, 480)
				.child(new ButtonNode("Example")
						.setFont(new Font(Font.SERIF, Font.BOLD, 15))
						.setSize(100, 50))
				.child(new PanelNode()
						.child(new TextFieldNode(20)
								.setSize(100, 10))
						.child(new DropdownBoxNode()
								.addOptions("yes", "no", "maybe")))
				.child(new PanelNode("yes")
						.child(new EditorPaneNode()
								.onBuild(pane -> {
									pane.setText("YES\nHello wow yes\nepic");
									pane.f
								})
								.setSize(300, 300))));
	}
}
