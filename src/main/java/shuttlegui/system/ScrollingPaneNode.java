package shuttlegui.system;

import javax.swing.JScrollPane;

public class ScrollingPaneNode extends BranchingTreeNode<JScrollPane, ScrollingPaneNode> {
	@Override
	JScrollPane createBuiltComponent() {
		JScrollPane result = new JScrollPane();
		return result;
	}

	@Override
	ScrollingPaneNode getSelf() {
		return this;
	}
}
