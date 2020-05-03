package shuttlegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
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

	private static JTextField launcherTextEntry;
	private static JFrame currentFrame;
	private static PlaceholderTextFocusListener ptfl;

	private static void createLaunch() {
		WindowManager.dispatch(window -> window
				.title("ShuttleGUI Launcher")
				.size(400, 150)
				.dontExitOnClose()
				.onBuild(frame -> {
					currentFrame = frame;
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							if (launcherTextEntry != null) {
								System.exit(0);
							}
						}
					});
				})
				.child(new PanelNode("Launcher")
						.setBorderLayout(() -> new BorderLayout(5, 10))
						.child(new LabelNode()
								.setName("Workspace Name:"), BorderLayout.WEST)
						.child(new TextFieldNode(20)
								.onBuild(field -> {
									field.setForeground(Color.GRAY);
									field.addFocusListener(ptfl = new PlaceholderTextFocusListener(field, "name"));
									launcherTextEntry = field;
								}), BorderLayout.CENTER)
						.child(new ButtonNode("Launch")
								.executes(event -> {
									String workspace = ptfl.noEntry ? "" : launcherTextEntry.getText().trim();

									if (!workspace.isEmpty()) {
										System.out.println("Launching Workspace! " + workspace);
										launcherTextEntry = null;
										ptfl = null;
										currentFrame.dispatchEvent(new WindowEvent(currentFrame, WindowEvent.WINDOW_CLOSING));
										createMain(workspace);
									}
								}), BorderLayout.SOUTH)));
	}

	private static void createMain(String workspace) {
		WindowManager.dispatch(window -> window
				.title("ShuttleGUI - " + workspace)
				.onBuild(frame -> {
					currentFrame = frame;
				}));
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
