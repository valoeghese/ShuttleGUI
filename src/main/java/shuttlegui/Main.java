package shuttlegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import shuttlegui.system.ButtonNode;
import shuttlegui.system.LabelNode;
import shuttlegui.system.PanelNode;
import shuttlegui.system.TextFieldNode;
import shuttlegui.system.WindowManager;

public class Main {
	public static void main(String[] args) {
		createLaunch();
	}

	private static void createLaunch() {
		WindowManager.dispatch(window -> window
				.title("ShuttleGUI Launcher")
				.size(400, 150)
				.child(new PanelNode("Launcher")
						.setBorderLayout(() -> new BorderLayout(5, 10))
						.child(new LabelNode()
								.setName("Workspace Name:"), BorderLayout.WEST)
						.child(new TextFieldNode(20)
								.onBuild(field -> {
									field.setForeground(Color.GRAY);
									field.addFocusListener(new PlaceholderTextFocusListener(field, "name"));
								}), BorderLayout.CENTER)
						.child(new ButtonNode("Launch"), BorderLayout.SOUTH)));
	}

	private static class PlaceholderTextFocusListener implements FocusListener {
		public PlaceholderTextFocusListener(JTextField parent, String placeholder) {
			this.parent = parent;
			this.placeholderText = placeholder;
			this.parent.setText(placeholder);
		}

		private final String placeholderText;
		private final JTextField parent;

		private boolean noEntry = true;

		@Override
		public void focusGained(FocusEvent e) {
			if (noEntry) {
				this.parent.setText("");
				this.parent.setForeground(Color.BLACK);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			noEntry = this.parent.getText().isEmpty();

			if (noEntry) {
				this.parent.setForeground(Color.GRAY);
				this.parent.setText(this.placeholderText);
			}
		}
	}
}
