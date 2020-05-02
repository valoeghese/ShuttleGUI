package shuttlegui.system;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.JComponent;

abstract class BranchingTreeNode<T extends JComponent, S extends BranchingTreeNode<T, S>> extends ChildTreeNode<T, S> {
	List<ChildTreeNode<?,?>> children = new ArrayList<>();

	public <U extends JComponent, N extends ChildTreeNode<U, N>> S child(N node) {
		this.children.add(node);
		return this.getSelf();
	}

	@Override
	void onComponentBuild(T component) {
		super.onComponentBuild(component);
		applyWidgets(component::add, this.children);
	}

	static void applyWidgets(Consumer<JComponent> componentConsumer, List<ChildTreeNode<?,?>> children) {
		applyWidgets(componentConsumer, TreeNode::build, children);
	}

	static void applyWidgets(Consumer<JComponent> componentConsumer, Function<ChildTreeNode<?,?>, JComponent> buildingFunction, List<ChildTreeNode<?,?>> children) {
		for (ChildTreeNode<?,?> child : children) {
			JComponent component = buildingFunction.apply(child);

			if (component != null) {
				componentConsumer.accept(component);
			}
		}
	}
}
