package shuttlegui.test;

import java.awt.Font;

import javax.swing.JEditorPane;

import shuttlegui.system.ButtonNode;
import shuttlegui.system.DropdownBoxNode;
import shuttlegui.system.EditorPaneNode;
import shuttlegui.system.PanelNode;
import shuttlegui.system.TextFieldNode;
import shuttlegui.system.WindowManager;

public class Main {
	static JEditorPane p;
	static boolean b = true;

	public static void main(String[] args) {
		WindowManager.dispatch(window -> window
				.size(480, 200)
				.child(new ButtonNode("Example")
						.setFont(new Font(Font.SERIF, Font.BOLD, 15))
						.setSize(100, 50)
						.executes(a -> {
							b = !b;
							p.setText(b ? "YES\nHello wow yes\nepic" : "Nice");
						}))
				.child(new PanelNode()
						.child(new TextFieldNode(20)
								.setSize(100, 10))
						.child(new DropdownBoxNode()
								.addOptions("yes", "no", "maybe")))
				.child(new PanelNode("yes")
						.child(new EditorPaneNode()
								.onBuild(pane -> {
									p = pane;
									pane.setText("YES\nHello wow yes\nepic");
								})
								.setSize(300, 300))));
	}
}
