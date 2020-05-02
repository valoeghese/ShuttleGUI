package shuttlegui.system;

import javax.swing.JTextPane;

public class TextPaneNode extends ChildTreeNode<JTextPane, TextPaneNode> {
	@Override
	JTextPane createBuiltComponent() {
		JTextPane pane = new JTextPane();
		return pane;
	}

	@Override
	TextPaneNode getSelf() {
		return this;
	}
}
