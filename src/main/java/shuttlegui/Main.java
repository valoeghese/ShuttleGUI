package shuttlegui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import shuttlegui.system.PanelNode;
import shuttlegui.system.TextFieldNode;
import shuttlegui.system.WindowManager;

public class Main {
	public static void main(String[] args) {
		createLaunch();
	}

	private static void createLaunch() {
		WindowManager.dispatch(window -> window
				.child(new PanelNode("Workspace Launch")
						.onBuild()
						.child(new TextFieldNode(20)
								.onBuild(field -> {
									field.setForeground(Color.GRAY);
									field.addFocusListener(new PlaceholderTextFocusListener(field, "Workspace Name"));
								}))));
	}

	private static class PlaceholderTextFocusListener implements FocusListener {
		public PlaceholderTextFocusListener(JTextField parent, String placeholder) {
			this.parent = parent;
			this.placeholderText = placeholder;
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
