package shuttlegui.system;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonNode extends ExecutableNode<JButton, ButtonNode> {
	public ButtonNode() {
	}

	public ButtonNode(String text) {
		this.text = text;
	}

	private String text = "";

	@Override
	public JButton createBuiltComponent() {
		JButton result = new JButton();
		result.setText(this.text);
		return result;
	}

	@Override
	ButtonNode getSelf() {
		return this;
	}

	@Override
	void addActionListener(JButton component, ActionListener listener) {
		component.addActionListener(listener);
	}
}
