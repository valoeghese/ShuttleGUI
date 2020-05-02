package shuttlegui.system;

import java.awt.event.ActionListener;

import javax.annotation.Nullable;
import javax.swing.JComponent;

abstract class ExecutableNode<T extends JComponent, S extends ExecutableNode<T, S>> extends ChildTreeNode<T, S> {
	@Nullable private ActionListener executes;

	public S executes(ActionListener r) {
		this.executes = r;
		return this.getSelf();
	}

	@Override
	void onComponentBuild(T component) {
		super.onComponentBuild(component);

		if (this.executes != null) {
			addActionListener(component, this.executes);
		}
	}

	abstract void addActionListener(T component, ActionListener listener);
}
