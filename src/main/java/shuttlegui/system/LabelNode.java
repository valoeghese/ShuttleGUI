package shuttlegui.system;

import javax.swing.JLabel;

public class LabelNode extends ChildTreeNode<JLabel, LabelNode> {
	@Override
	JLabel createBuiltComponent() {
		JLabel field = new JLabel();
		field.setText(this.name);
		return field;
	}

	@Override
	LabelNode getSelf() {
		return this;
	}
}
