package shuttlegui.system;

import java.awt.BorderLayout;

import javax.annotation.Nullable;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class PanelNode extends BranchingTreeNode<JPanel, PanelNode> {
	public PanelNode() {
		this(null);
	}

	public PanelNode(String name) {
		this.name = name;
	}

	@Nullable private final String name;

	/**
	 * @deprecated use {@link PanelNode#child(ChildTreeNode, BorderLayout)}
	 */
	@Override
	@Deprecated
	public <U extends JComponent, N extends ChildTreeNode<U, N>> PanelNode child(N node) {
		return super.child(node);
	}

	public <U extends JComponent, N extends ChildTreeNode<U, N>> PanelNode child(N node, String layout) {
		this.children.add(new ConfiguredBorderNode<>(node, layout));
		return this;
	}

	@Override
	void onComponentBuild(JPanel component) {
		this.addDecorations(component);

		applyWidgets(c -> {
			if (c instanceof DummyComponent<?>) {
				component.add(
						((DummyComponent<?>) c).parentComponent,
						((DummyComponent<?>) c).layout);
			} else {
				component.add(c);
			}
		}, this.children);
	}

	@Override
	JPanel createBuiltComponent() {
		JPanel result = new JPanel(new BorderLayout());

		if (this.name != null) {
			result.setName(this.name);
		}

		return result;
	}

	@Override
	PanelNode getSelf() {
		return this;
	}

	private static class ConfiguredBorderNode<U extends JComponent, N extends ChildTreeNode<U, N>> extends ChildTreeNode<DummyComponent<U>, ConfiguredBorderNode<U, N>>{
		private ConfiguredBorderNode(N node, String layout) {
			this.node = node;
			this.layout = layout;
		}

		private final N node;
		private final String layout;

		@Override
		DummyComponent<U> createBuiltComponent() {
			return new DummyComponent<>(this.node.build(), this.layout);
		}

		@Override
		ConfiguredBorderNode<U, N> getSelf() {
			return this;
		}
	}

	private static class DummyComponent<U extends JComponent> extends JComponent {
		private static final long serialVersionUID = 594687772553063436L;

		private DummyComponent(U parent, String layout) {
			this.parentComponent = parent;
			this.layout = layout;
		}

		private final U parentComponent;
		private final String layout;
	}
}
