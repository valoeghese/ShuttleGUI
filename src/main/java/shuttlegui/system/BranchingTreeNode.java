package shuttlegui.system;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JComponent;

abstract class BranchingTreeNode<T extends JComponent, S extends BranchingTreeNode<T, S>> extends ComponentTreeNode<T, S> {
	List<TreeNode<?,?>> children = new ArrayList<>();

	public <U extends JComponent, N extends BranchingTreeNode<U, N>> S child(N node) {
		this.children.add(node);
		return this.getSelf();
	}

	public <U extends JComponent, N extends ComponentTreeNode<U, N>> S child(N node) {
		this.children.add(node);
		return this.getSelf();
	}

	@Override
	void onComponentBuild(T component) {
		super.onComponentBuild(component);
		applyWidgets(component::add, this.children);
	}

	static void applyWidgets(Consumer<JComponent> componentConsumer, List<TreeNode<?,?>> children) {
		for (TreeNode<?,?> child : children) {
			if (child instanceof BranchingTreeNode<?,?>) {
				componentConsumer.accept((JComponent) child.build());
			} else if (child instanceof ComponentTreeNode<?,?>) {
				componentConsumer.accept((JComponent) child.build());
			}
		}
	}
}
