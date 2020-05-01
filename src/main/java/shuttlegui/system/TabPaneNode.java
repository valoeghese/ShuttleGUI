package shuttlegui.system;

import javax.swing.JTabbedPane;

public class TabPaneNode extends BranchingTreeNode<JTabbedPane, TabPaneNode> {
	@Override
	JTabbedPane createBuiltComponent() {
		JTabbedPane result = new JTabbedPane();
		return result;
	}

	@Override
	TabPaneNode getSelf() {
		return this;
	}
}
