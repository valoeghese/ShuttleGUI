package shuttlegui.system;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import com.google.common.collect.ImmutableList;

public class DropdownBoxNode extends ExecutableNode<JComboBox<String>, DropdownBoxNode> {
	private List<String> options = new ArrayList<>();

	public DropdownBoxNode addOptions(String... options) {
		this.options.addAll(ImmutableList.copyOf(options));
		return this;
	}

	@Override
	JComboBox<String> createBuiltComponent() {
		JComboBox<String> pane = new JComboBox<>(this.options.toArray(new String[this.options.size()]));
		return pane;
	}

	@Override
	DropdownBoxNode getSelf() {
		return this;
	}

	@Override
	void addActionListener(JComboBox<String> component, ActionListener listener) {
		component.addActionListener(listener);
	}
}
