package shuttlegui.system;

import javax.annotation.Nullable;
import javax.swing.JPanel;

public class PanelNode extends BranchingTreeNode<JPanel, PanelNode> {
	public PanelNode() {
		this(null);
	}

	public PanelNode(String name) {
		this.name = name;
	}

	@Nullable private final String name;

	@Override
	JPanel createBuiltComponent() {
		JPanel result = new JPanel();

		if (this.name != null) {
			result.setName(this.name);
		}

		return result;
	}

	@Override
	PanelNode getSelf() {
		return this;
	}
}
