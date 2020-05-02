package shuttlegui.system;

import javax.swing.JTextField;

public class TextFieldNode extends ChildTreeNode<JTextField, TextFieldNode> {
	public TextFieldNode(int columns) {
		this.columns = columns;
	}

	private final int columns;

	@Override
	JTextField createBuiltComponent() {
		JTextField field = new JTextField(this.columns);
		return field;
	}

	@Override
	TextFieldNode getSelf() {
		return this;
	}
}
